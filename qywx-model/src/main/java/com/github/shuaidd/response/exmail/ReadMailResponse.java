package com.github.shuaidd.response.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 应用邮件内容响应。
 */
public class ReadMailResponse extends AbstractBaseResponse {

    /**
     * 邮件 eml 内容
     */
    @JsonProperty("mail_data")
    private String mailData;

    public String getMailData() {
        return mailData;
    }

    public void setMailData(String mailData) {
        this.mailData = mailData;
    }
}
