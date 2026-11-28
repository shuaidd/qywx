package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 家校学生关联的家长信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ParentRelation {

    /**
     * 家长的userid
     */
    @JsonProperty("parent_userid")
    private String parentUserId;

    /**
     * 学生与家长的关系
     */
    private String relation;

    /**
     * 家长手机号，第三方应用不可获取
     */
    private String mobile;

    /**
     * 家长是否关注学校通知：0-未关注；1-已关注
     */
    @JsonProperty("is_subscribe")
    private Integer subscribe;

    /**
     * 家长的外部联系人userid，仅已关注后返回
     */
    @JsonProperty("external_userid")
    private String externalUserId;

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }
}
