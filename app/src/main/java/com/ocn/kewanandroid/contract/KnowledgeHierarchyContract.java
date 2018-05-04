package com.ocn.kewanandroid.contract;

import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.main.hierarchy.KnowledgeHierarchyData;

import java.util.List;

/**
 * Created by kevin on 2018/5/3.
 */

public interface KnowledgeHierarchyContract {
    interface View extends BaseView {
         void showKnowledgeHierarchyData(BaseResponse<List<KnowledgeHierarchyData>> knowledgeHierarchyResponse);

        void showKnowledgeHierarchyDetailDataFail();
    }
    interface Presenter extends AbstractPresenter<View>{
        /**
         * 知识列表
         */
        void getKnowledgeHierarchyData();
    }
}
