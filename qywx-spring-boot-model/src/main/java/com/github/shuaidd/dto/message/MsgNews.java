package com.github.shuaidd.dto.message;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 17:50
 **/
public class MsgNews {
    private List<NewsArticle> articles;

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }
}
