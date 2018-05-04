package com.ocn.kewanandroid.widget;

import android.text.TextUtils;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.core.http.exception.ServerException;
import com.ocn.kewanandroid.utils.LogHelper;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by kevin on 2018/4/20.
 */

public abstract class BaseSubscribe<T> extends ResourceSubscriber<T> {

    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseSubscribe(BaseView view){
        this.mView = view;
    }
    protected BaseSubscribe(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseSubscribe(BaseView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseSubscribe(BaseView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView==null){
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)){
            mView.showErrorMsg(mErrorMsg);
        }else if (e instanceof ServerException){
            mView.showErrorMsg(e.toString());
        }else if (e instanceof HttpException){
            mView.showErrorMsg(MyApp.getInstance().getString(R.string.http_error));
        }else{
            mView.showErrorMsg(MyApp.getInstance().getString(R.string.unKnown_error));
            LogHelper.d(e.toString());
        }
        if (isShowError){
            mView.showError();
        }
    }
}
