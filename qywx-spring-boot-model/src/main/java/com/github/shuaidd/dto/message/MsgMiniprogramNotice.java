package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.ContentItem;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 17:58
 **/
public class MsgMiniprogramNotice {

    @JsonProperty("appid")
    private String appId;
    private String page;
    private String title;
    private String description;
    @JsonProperty("emphasis_first_item")
    private Boolean emphasisFirstItem;
    @JsonProperty("content_item")
    private List<ContentItem> contentItems;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEmphasisFirstItem() {
        return emphasisFirstItem;
    }

    public void setEmphasisFirstItem(Boolean emphasisFirstItem) {
        this.emphasisFirstItem = emphasisFirstItem;
    }

    public List<ContentItem> getContentItems() {
        return contentItems;
    }

    public void setContentItems(List<ContentItem> contentItems) {
        this.contentItems = contentItems;
    }
}
