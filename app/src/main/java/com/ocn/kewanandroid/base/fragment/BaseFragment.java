package com.ocn.kewanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.di.component.DaggerFragmentComponent;
import com.ocn.kewanandroid.di.component.FragmentComponent;
import com.ocn.kewanandroid.di.module.FragmentModule;
import com.ocn.kewanandroid.utils.CommonUtils;

import javax.inject.Inject;

/**
 * Created by kevin on 2018/4/18.
 */

public abstract class BaseFragment<T extends AbstractPresenter> extends AbstractSimpleFragment implements BaseView{
    @Inject
    T mPresent;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (mPresent!= null){
            mPresent.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
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
        if(isAdded()){
            CommonUtils.showSnackMessage(_mActivity,errorMsg);
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
    public void showCollectFail() {
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.collect_fail));
    }

    @Override
    public void showCancelCollectFail() {
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.cancel_collect_fail));
    }

    @Override
    public void showCollectSuccess() {

    }

    @Override
    public void showCancelCollectSuccess() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }
    /**
     * 注入当前Fragment所需的依赖
     */
    protected abstract void initInject();
}
