package com.github.shuaidd.response.linkedcorp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.WeChatUserExtAttr;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-04 21:29
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedGroupUser {

    @JsonProperty("userid")
    private String userId;

    private String name;

    private String mobile;

    private String telephone;

    private String email;

    private String position;

    @JsonProperty("corpid")
    private String corpId;

    private List<String> department;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public List<String> getDepartment() {
        return department;
    }

    public void setDepartment(List<String> department) {
        this.department = department;
    }

    public WeChatUserExtAttr getExtAttr() {
        return extAttr;
    }

    public void setExtAttr(WeChatUserExtAttr extAttr) {
        this.extAttr = extAttr;
    }
}
