package com.ocn.kewanandroid.presenter.mainpager;

import com.ocn.kewanandroid.app.Constants;
import com.ocn.kewanandroid.base.presenter.BasePresenter;
import com.ocn.kewanandroid.component.RxBus;
import com.ocn.kewanandroid.contract.mainpager.MainPagerContract;
import com.ocn.kewanandroid.core.DataManager;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.main.banner.BannerData;
import com.ocn.kewanandroid.core.bean.main.collect.FeedArticleData;
import com.ocn.kewanandroid.core.bean.main.collect.FeedArticleListData;
import com.ocn.kewanandroid.core.bean.main.login.LoginData;
import com.ocn.kewanandroid.core.event.CollectEvent;
import com.ocn.kewanandroid.core.event.LoginEvent;
import com.ocn.kewanandroid.utils.RxUtils;
import com.ocn.kewanandroid.widget.BaseObserver;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;

/**
 * Created by kevin on 2018/5/3.
 */

public class MainPagerPresenter extends BasePresenter<MainPagerContract.View>  implements MainPagerContract.Presenter{


    private DataManager mDataManager;


    @Inject
    MainPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(MainPagerContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                    .filter(new Predicate<CollectEvent>() {
                        @Override
                        public boolean test(CollectEvent collectEvent) throws Exception {
                            return !collectEvent.isCancelCollectSuccess();
                        }
                    })
                    .subscribe(new Consumer<CollectEvent>() {
                        @Override
                        public void accept(CollectEvent collectEvent) throws Exception {
                            mView.showCollectSuccess();
                        }
                    }));

        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                    .filter(new Predicate<CollectEvent>() {
                        @Override
                        public boolean test(CollectEvent collectEvent) throws Exception {
                            return collectEvent.isCancelCollectSuccess();
                        }
                    })
                    .subscribe(new Consumer<CollectEvent>() {
                        @Override
                        public void accept(CollectEvent collectEvent) throws Exception {
                            mView.showCancelCollectSuccess();
                        }
                    }));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(new Predicate<LoginEvent>() {
                    @Override
                    public boolean test(LoginEvent loginEvent) throws Exception {
                        return loginEvent.isLogin();
                    }
                })
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {
                        mView.showLoginView();
                    }
                }));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                    .filter(new Predicate<LoginEvent>() {
                        @Override
                        public boolean test(LoginEvent loginEvent) throws Exception {
                            return !loginEvent.isLogin();
                        }
                    })
                    .subscribe(new Consumer<LoginEvent>() {
                        @Override
                        public void accept(LoginEvent loginEvent) throws Exception {
                            mView.showLogoutView();
                        }
                    }));
    }

    @Override
    public void loadMainPagerData() {
        String account = mDataManager.getLoginAccount();
        String password = mDataManager.getLoginPassword();
        Observable<BaseResponse<LoginData>> mLoginObservable = mDataManager.getLoginData(account,password);
        Observable<BaseResponse<List<BannerData>>> mBannerObservable = mDataManager.getBannerData();
        Observable<BaseResponse<FeedArticleListData>> mArticleObservable = mDataManager.getFeedArticleList(0);
    }

    @Override
    public void getFeedArticleList() {

    }

    @Override
    public void addCollectArticle(int position, FeedArticleData feedArticleData) {

    }

    @Override
    public void cancelCollectArticle(int position, FeedArticleData feedArticleData) {

    }

    @Override
    public void getBannerData() {

    }

    @Override
    public void autoRefresh() {

    }

    @Override
    public void loadMore() {

    }
}
