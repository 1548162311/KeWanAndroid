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
import com.ocn.kewanandroid.core.http.api.RetrofitApis;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by kevin on 2018/4/13.
 */

public class RetrofitHelper implements HttpHelper {
    private RetrofitApis mRetrofitApis;

    @Inject
    RetrofitHelper(RetrofitApis retrofitApis){
        this.mRetrofitApis=retrofitApis;
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mRetrofitApis.getFeedArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchList(int pageNum, String k) {
        return mRetrofitApis.getSearchList(pageNum, k);
    }

    @Override
    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData() {
        return mRetrofitApis.getTopSearchData();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites() {
        return mRetrofitApis.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return mRetrofitApis.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(int page, int cid) {
        return mRetrofitApis.getKnowledgeHierarchyDetailData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mRetrofitApis.getNavigationListData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mRetrofitApis.getProjectClassifyData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid) {
        return mRetrofitApis.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mRetrofitApis.getLoginData(username,password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String rePassword) {
        return mRetrofitApis.getRegisterData(username, password, rePassword);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id) {
        return mRetrofitApis.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(String title, String author, String link) {
        return mRetrofitApis.addCollectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getCollectList(int page) {
        return mRetrofitApis.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(int id) {
        return mRetrofitApis.cancelCollectPageArticle(id,-1);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(int id) {
        return mRetrofitApis.cancelCollectArticle(id,-1);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mRetrofitApis.getBannerData();
    }
}
