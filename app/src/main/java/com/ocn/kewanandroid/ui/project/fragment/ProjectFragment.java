package com.ocn.kewanandroid.ui.project.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.Constants;
import com.ocn.kewanandroid.base.fragment.AbstractRootFragment;
import com.ocn.kewanandroid.contract.project.ProjectContract;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.project.ProjectClassifyData;
import com.ocn.kewanandroid.presenter.project.ProjectPresenter;

import java.util.List;


public class ProjectFragment extends AbstractRootFragment<ProjectPresenter> implements ProjectContract.View {

    public static ProjectFragment getInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void showProjectClassifyData(BaseResponse<List<ProjectClassifyData>> projectClassifyResponse) {

    }

    @Override
    public void showProjectClassifyDataFail() {

    }

    @Override
    protected void initInject() {

    }
}
