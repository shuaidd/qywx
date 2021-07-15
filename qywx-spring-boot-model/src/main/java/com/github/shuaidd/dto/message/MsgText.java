package com.github.shuaidd.dto.message;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 17:17
 **/
public class MsgText {
    public MsgText(String content) {
        this.content = content;
    }

    public MsgText() {
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
