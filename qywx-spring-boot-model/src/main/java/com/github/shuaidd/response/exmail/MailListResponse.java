package com.github.shuaidd.response.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 应用收件箱邮件列表响应。
 */
public class MailListResponse extends AbstractBaseResponse {

    /**
     * 邮件列表
     */
    @JsonProperty("mail_list")
    private List<MailDTO> mailList;

    public List<MailDTO> getMailList() {
        return mailList;
    }

    public void setMailList(List<MailDTO> mailList) {
        this.mailList = mailList;
    }

    /**
     * 收件箱邮件信息
     */
    public static class MailDTO {

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
}
