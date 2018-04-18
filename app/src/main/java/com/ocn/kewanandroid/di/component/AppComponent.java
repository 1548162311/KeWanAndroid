package com.ocn.kewanandroid.di.component;

import com.ocn.kewanandroid.app.MyApp;
import com.ocn.kewanandroid.core.DataManager;
import com.ocn.kewanandroid.core.db.GreenDaoHelper;
import com.ocn.kewanandroid.core.http.RetrofitHelper;
import com.ocn.kewanandroid.core.prefs.PreferenceHelperImpl;
import com.ocn.kewanandroid.di.module.AppModule;
import com.ocn.kewanandroid.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kevin on 2018/4/11.
 */
@Singleton
@Component(modules = {AppModule.class,HttpModule.class})
public interface AppComponent {
    /**
     * 提供App的Context
     * @return
     */
    MyApp getContext();

    /**
     *数据中心
     * @return
     */
    DataManager getDataManager();

    /**
     *提供http的帮助类
     * @return
     */
    RetrofitHelper retrofitHelper();

    /**
     *提供数据库帮助类
     * @return
     */
    GreenDaoHelper realmHelper();

    /**
     *提供sp帮助类
     * @return
     */
    PreferenceHelperImpl preferencesHelper();
}
