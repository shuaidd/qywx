package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 提交智能文档导出任务请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ExportSmartDocRequest {

    /**
     * 智能文档的文档 ID
     */
    @JsonProperty("docid")
    private String docId;

    /**
     * 期望返回的内容格式类型：1-Markdown 格式
     */
    @JsonProperty("content_type")
    private Integer contentType;

    /**
     * 智能文档链接，用于定位待导出的文档
     */
    @JsonProperty("url")
    private String url;

    /**
     * 页面 ID；为空表示导出整个文档
     */
    @JsonProperty("page_id")
    private String pageId;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("contentType", contentType)
                .append("url", url)
                .append("pageId", pageId)
                .toString();
    }
}