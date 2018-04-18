package com.ocn.kewanandroid.di.component;

import android.app.Activity;

import com.ocn.kewanandroid.MainActivity;
import com.ocn.kewanandroid.di.module.ActivityModule;
import com.ocn.kewanandroid.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by kevin on 2018/4/16.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    /**
     * 获取Activity实例
     * @return
     */
    Activity getActivity();


}
