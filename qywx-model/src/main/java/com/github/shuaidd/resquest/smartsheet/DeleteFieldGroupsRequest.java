package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 删除智能表格编组请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class DeleteFieldGroupsRequest {

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
     * 要删除的编组ID列表
     */
    @JsonProperty("field_group_ids")
    private List<String> fieldGroupIds;

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

    public List<String> getFieldGroupIds() {
        return fieldGroupIds;
    }

    public void setFieldGroupIds(List<String> fieldGroupIds) {
        this.fieldGroupIds = fieldGroupIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("sheetId", sheetId)
                .append("fieldGroupIds", fieldGroupIds)
                .toString();
    }
}
