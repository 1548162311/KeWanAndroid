package com.ocn.kewanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.utils.CommonUtils;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by kevin on 2018/4/18.
 */

public abstract class AbstractSimpleFragment extends SupportFragment {
    private Unbinder unbinder;
    private long clickTime;
    private CompositeDisposable mCompositeDisposable;
    public boolean isInnerFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        mCompositeDisposable =  new CompositeDisposable();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApp.getRefWatcher(_mActivity);
        refWatcher.watch(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initEventAndData();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount()>1){
            popChild();
        }else{
            if (isInnerFragment){
                _mActivity.finish();
                return true;
            }
            long currentTime = System.currentTimeMillis();
            long time = 2000;
            if (currentTime-clickTime>time){
                CommonUtils.showSnackMessage(_mActivity,getString(R.string.double_click_exit_tint));
                clickTime = System.currentTimeMillis();
            }else{
                _mActivity.finish();
            }
        }
        return true;
    }

    protected abstract void initEventAndData();

    protected abstract int getLayoutId();
}
