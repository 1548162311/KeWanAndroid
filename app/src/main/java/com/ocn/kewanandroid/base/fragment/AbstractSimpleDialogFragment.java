package com.ocn.kewanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by kevin on 2018/4/18.
 */

public abstract class AbstractSimpleDialogFragment extends DialogFragment {

    private Unbinder unbinder;
    private View mRootView;
    private CompositeDisposable mCompositeDisposable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayout(),container,false);
        unbinder = ButterKnife.bind(this,mRootView);
        mCompositeDisposable = new CompositeDisposable();
        initEventAndData();
        return mRootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
        unbinder.unbind();
    }

    /**
     *获取当前Activity的UI布局
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();
}
