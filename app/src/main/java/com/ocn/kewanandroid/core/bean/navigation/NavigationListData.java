package com.ocn.kewanandroid.core.bean.navigation;

import com.ocn.kewanandroid.core.bean.main.collect.FeedArticleData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kevin on 2018/4/11.
 */

public class NavigationListData implements Serializable {

    private List<FeedArticleData> articles;
    private int cid;
    private String name;

    public List<FeedArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<FeedArticleData> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
