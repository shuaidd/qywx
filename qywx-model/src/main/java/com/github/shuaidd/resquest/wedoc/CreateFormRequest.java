package com.github.shuaidd.resquest.wedoc;

import com.github.shuaidd.dto.wedoc.FormInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建收集表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class CreateFormRequest {

    /**
     * 空间spaceid
     */
    @JsonProperty("spaceid")
    private String spaceId;

    /**
     * 父目录fileid；在根目录时为空间spaceid
     */
    @JsonProperty("fatherid")
    private String fatherId;

    /**
     * 收集表信息
     */
    @JsonProperty("form_info")
    private FormInfo formInfo;

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
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
                .append("spaceId", spaceId)
                .append("fatherId", fatherId)
                .append("formInfo", formInfo)
                .toString();
    }
}
