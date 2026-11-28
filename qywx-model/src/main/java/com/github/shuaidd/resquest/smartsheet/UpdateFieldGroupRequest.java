package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.FieldGroupChild;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 更新智能表格编组请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UpdateFieldGroupRequest {

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
     * 编组ID
     */
    @JsonProperty("field_group_id")
    private String fieldGroupId;

    /**
     * 编组名称，不能和已有名称重复
     */
    @JsonProperty("name")
    private String name;

    /**
     * 编组包含的字段列表，最多支持150个字段
     */
    @JsonProperty("children")
    private List<FieldGroupChild> children;

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

    public String getFieldGroupId() {
        return fieldGroupId;
    }

    public void setFieldGroupId(String fieldGroupId) {
        this.fieldGroupId = fieldGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldGroupChild> getChildren() {
        return children;
    }

    public void setChildren(List<FieldGroupChild> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("sheetId", sheetId)
                .append("fieldGroupId", fieldGroupId)
                .append("name", name)
                .append("children", children)
                .toString();
    }
}
