package com.ocn.kewanandroid.presenter.navigation;

import com.ocn.kewanandroid.base.presenter.BasePresenter;
import com.ocn.kewanandroid.contract.navigation.NavigationContract;
import com.ocn.kewanandroid.core.DataManager;

import javax.inject.Inject;

/**
 * Created by kevin on 2018/5/3.
 */

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {

    private DataManager mDataManager;

    @Inject
     NavigationPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getNavigationListData() {

    }
}
