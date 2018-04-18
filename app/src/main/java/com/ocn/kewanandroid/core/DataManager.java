package com.ocn.kewanandroid.core;

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
import com.ocn.kewanandroid.core.dao.HistoryData;
import com.ocn.kewanandroid.core.db.DbHelper;
import com.ocn.kewanandroid.core.http.HttpHelper;
import com.ocn.kewanandroid.core.prefs.PreferenceHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by kevin on 2018/4/13.
 */

public class DataManager implements HttpHelper,DbHelper,PreferenceHelper {

    private HttpHelper mHttpHelper;
    private DbHelper mDbHelper;
    private PreferenceHelper mPreferenceHelper;

    public DataManager(HttpHelper httpHelper,DbHelper dbHelper,PreferenceHelper preferenceHelper){

    }
    @Override
    public List<HistoryData> addHistoryData(String data) {
        return mDbHelper.addHistoryData(data);
    }

    @Override
    public void clearHistoryData() {
        mDbHelper.clearHistoryData();
    }

    @Override
    public List<HistoryData> loadAllHistoryData() {
        return mDbHelper.loadAllHistoryData();
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferenceHelper.setLoginAccount(account);
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferenceHelper.setLoginPassword(password);
    }

    @Override
    public String getLoginAccount() {
        return mPreferenceHelper.getLoginAccount();
    }

    @Override
    public String getLoginPassword() {
        return mPreferenceHelper.getLoginPassword();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferenceHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferenceHelper.setCurrentPage(position);
    }

    @Override
    public int getCurrentPage() {
        return mPreferenceHelper.getCurrentPage();
    }

    @Override
    public void setProjectCurrentPage(int position) {
        mPreferenceHelper.setProjectCurrentPage(position);
    }

    @Override
    public int getProjectCurrentPage() {
        return mPreferenceHelper.getProjectCurrentPage();
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferenceHelper.getAutoCacheState();
    }

    @Override
    public boolean getNoImageState() {
        return mPreferenceHelper.getNoImageState();
    }

    @Override
    public boolean getNightModeState() {
        return mPreferenceHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean b) {
        mPreferenceHelper.setNightModeState(b);
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferenceHelper.setNoImageState(b);
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mPreferenceHelper.setAutoCacheState(b);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mHttpHelper.getFeedArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchList(int pageNum, String k) {
        return mHttpHelper.getSearchList(pageNum, k);
    }

    @Override
    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData() {
        return  mHttpHelper.getTopSearchData();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites() {
        return mHttpHelper.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return mHttpHelper.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(int page, int cid) {
        return mHttpHelper.getKnowledgeHierarchyDetailData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mHttpHelper.getNavigationListData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mHttpHelper.getProjectClassifyData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid) {
        return mHttpHelper.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mHttpHelper.getLoginData(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String rePassword) {
        return mHttpHelper.getRegisterData(username, password, rePassword);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id) {
        return mHttpHelper.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(String title, String author, String link) {
        return mHttpHelper.addCollectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getCollectList(int page) {
        return mHttpHelper.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(int id) {
        return mHttpHelper.cancelCollectPageArticle(id);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(int id) {
        return mHttpHelper.cancelCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mHttpHelper.getBannerData();
    }
}
