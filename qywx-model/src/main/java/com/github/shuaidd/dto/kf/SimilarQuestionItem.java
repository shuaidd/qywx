package com.github.shuaidd.dto.kf;

import com.github.shuaidd.dto.message.MsgText;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-01 17:10
 **/
public class SimilarQuestionItem {

    private MsgText text;

    public MsgText getText() {
        return text;
    }

    public void setText(MsgText text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimilarQuestionItem.class.getSimpleName() + "[", "]")
                .add("text=" + text)
                .toString();
    }
}
