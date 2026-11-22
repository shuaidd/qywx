package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 家校家长请求明细
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ParentItem {

    /**
     * 家长UserID，学校内必须唯一
     */
    @JsonProperty("parent_userid")
    private String parentUserId;

    /**
     * 要变更的家长UserID，每个家长仅能修改一次
     */
    @JsonProperty("new_parent_userid")
    private String newParentUserId;

    /**
     * 家长手机号
     */
    private String mobile;

    /**
     * 是否发起邀请，默认为true
     */
    @JsonProperty("to_invite")
    private Boolean toInvite;

    /**
     * 家长的孩子列表，最多10个
     */
    private List<ParentChildItem> children;

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getNewParentUserId() {
        return newParentUserId;
    }

    public void setNewParentUserId(String newParentUserId) {
        this.newParentUserId = newParentUserId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getToInvite() {
        return toInvite;
    }

    public void setToInvite(Boolean toInvite) {
        this.toInvite = toInvite;
    }

    public List<ParentChildItem> getChildren() {
        return children;
    }

    public void setChildren(List<ParentChildItem> children) {
        this.children = children;
    }
}
