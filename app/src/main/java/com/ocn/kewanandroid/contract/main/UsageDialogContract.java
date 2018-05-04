package com.ocn.kewanandroid.contract.main;

import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.main.search.UsefulSiteData;

import java.util.List;

/**
 * Created by kevin on 2018/5/4.
 */

public interface UsageDialogContract {

    interface View extends BaseView{
        void showUsefulSites(BaseResponse<List<UsefulSiteData>> usefulSitesResponse);

        void showUsefulSitesDataFail();
    }
    interface Presenter extends AbstractPresenter<UsageDialogContract.View> {

        void getUsefulSites();
    }
}
