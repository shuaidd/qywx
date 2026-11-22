package com.github.shuaidd.response.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 成员邮箱未读数响应。
 */
public class MailUnreadCountResponse extends AbstractBaseResponse {

    /**
     * 未读邮件数量
     */
    @JsonProperty("count")
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
