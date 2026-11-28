package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 删除智能文档内容块请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class DeleteSmartDocBlockRequest {

    /**
     * 智能文档的文档 ID
     */
    @JsonProperty("docid")
    private String docId;

    /**
     * 页面 ID
     */
    @JsonProperty("page_id")
    private String pageId;

    /**
     * 需要删除的内容块 ID 列表
     */
    @JsonProperty("ids")
    private List<String> ids;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("pageId", pageId)
                .append("ids", ids)
                .toString();
    }
}