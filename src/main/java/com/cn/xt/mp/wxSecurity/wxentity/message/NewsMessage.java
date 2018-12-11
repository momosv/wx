package com.cn.xt.mp.wxSecurity.wxentity.message;

import java.util.List;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/28 15:21
 **/
public class NewsMessage extends BaseMessage {
        private int ArticleCount;
        private List<News> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        this.ArticleCount = articleCount;
    }

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> articles) {
        this.Articles = articles;
    }
}
