package com.ocn.kewanandroid.presenter.main;

import com.ocn.kewanandroid.base.presenter.BasePresenter;
import com.ocn.kewanandroid.contract.main.SplashContract;
import com.ocn.kewanandroid.core.DataManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by kevin on 2018/4/18.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    private DataManager dataManager;
    @Inject
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(final SplashContract.View view) {
        super.attachView(view);
        long splashTime = 2000;
        addSubscribe(Observable.timer(splashTime, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                view.jumpToMain();
            }
        }));
    }
}
