package com.ocn.kewanandroid.widget;


import android.text.TextUtils;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.core.http.exception.ServerException;
import com.ocn.kewanandroid.utils.LogHelper;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * Created by kevin on 2018/5/4.
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObserver(BaseView view) {
        this.mView = view;
    }
    protected BaseObserver(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(BaseView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }
    protected BaseObserver(BaseView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }


    @Override
    public void onError(Throwable e) {
        if (mView==null){
            return;
        }
        if (mErrorMsg!= null && !TextUtils.isEmpty(mErrorMsg)){
            mView.showErrorMsg(mErrorMsg);
        }else if(e instanceof ServerException){
            mView.showErrorMsg(e.toString());
        }else if(e instanceof HttpException){
            mView.showErrorMsg(MyApp.getInstance().getString(R.string.http_error));
        }else{
            mView.showErrorMsg(MyApp.getInstance().getString(R.string.unKnown_error));
            LogHelper.d(e.toString());
        }
        if (isShowError) {
            mView.showError();
        }
    }

    @Override
    public void onComplete() {

    }
}
