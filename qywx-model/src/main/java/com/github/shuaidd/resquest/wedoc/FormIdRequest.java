package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取收集表信息请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormIdRequest {

    /**
     * 收集表id
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
