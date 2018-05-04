package com.ocn.kewanandroid.contract.mainpager;

import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.main.banner.BannerData;
import com.ocn.kewanandroid.core.bean.main.collect.FeedArticleData;
import com.ocn.kewanandroid.core.bean.main.collect.FeedArticleListData;

import java.util.List;

/**
 * Created by kevin on 2018/5/3.
 */

public interface MainPagerContract {

    interface View extends BaseView{

        void showAutoLoginSuccess();

        void showAutoLoginFail();

        void showArticleList(BaseResponse<FeedArticleListData> feedArticleListResponse, boolean isRefresh);

        void showCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse);

        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse);

        void showBannerData(BaseResponse<List<BannerData>> bannerResponse);

        void showArticleListFail();

        void showBannerDataFail();

    }
    interface Presenter extends AbstractPresenter<View> {

        void loadMainPagerData();

        void getFeedArticleList();

        void addCollectArticle(int position, FeedArticleData feedArticleData);

        void cancelCollectArticle(int position, FeedArticleData feedArticleData);

        void getBannerData();

        void autoRefresh();

        void loadMore();
    }
}
