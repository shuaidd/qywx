package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 智能表格编组字段
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FieldGroupChild {

    /**
     * 字段ID，字段只能同时存在于一个编组
     */
    @JsonProperty("field_id")
    private String fieldId;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fieldId", fieldId)
                .toString();
    }
}
