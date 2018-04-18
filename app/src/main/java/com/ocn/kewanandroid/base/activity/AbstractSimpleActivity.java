package com.ocn.kewanandroid.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.ocn.kewanandroid.component.ActivityCollector;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by kevin on 2018/4/13.
 */

public abstract class AbstractSimpleActivity extends SupportActivity {

    private Unbinder unbinder;
    private AbstractSimpleActivity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mActivity = this;
        onViewCreate();
        ActivityCollector.getInstance().addActivity(this);
        initEventAndData();
    }

    protected void setToolBar(Toolbar toolbar, CharSequence title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        assert getSupportActionBar()!=null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        unbinder.unbind();
    }

    protected void initEventAndData() {

    }

    protected void onViewCreate() {

    }

    protected abstract int getLayoutId();
}
