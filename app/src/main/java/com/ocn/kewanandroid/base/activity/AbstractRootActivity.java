package com.ocn.kewanandroid.base.activity;

import com.ocn.kewanandroid.base.presenter.BasePresenter;

/**
 * Created by kevin on 2018/4/16.
 */

public abstract class AbstractRootActivity<T extends BasePresenter> extends BaseActivity<T> {

    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;

    @Override
    protected void initEventAndData() {

    }
}
