package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 启用或禁用邮箱账号请求。
 */
public class ActEmailRequest {

    /**
     * 成员 userid；与 publicemail_id 至少传一项，同时传时只操作成员邮箱
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 业务邮箱 ID；与 userid 至少传一项
     */
    @JsonProperty("publicemail_id")
    private Integer publicemailId;

    /**
     * 操作类型：1启用，2禁用
     */
    @JsonProperty("type")
    private Integer type;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getPublicemailId() {
        return publicemailId;
    }

    public void setPublicemailId(Integer publicemailId) {
        this.publicemailId = publicemailId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
