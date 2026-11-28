package com.github.shuaidd.response.wedoc;

import com.github.shuaidd.response.AbstractBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建收集表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class CreateFormResponse extends AbstractBaseResponse {

    /**
     * 创建的收集表id
     */
    @JsonProperty("formid")
    private String formId;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("formId", formId)
                .toString();
    }
}
