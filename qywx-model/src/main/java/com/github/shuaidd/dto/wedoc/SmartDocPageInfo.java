package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 智能文档页面信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocPageInfo {

    /**
     * 页面 ID
     */
    @JsonProperty("page_id")
    private String pageId;

    /**
     * 页面标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 父页面 ID；为空表示第一层页面
     */
    @JsonProperty("parent_id")
    private String parentId;

    /**
     * 排序位置，表示排在哪个页面之后
     */
    @JsonProperty("after_id")
    private String afterId;

    /**
     * 页面布局模式：1-默认布局，2-纸张布局，3-全宽布局
     */
    @JsonProperty("layout_mode")
    private Integer layoutMode;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAfterId() {
        return afterId;
    }

    public void setAfterId(String afterId) {
        this.afterId = afterId;
    }

    public Integer getLayoutMode() {
        return layoutMode;
    }

    public void setLayoutMode(Integer layoutMode) {
        this.layoutMode = layoutMode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pageId", pageId)
                .append("title", title)
                .append("parentId", parentId)
                .append("afterId", afterId)
                .append("layoutMode", layoutMode)
                .toString();
    }
}
