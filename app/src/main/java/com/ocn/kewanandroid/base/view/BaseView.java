package com.ocn.kewanandroid.base.view;

/**
 * Created by kevin on 2018/4/13.
 */

public interface BaseView {

    void useNightMode(boolean isNightMode);

    void showErrorMsg(String errorMsg);

    void showNormal();

    void showError();

    void showLoading();

    void reload();

    void showLoginView();

    void showLogoutView();

    void showCollectFail();

    void showCancelCollectFail();

    void showCollectSuccess();

    void showCancelCollectSuccess();

}
