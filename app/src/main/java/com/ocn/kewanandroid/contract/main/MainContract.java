package com.ocn.kewanandroid.contract.main;

import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.view.BaseView;

/**
 * Created by kevin on 2018/4/19.
 */

public interface MainContract {
    interface View extends BaseView{

        void showSwitchProject();

        void showSwitchNavigation();
    }
    interface Present extends AbstractPresenter<View>{

        void setNightModeState(boolean b);
    }
}
