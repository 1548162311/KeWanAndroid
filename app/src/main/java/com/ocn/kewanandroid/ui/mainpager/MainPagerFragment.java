package com.ocn.kewanandroid.ui.mainpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.Constants;
import com.ocn.kewanandroid.base.fragment.AbstractRootFragment;
import com.ocn.kewanandroid.contract.mainpager.MainPagerContract;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.main.banner.BannerData;
import com.ocn.kewanandroid.core.bean.main.collect.FeedArticleData;
import com.ocn.kewanandroid.core.bean.main.collect.FeedArticleListData;
import com.ocn.kewanandroid.presenter.mainpager.MainPagerPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kevin on 2018/5/3.
 */

public class MainPagerFragment extends AbstractRootFragment<MainPagerPresenter> implements MainPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.main_pager_recycler_view)
    RecyclerView mRecyclerView;


    private Banner mBanner;

    private boolean isRecreate;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isRecreate = getArguments().getBoolean(Constants.ARG_PARAM1);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mBanner != null){
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner!= null){
            mBanner.stopAutoPlay();
        }
    }

    public static MainPagerFragment getInstance(boolean param1, String param2) {
        MainPagerFragment fragment = new MainPagerFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_pager;
    }


    @Override
    public void showAutoLoginSuccess() {

    }

    @Override
    public void showAutoLoginFail() {

    }

    @Override
    public void showArticleList(BaseResponse<FeedArticleListData> feedArticleListResponse, boolean isRefresh) {

    }

    @Override
    public void showCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse) {

    }

    @Override
    public void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse) {

    }

    @Override
    public void showBannerData(BaseResponse<List<BannerData>> bannerResponse) {

    }

    @Override
    public void showArticleListFail() {

    }

    @Override
    public void showBannerDataFail() {

    }

}
