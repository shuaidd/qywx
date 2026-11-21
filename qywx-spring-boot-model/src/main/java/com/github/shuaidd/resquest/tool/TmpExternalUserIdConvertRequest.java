package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * tmp_external_userid 转换请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TmpExternalUserIdConvertRequest {

    @JsonProperty("business_type")
    private Integer businessType;

    @JsonProperty("user_type")
    private Integer userType;

    @JsonProperty("tmp_external_userid_list")
    private List<String> tmpExternalUserIdList;

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public List<String> getTmpExternalUserIdList() {
        return tmpExternalUserIdList;
    }

    public void setTmpExternalUserIdList(List<String> tmpExternalUserIdList) {
        this.tmpExternalUserIdList = tmpExternalUserIdList;
    }
}
