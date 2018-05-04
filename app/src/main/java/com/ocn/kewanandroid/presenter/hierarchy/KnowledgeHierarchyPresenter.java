package com.ocn.kewanandroid.presenter.hierarchy;

import com.ocn.kewanandroid.base.presenter.BasePresenter;
import com.ocn.kewanandroid.contract.KnowledgeHierarchyContract;
import com.ocn.kewanandroid.core.DataManager;

import javax.inject.Inject;

/**
 * Created by kevin on 2018/5/3.
 */

public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.View> implements KnowledgeHierarchyContract.Presenter {
    private DataManager mDataManager;
    @Inject
    KnowledgeHierarchyPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getKnowledgeHierarchyData() {

    }
}
