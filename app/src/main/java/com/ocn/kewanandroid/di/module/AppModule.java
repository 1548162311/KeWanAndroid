package com.ocn.kewanandroid.di.module;

import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.core.DataManager;
import com.ocn.kewanandroid.core.db.DbHelper;
import com.ocn.kewanandroid.core.db.GreenDaoHelper;
import com.ocn.kewanandroid.core.http.HttpHelper;
import com.ocn.kewanandroid.core.http.RetrofitHelper;
import com.ocn.kewanandroid.core.prefs.PreferenceHelper;
import com.ocn.kewanandroid.core.prefs.PreferenceHelperImpl;

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
    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }


    @Provides
    @Singleton
    DbHelper provideDBHelper(GreenDaoHelper greenDaoHelper){
        return greenDaoHelper;
    }
    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferenceHelperImpl preferenceHelper){
        return preferenceHelper;
    }
    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper,DbHelper dbHelper,PreferenceHelper preferenceHelper){
        return new DataManager(httpHelper,dbHelper,preferenceHelper);
    }
}
