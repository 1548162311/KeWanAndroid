package com.ocn.kewanandroid.base.activity;

import android.support.v7.app.AppCompatDelegate;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.presenter.BasePresenter;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.di.component.ActivityComponent;
import com.ocn.kewanandroid.di.component.DaggerActivityComponent;
import com.ocn.kewanandroid.di.component.DaggerAppComponent;
import com.ocn.kewanandroid.utils.CommonUtils;

import javax.inject.Inject;

/**
 * Created by kevin on 2018/4/13.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AbstractSimpleActivity implements BaseView {
    @Inject
    T mPresent;

    @Override
    protected void onDestroy() {
        if (mPresent != null){
            mPresent.detachView();
        }
        super.onDestroy();
    }
    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder().appComponent(MyApp.getAppComponent()).build();
    }

    @Override
    protected void onViewCreate() {
        super.onViewCreate();
        initInject();
        if (mPresent!=null){
            mPresent.attachView(this);
        }
    }

    @Override
    public void useNightMode(boolean isNightMode) {
        if (isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtils.showSnackMessage(this,errorMsg);
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
        CommonUtils.showSnackMessage(this, getString(R.string.collect_fail));
    }

    @Override
    public void showCancelCollectFail() {
        CommonUtils.showSnackMessage(this, getString(R.string.cancel_collect_fail));
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
     * 注入当前Activity所需的依赖
     */
    protected abstract void initInject();
}
