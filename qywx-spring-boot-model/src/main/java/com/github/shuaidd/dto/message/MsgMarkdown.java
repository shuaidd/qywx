package com.github.shuaidd.dto.message;

import java.util.StringJoiner;

/**
 * 描述
 * <p>
 *
 * @author ddshuai
 * date 2019-04-08 17:57
 **/
public class MsgMarkdown {
    private String content;

    public MsgMarkdown(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MsgMarkdown.class.getSimpleName() + "[", "]")
                .add("content='" + content + "'")
                .toString();
    }
}
