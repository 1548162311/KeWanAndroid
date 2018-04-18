package com.ocn.kewanandroid.di.module;


import com.ocn.kewanandroid.BuildConfig;
import com.ocn.kewanandroid.app.Constants;
import com.ocn.kewanandroid.core.http.api.RetrofitApis;
import com.ocn.kewanandroid.core.http.cookies.CookiesManager;
import com.ocn.kewanandroid.di.qualifier.WanAndroidUrl;
import com.ocn.kewanandroid.utils.CommonUtils;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kevin on 2018/4/16.
 */

@Module
public class HttpModule {
    @Singleton
    @Provides
    RetrofitApis provideRetrofitApi(@WanAndroidUrl Retrofit retrofit){
        return retrofit.create(RetrofitApis.class);
    }

    @Singleton
    @Provides
    @WanAndroidUrl
    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient okHttpClient){
       return createRetrofit(builder,okHttpClient,RetrofitApis.HOST);
    }

    @Singleton
    @Provides
    Retrofit.Builder providesRetrofitBuilder(){
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder){
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile,1024*1024*50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if(!CommonUtils.isNetworkConnected()){
                   request = request.newBuilder()
                           .cacheControl(CacheControl.FORCE_CACHE)
                           .build();
                }
                Response response = chain.proceed(request);
                if(CommonUtils.isNetworkConnected()){
                    // 有网络时, 不缓存, 最大保存时长为0
                    int maxAge = 0;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                }else{
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20,TimeUnit.SECONDS);
        builder.writeTimeout(20,TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        //cookie认证
        builder.cookieJar(new CookiesManager());
        return builder.build();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient okHttpClient, String url) {
        return builder
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
