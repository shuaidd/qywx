package com.github.shuaidd.dto.message;

import com.github.shuaidd.dto.MpNewsArticle;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 17:54
 **/
public class MsgMpNews {
    private List<MpNewsArticle> articles;

    public List<MpNewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<MpNewsArticle> articles) {
        this.articles = articles;
    }
}
