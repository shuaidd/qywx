package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获取智能文档内容块列表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetSmartDocBlockListRequest {

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
     * 需要获取的内容块 ID 列表，为空时获取页面内容块
     */
    @JsonProperty("ids")
    private List<String> ids;

    /**
     * 分批起始点，最小为 0
     */
    @JsonProperty("start")
    private Integer start;

    /**
     * 分批大小，最大为 200，默认为 200
     */
    @JsonProperty("limit")
    private Integer limit;

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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("pageId", pageId)
                .append("ids", ids)
                .append("start", start)
                .append("limit", limit)
                .toString();
    }
}