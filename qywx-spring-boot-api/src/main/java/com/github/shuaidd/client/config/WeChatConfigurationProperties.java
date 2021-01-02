package com.github.shuaidd.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-03 15:47
 **/
@ConfigurationProperties(prefix = "qywx")
public class WeChatConfigurationProperties {

    /**
     * 企业号
     */
    private String corpId;

    /**
     * 企业微信api域名
     */
    private String url;

    /**
     * 企业微信公共目录
     */
    private String publicPath;

    /**
     * 应用列表
     */
    private List<ApplicationProperties> applicationList;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public List<ApplicationProperties> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<ApplicationProperties> applicationList) {
        this.applicationList = applicationList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicPath() {
        return publicPath;
    }

    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath;
    }
}
