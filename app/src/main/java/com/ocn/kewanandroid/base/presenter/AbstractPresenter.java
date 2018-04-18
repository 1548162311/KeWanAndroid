package com.ocn.kewanandroid.base.presenter;

import com.ocn.kewanandroid.base.view.BaseView;

/**
 * Created by kevin on 2018/4/13.
 */

public interface AbstractPresenter<T extends BaseView> {


    void attachView(T View);

    void detachView();

    boolean getNightModeState();
}
