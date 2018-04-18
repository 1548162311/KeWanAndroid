package com.ocn.kewanandroid.base.presenter;

import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.core.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by kevin on 2018/4/13.
 */

public class BasePresenter<T extends BaseView> implements AbstractPresenter<T> {
    protected T mView;
    private CompositeDisposable compositeDisposable;
    private DataManager mDataManager;

    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }
    @Override
    public void attachView(T view) {
        mView = view;
    }
    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void detachView() {
        this.mView=null;
        if (compositeDisposable != null){
            compositeDisposable.clear();
        }
    }

    @Override
    public boolean getNightModeState() {
        return mDataManager.getNightModeState();
    }
}
