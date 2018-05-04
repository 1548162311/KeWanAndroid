package com.ocn.kewanandroid.ui.hierarchy.fragment;

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
import com.ocn.kewanandroid.presenter.hierarchy.KnowledgeHierarchyPresenter;

public class KnowledgeHierarchyFragment extends AbstractRootFragment<KnowledgeHierarchyPresenter> {

    public static KnowledgeHierarchyFragment getInstance(String param1, String param2) {
        KnowledgeHierarchyFragment fragment = new KnowledgeHierarchyFragment();
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
    protected void initInject() {

    }
}
