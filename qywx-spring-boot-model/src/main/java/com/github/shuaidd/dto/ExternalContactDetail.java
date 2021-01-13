package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 16:37
 **/
public class ExternalContactDetail {
    @JsonProperty(value = "external_userid")
    private String externalUserId;

    private String name;
    private String position;
    private String avatar;
    @JsonProperty("corp_name")
    private String corpName;
    @JsonProperty("corp_full_name")
    private String corpFullName;
    private Integer type;
    private Integer gender;
    @JsonProperty("unionid")
    private String unionId;
    @JsonProperty("external_profile")
    private ExternalProfile externalProfile;

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpFullName() {
        return corpFullName;
    }

    public void setCorpFullName(String corpFullName) {
        this.corpFullName = corpFullName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public ExternalProfile getExternalProfile() {
        return externalProfile;
    }

    public void setExternalProfile(ExternalProfile externalProfile) {
        this.externalProfile = externalProfile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("externalUserId", externalUserId)
                .append("name", name)
                .append("position", position)
                .append("avatar", avatar)
                .append("corpName", corpName)
                .append("corpFullName", corpFullName)
                .append("type", type)
                .append("gender", gender)
                .append("unionId", unionId)
                .append("externalProfile", externalProfile)
                .toString();
    }
}
