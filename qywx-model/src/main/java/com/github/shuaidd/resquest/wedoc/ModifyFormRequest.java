package com.github.shuaidd.resquest.wedoc;

import com.github.shuaidd.dto.wedoc.FormInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 编辑收集表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ModifyFormRequest {

    /**
     * 操作类型；1-全量修改问题，2-全量修改设置
     */
    @JsonProperty("oper")
    private Integer oper;

    /**
     * 收集表id
     */
    @JsonProperty("formid")
    private String formId;

    /**
     * 收集表信息
     */
    @JsonProperty("form_info")
    private FormInfo formInfo;

    public Integer getOper() {
        return oper;
    }

    public void setOper(Integer oper) {
        this.oper = oper;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public FormInfo getFormInfo() {
        return formInfo;
    }

    public void setFormInfo(FormInfo formInfo) {
        this.formInfo = formInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("oper", oper)
                .append("formId", formId)
                .append("formInfo", formInfo)
                .toString();
    }
}
