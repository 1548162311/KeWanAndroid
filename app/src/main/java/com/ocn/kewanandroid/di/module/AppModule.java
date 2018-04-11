package com.ocn.kewanandroid.di.module;

import com.ocn.kewanandroid.app.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kevin on 2018/4/11.
 */
@Module
public class AppModule {
    private final MyApp application;

    public AppModule(MyApp application){
        this.application = application;
    }
    @Provides
    @Singleton
    MyApp provideApplicationContext(){
        return  application;
    }

}
