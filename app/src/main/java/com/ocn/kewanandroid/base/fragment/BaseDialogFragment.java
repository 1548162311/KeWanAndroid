package com.ocn.kewanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.base.presenter.BasePresenter;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.di.component.DaggerFragmentComponent;
import com.ocn.kewanandroid.di.component.FragmentComponent;
import com.ocn.kewanandroid.di.module.FragmentModule;
import com.ocn.kewanandroid.utils.CommonUtils;

import javax.inject.Inject;

/**
 * Created by kevin on 2018/4/18.
 */

public abstract class BaseDialogFragment<T extends BasePresenter> extends AbstractSimpleDialogFragment implements BaseView {
    @Inject
    T mPresent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
        if (mPresent!= null){
            mPresent.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresent!=null){
            mPresent.detachView();
        }
        super.onDestroyView();
    }
    public FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(MyApp.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void useNightMode(boolean isNightMode) {

    }

    @Override
    public void showErrorMsg(String errorMsg) {
        if (getActivity() != null){
            CommonUtils.showSnackMessage(getActivity(), errorMsg);
        }
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showCollectFail() {
        if (getActivity() != null) {
            CommonUtils.showSnackMessage(getActivity(), getString(R.string.collect_fail));
        }
    }

    @Override
    public void showCancelCollectFail() {
        if (getActivity() != null) {
            CommonUtils.showSnackMessage(getActivity(), getString(R.string.cancel_collect_fail));
        }
    }

    @Override
    public void showCollectSuccess() {

    }

    @Override
    public void showCancelCollectSuccess() {

    }
    /**
     * 注入当前Fragment所需的依赖
     */
    protected abstract void initInject();

}
