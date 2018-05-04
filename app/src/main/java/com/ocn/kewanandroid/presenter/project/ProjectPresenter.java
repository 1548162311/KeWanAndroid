package com.ocn.kewanandroid.presenter.project;

import com.ocn.kewanandroid.base.presenter.BasePresenter;
import com.ocn.kewanandroid.contract.project.ProjectContract;
import com.ocn.kewanandroid.core.DataManager;

import javax.inject.Inject;

/**
 * Created by kevin on 2018/5/3.
 */

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter{

    private DataManager mDataManager;

    @Inject
    ProjectPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getProjectClassifyData() {

    }
}
