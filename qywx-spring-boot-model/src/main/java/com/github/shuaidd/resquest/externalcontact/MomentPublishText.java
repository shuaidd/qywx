package com.github.shuaidd.resquest.externalcontact;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 企业朋友圈文本内容
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishText {

    /**
     * 消息文本内容；不能与附件同时为空，最长 2000 个字符
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("content", content)
                .toString();
    }
}