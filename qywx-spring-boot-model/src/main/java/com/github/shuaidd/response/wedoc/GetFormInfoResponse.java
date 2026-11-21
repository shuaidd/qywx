package com.github.shuaidd.response.wedoc;

import com.github.shuaidd.dto.wedoc.FormInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取收集表信息响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetFormInfoResponse extends AbstractBaseResponse {

    /**
     * 收集表信息
     */
    @JsonProperty("form_info")
    private FormInfo formInfo;

    public FormInfo getFormInfo() {
        return formInfo;
    }

    public void setFormInfo(FormInfo formInfo) {
        this.formInfo = formInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("formInfo", formInfo)
                .toString();
    }
}
