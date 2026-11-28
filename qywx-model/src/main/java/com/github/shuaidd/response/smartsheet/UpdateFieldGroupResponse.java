package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.FieldGroup;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 更新智能表格编组响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UpdateFieldGroupResponse extends AbstractBaseResponse {

    /**
     * 更新后的编组信息
     */
    @JsonProperty("field_group")
    private FieldGroup fieldGroup;

    public FieldGroup getFieldGroup() {
        return fieldGroup;
    }

    public void setFieldGroup(FieldGroup fieldGroup) {
        this.fieldGroup = fieldGroup;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fieldGroup", fieldGroup)
                .toString();
    }
}
