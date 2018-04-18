package com.ocn.kewanandroid.di.module;

import android.app.Activity;

import com.ocn.kewanandroid.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kevin on 2018/4/16.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }
    @Provides
    @ActivityScope
    Activity provideActivity(){
        return  mActivity;
    }
}
