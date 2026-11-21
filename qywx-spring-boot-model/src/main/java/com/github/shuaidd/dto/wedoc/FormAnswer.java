package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表答案集合
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormAnswer {

    /**
     * 答案列表
     */
    @JsonProperty("answer_list")
    private List<FormAnswerRecord> answerList;

    public List<FormAnswerRecord> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<FormAnswerRecord> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answerList", answerList)
                .toString();
    }
}
