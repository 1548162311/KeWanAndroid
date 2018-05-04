package com.ocn.kewanandroid.di.component;

import android.app.Activity;

import com.ocn.kewanandroid.di.module.ActivityModule;
import com.ocn.kewanandroid.di.scope.ActivityScope;
import com.ocn.kewanandroid.ui.main.activity.MainActivity;
import com.ocn.kewanandroid.ui.main.activity.SplashActivity;

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

    /**
     * 注入SplashActivity所需的依赖
     * @param splashActivity
     */
    void inject(SplashActivity splashActivity);

    /**
     * 注入MAinActivity所需的依赖
     * @param mainActivity
     */
    void inject(MainActivity mainActivity);


}
