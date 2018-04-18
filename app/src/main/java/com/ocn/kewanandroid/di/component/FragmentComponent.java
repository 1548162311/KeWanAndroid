package com.ocn.kewanandroid.di.component;

import android.app.Activity;

import com.ocn.kewanandroid.di.module.FragmentModule;
import com.ocn.kewanandroid.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by kevin on 2018/4/18.
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
