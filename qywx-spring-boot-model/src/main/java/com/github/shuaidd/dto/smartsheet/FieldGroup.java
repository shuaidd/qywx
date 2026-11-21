package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 智能表格编组
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FieldGroup {

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
                .append("fieldGroupId", fieldGroupId)
                .append("name", name)
                .append("children", children)
                .toString();
    }
}
