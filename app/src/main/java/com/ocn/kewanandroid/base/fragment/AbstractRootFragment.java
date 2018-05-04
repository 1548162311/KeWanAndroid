package com.ocn.kewanandroid.base.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.base.presenter.BasePresenter;

/**
 * Created by kevin on 2018/5/3.
 */

public abstract class AbstractRootFragment<T extends BasePresenter> extends BaseFragment<T> {


    private LottieAnimationView mLoadingAnimation;
    private View mLoadingView;
    private ViewGroup mNormalView;
    private ViewGroup mParent;

    @Override
    protected void initEventAndData() {
        if(getView() == null){
            return;
        }
        mNormalView = getView().findViewById(R.id.normal_view);
    }
}
