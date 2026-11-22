package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 查询用户邮件客户端功能属性请求。
 */
public class GetUserOptionRequest {

    /**
     * 用户 userid
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 功能属性类型：1强制安全登录，2 IMAP/SMTP，3 POP/SMTP，4安全登录
     */
    @JsonProperty("type")
    private List<Integer> typeList;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
    }
}
