package com.ocn.kewanandroid.contract.navigation;

import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.navigation.NavigationListData;

import java.util.List;

/**
 * Created by kevin on 2018/5/3.
 */

public interface NavigationContract {
    interface View extends BaseView {
        void showNavigationListData(BaseResponse<List<NavigationListData>> navigationListResponse);

        void showNavigationListFail();
    }
    interface Presenter extends AbstractPresenter<View> {

        void getNavigationListData();
    }
}
