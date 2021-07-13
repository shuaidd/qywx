package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-05 17:18
 **/
public class WeChatUser extends AbstractBaseResponse {

    @JsonProperty("userid")
    private String userId;

    private String name;

    private String alias;

    private String mobile;

    private List<Integer> department;

    private List<Integer> order;

    private String position;

    /**
     * 性别。1表示男性，2表示女性
     */
    private String gender;

    private String email;

    @JsonProperty("is_leader_in_dept")
    private List<Integer> isLeaderInDept;

    private Integer enable;

    @JsonProperty("avatar_mediaid")
    private String avatarMediaId;

    private String telephone;

    private String address;

    @JsonProperty("to_invite")
    private Boolean toInvite;

    @JsonProperty("external_position")
    private String externalPosition;

    @JsonProperty("extattr")
    private WeChatUserExtAttr extAttr;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public List<Integer> getOrder() {
        return order;
    }

    public void setOrder(List<Integer> order) {
        this.order = order;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getIsLeaderInDept() {
        return isLeaderInDept;
    }

    public void setIsLeaderInDept(List<Integer> isLeaderInDept) {
        this.isLeaderInDept = isLeaderInDept;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getAvatarMediaId() {
        return avatarMediaId;
    }

    public void setAvatarMediaId(String avatarMediaId) {
        this.avatarMediaId = avatarMediaId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getToInvite() {
        return toInvite;
    }

    public void setToInvite(Boolean toInvite) {
        this.toInvite = toInvite;
    }

    public String getExternalPosition() {
        return externalPosition;
    }

    public void setExternalPosition(String externalPosition) {
        this.externalPosition = externalPosition;
    }

    public WeChatUserExtAttr getExtAttr() {
        return extAttr;
    }

    public void setExtAttr(WeChatUserExtAttr extAttr) {
        this.extAttr = extAttr;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WeChatUser.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("name='" + name + "'")
                .add("alias='" + alias + "'")
                .add("mobile='" + mobile + "'")
                .add("department=" + department)
                .add("order=" + order)
                .add("position='" + position + "'")
                .add("gender='" + gender + "'")
                .add("email='" + email + "'")
                .add("isLeaderInDept=" + isLeaderInDept)
                .add("enable=" + enable)
                .add("avatarMediaId='" + avatarMediaId + "'")
                .add("telephone='" + telephone + "'")
                .add("address='" + address + "'")
                .add("toInvite=" + toInvite)
                .add("externalPosition='" + externalPosition + "'")
                .add("extAttr=" + extAttr)
                .toString();
    }
}
