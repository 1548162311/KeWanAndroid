package com.ocn.kewanandroid.core.http.api;

import com.ocn.kewanandroid.core.bean.BaseResponse;
import com.ocn.kewanandroid.core.bean.main.banner.BannerData;
import com.ocn.kewanandroid.core.bean.main.collect.FeedArticleListData;
import com.ocn.kewanandroid.core.bean.main.hierarchy.KnowledgeHierarchyData;
import com.ocn.kewanandroid.core.bean.main.login.LoginData;
import com.ocn.kewanandroid.core.bean.main.search.TopSearchData;
import com.ocn.kewanandroid.core.bean.main.search.UsefulSiteData;
import com.ocn.kewanandroid.core.bean.navigation.NavigationListData;
import com.ocn.kewanandroid.core.bean.project.ProjectClassifyData;
import com.ocn.kewanandroid.core.bean.project.ProjectListData;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kevin on 2018/4/13.
 */

public interface RetrofitApis {
    String HOST = "http://www.wanandroid.com/";

    /**
     * 获取首页文章列表
     * @param num
     * @return
     */
    @GET("article/list/{num}/json")
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(@Path("num") int num);

    /**
     * 搜索
     * @param page
     * @param k
     * @return
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<FeedArticleListData>> getSearchList(@Path("page") int page ,@Field("k") String k);

    /**
     * 热搜
     * @return
     */
    @GET("hotkey/json")
    @Headers("Cache-Control: public, max-age=36000")
    Observable<BaseResponse<List<TopSearchData>>> getTopSearchData();

    /**
     * 常用网站
     * @return
     */
    @GET("friend/json")
    Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites();

    /**
     * 广告栏
     * @return
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 知识体系
     * @return
     */
    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();

    /**
     * 知识体系下的文章
     * @param page
     * @param cid
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(@Path("page") int page ,@Query("cid") int cid);


    /**
     * 导航
     * @return
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

    /**
     * 项目分类
     * @return
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * @param page
     * @param cid
     * @return
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ProjectListData>> getProjectListData(@Path("page") int page, @Query("cid") int cid);

    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getLoginData(@Field("username")String username,@Field("password")String password);

    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getRegisterData(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 收藏站内文章
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<FeedArticleListData>> addCollectArticle(@Path("id") int id);


    /**
     * 收藏站外文章
     * @param title
     * @param author
     * @param link
     * @return
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(@Field("title") String  title, @Field("author") String author, @Field("link") String link);

    /**
     *  获取收藏列表
     * @param page
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getCollectList(@Path("page") int page);

    /**
     * 取消站内文章
     * @param id
     * @param originId
     * @return
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(@Path("id") int id, @Field("originId") int originId);

    /**
     * 取消收藏页面站内文章
     * @param id
     * @param originId
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(@Path("id") int id, @Field("originId") int originId);
}
