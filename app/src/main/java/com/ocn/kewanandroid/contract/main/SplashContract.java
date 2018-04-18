package com.ocn.kewanandroid.contract.main;

import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.view.BaseView;

/**
 * Created by kevin on 2018/4/18.
 */

public interface SplashContract {
    interface View extends BaseView{
        void jumpToMain();
    }
    interface Presenter extends AbstractPresenter<View>{

    }
}
