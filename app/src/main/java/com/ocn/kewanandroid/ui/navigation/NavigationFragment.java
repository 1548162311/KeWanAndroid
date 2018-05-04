package com.ocn.kewanandroid.ui.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.Constants;
import com.ocn.kewanandroid.base.fragment.AbstractRootFragment;
import com.ocn.kewanandroid.contract.navigation.NavigationContract;
import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.navigation.NavigationListData;
import com.ocn.kewanandroid.presenter.navigation.NavigationPresenter;

import java.util.List;

/**
 * Created by kevin on 2018/5/3.
 */

public class NavigationFragment extends AbstractRootFragment<NavigationPresenter> implements NavigationContract.View {

    public static NavigationFragment getInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_hierarchy;
    }

    @Override
    public void showNavigationListData(BaseResponse<List<NavigationListData>> navigationListResponse) {

    }

    @Override
    public void showNavigationListFail() {

    }

    @Override
    protected void initInject() {

    }
}
