package com.ocn.kewanandroid.contract.project;

import com.ocn.kewanandroid.base.presenter.AbstractPresenter;
import com.ocn.kewanandroid.base.view.BaseView;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.project.ProjectClassifyData;

import java.util.List;

/**
 * Created by kevin on 2018/5/3.
 */

public interface ProjectContract {
    interface View extends BaseView{
         void showProjectClassifyData(BaseResponse<List<ProjectClassifyData>> projectClassifyResponse);

        void showProjectClassifyDataFail();
    }
    interface Presenter extends AbstractPresenter<View> {
        void getProjectClassifyData();
    }
}
