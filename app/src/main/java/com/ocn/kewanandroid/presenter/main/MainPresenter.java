package com.ocn.kewanandroid.presenter.main;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.base.presenter.BasePresenter;
import com.ocn.kewanandroid.component.RxBus;
import com.ocn.kewanandroid.contract.main.MainContract;
import com.ocn.kewanandroid.core.DataManager;
import com.ocn.kewanandroid.core.event.AutoLoginEvent;
import com.ocn.kewanandroid.core.event.LoginEvent;
import com.ocn.kewanandroid.core.event.NightModeEvent;
import com.ocn.kewanandroid.core.event.SwitchNavigationEvent;
import com.ocn.kewanandroid.core.event.SwitchProjectEvent;
import com.ocn.kewanandroid.utils.RxUtils;
import com.ocn.kewanandroid.widget.BaseSubscribe;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by kevin on 2018/4/19.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Present {

    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(NightModeEvent.class)
        .compose(RxUtils.<NightModeEvent>rxFlSchedulerHelper())
        .map(new Function<NightModeEvent, Boolean>() {
            @Override
            public Boolean apply(NightModeEvent nightModeEvent) throws Exception {
                return nightModeEvent.isNightMode();
            }
        }).subscribeWith(new BaseSubscribe<Boolean>(mView, MyApp.getInstance().getString(R.string.failed_to_cast_mode)) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.useNightMode(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        registerEvent();
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
        addSubscribe(RxBus.getDefault().toFlowable(AutoLoginEvent.class)
                    .subscribe(new Consumer<AutoLoginEvent>() {
                        @Override
                        public void accept(AutoLoginEvent autoLoginEvent) throws Exception {
                            mView.showLoginView();
                        }
                    }));

        addSubscribe(RxBus.getDefault().toFlowable(SwitchProjectEvent.class)
                    .subscribe(new Consumer<SwitchProjectEvent>() {
                        @Override
                        public void accept(SwitchProjectEvent switchProjectEvent) throws Exception {
                            mView.showSwitchProject();
                        }
                    }));

        addSubscribe(RxBus.getDefault().toFlowable(SwitchNavigationEvent.class)
                    .subscribe(new Consumer<SwitchNavigationEvent>() {
                        @Override
                        public void accept(SwitchNavigationEvent switchNavigationEvent) throws Exception {
                            mView.showSwitchNavigation();
                        }
                    }));
    }

    @Override
    public void setNightModeState(boolean b) {
        mDataManager.setNightModeState(b);
    }
}
