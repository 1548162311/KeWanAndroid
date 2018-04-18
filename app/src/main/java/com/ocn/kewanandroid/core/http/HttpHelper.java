package com.ocn.kewanandroid.core.http;

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

/**
 * Created by kevin on 2018/4/11.
 */

public interface HttpHelper {
    /**
     * 首页文章列表
     * @param pageNum
     * @return
     */
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum);

    /**
     * 获取搜索的文章列表
     * @param pageNum
     * @param k
     * @return
     */
    Observable<BaseResponse<FeedArticleListData>> getSearchList(int pageNum, String k);

    /**
     * 热搜
     * @return
     */
    Observable<BaseResponse<List<TopSearchData>>> getTopSearchData();

    /**
     * 常用网站
     * @return
     */
    Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites();

    /**
     * 知识体系
     * @return
     */
    Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();

    /**
     * 知识体系下的文章
     */
    Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(int page, int cid);

    /**
     * 导航
     * @return
     */
    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

    /**
     * 项目分类
     * @return
     */
    Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * @param page
     * @param cid
     * @return
     */
    Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    Observable<BaseResponse<LoginData>> getLoginData(String username, String password);

    /**
     * 注册
     * @param username
     * @param password
     * @param rePassword
     * @return
     */
    Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String rePassword);

    /**
     * 收藏站内文章
     * @param id
     * @return
     */
    Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id);

    /**
     * 收藏站外文章
     * @param title
     * @param author
     * @param link
     * @return
     */
    Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(String  title, String author, String link);

    /**
     * 获取收藏列表
     * @param page
     * @return
     */
    Observable<BaseResponse<FeedArticleListData>> getCollectList(int page);

    /**
     * 取消收藏页面站内文章
     * @param id
     * @return
     */
    Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(int id);

    /**
     * 取消站内文章
     * @param id
     * @return
     */
    Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(int id);

    /**
     *广告栏
     * @return
     */
    Observable<BaseResponse<List<BannerData>>> getBannerData();
}
