package com.ocn.kewanandroid.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.BuildConfig;
import android.support.v4.content.ContextCompat;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.core.dao.DaoMaster;
import com.ocn.kewanandroid.core.dao.DaoSession;
import com.ocn.kewanandroid.utils.CommonUtils;
import com.ocn.kewanandroid.utils.logger.TxtFormatStrategy;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by kevin on 2018/4/11.
 */

public class MyApp extends Application {
    private static MyApp mInstance;
    private RefWatcher refWatcher;
    private DaoSession mDaoSession;
    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
                //指定为Delivery Header，默认是贝塞尔雷达Header
                return new DeliveryHeader(context);
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new BallPulseFooter(context).setAnimatingColor(ContextCompat.getColor(context, R.color.colorPrimary));
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initGreenDao();
        refWatcher = LeakCanary.install(this);
        initBugly();

        initLogger();

    }

    public static synchronized MyApp getInstance(){
        return mInstance;
    }

    public static RefWatcher getRefWatcher(Context context){
        MyApp app = (MyApp) context.getApplicationContext();
        return app.refWatcher;
    }
    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,Constants.DB_NAME);
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        mDaoSession = daoMaster.newSession();

    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    private void initBugly() {
        // 获取当前包名
        String packageNme =getApplicationContext().getPackageName();
        // 获取当前进程名
        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setUploadProcess(processName ==null || processName.equals(packageNme));
        CrashReport.initCrashReport(getApplicationContext(),Constants.BUGLY_ID,false,strategy);
    }

    private void initLogger() {
        //DEBUG版本才打控制台log
        if(BuildConfig.DEBUG){
            Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
                    .tag(getString(R.string.app_name)).build()));
        }
        //把log存到本地
        Logger.addLogAdapter(new DiskLogAdapter(TxtFormatStrategy.newBuilder()
                .tag(getString(R.string.app_name)).build(getPackageName(), getString(R.string.app_name))));
    }

}
