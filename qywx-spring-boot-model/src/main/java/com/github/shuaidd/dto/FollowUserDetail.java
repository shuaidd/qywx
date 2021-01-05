package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 16:21
 **/
public class FollowUserDetail {

    @JsonProperty("userid")
    private String userId;

    private String state;

    @JsonProperty("oper_userid")
    private String operUserId;

    @JsonProperty("add_way")
    private Integer addWay;

    @JsonProperty("remark_mobiles")
    private List<String> remarkMobiles;

    @JsonProperty("remark_corp_name")
    private String remarkCorpName;

    private List<FollowUserDetail> tags;

    @JsonProperty("createtime")
    private Date createTime;

    private String description;

    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(String operUserId) {
        this.operUserId = operUserId;
    }

    public Integer getAddWay() {
        return addWay;
    }

    public void setAddWay(Integer addWay) {
        this.addWay = addWay;
    }

    public List<String> getRemarkMobiles() {
        return remarkMobiles;
    }

    public void setRemarkMobiles(List<String> remarkMobiles) {
        this.remarkMobiles = remarkMobiles;
    }

    public String getRemarkCorpName() {
        return remarkCorpName;
    }

    public void setRemarkCorpName(String remarkCorpName) {
        this.remarkCorpName = remarkCorpName;
    }

    public List<FollowUserDetail> getTags() {
        return tags;
    }

    public void setTags(List<FollowUserDetail> tags) {
        this.tags = tags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
