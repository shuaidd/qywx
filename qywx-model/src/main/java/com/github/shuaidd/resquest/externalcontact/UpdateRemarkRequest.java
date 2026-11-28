package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 21:26
 **/
public class UpdateRemarkRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("external_userid")
    private String externalUserId;

    private String remark;
    private String description;

    @JsonProperty("remark_company")
    private String remarkCompany;

    @JsonProperty("remark_mobiles")
    private List<String> remarkMobiles;

    @JsonProperty("remark_pic_mediaid")
    private String remarkPicMediaId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarkCompany() {
        return remarkCompany;
    }

    public void setRemarkCompany(String remarkCompany) {
        this.remarkCompany = remarkCompany;
    }

    public List<String> getRemarkMobiles() {
        return remarkMobiles;
    }

    public void setRemarkMobiles(List<String> remarkMobiles) {
        this.remarkMobiles = remarkMobiles;
    }

    public String getRemarkPicMediaId() {
        return remarkPicMediaId;
    }

    public void setRemarkPicMediaId(String remarkPicMediaId) {
        this.remarkPicMediaId = remarkPicMediaId;
    }
}
