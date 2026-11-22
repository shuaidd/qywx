package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 读取应用邮件内容请求。
 */
public class ReadMailRequest {

    /**
     * 邮件 ID
     */
    @JsonProperty("mail_id")
    private String mailId;

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }
}
