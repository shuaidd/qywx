package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取智能表格编组请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetFieldGroupsRequest {

    /**
     * 文档ID
     */
    @JsonProperty("docid")
    private String docId;

    /**
     * 子表ID
     */
    @JsonProperty("sheet_id")
    private String sheetId;

    /**
     * 分页偏移量，初始值为0
     */
    @JsonProperty("offset")
    private Integer offset;

    /**
     * 分页大小，每页返回的数据条数
     */
    @JsonProperty("limit")
    private Integer limit;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
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
                .append("sheetId", sheetId)
                .append("offset", offset)
                .append("limit", limit)
                .toString();
    }
}
