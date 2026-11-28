package com.github.shuaidd.response.wedoc;

import com.github.shuaidd.dto.wedoc.FormAnswer;
import com.github.shuaidd.response.AbstractBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 读取收集表答案响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormAnswerResponse extends AbstractBaseResponse {

    /**
     * 答案数据
     */
    @JsonProperty("answer")
    private FormAnswer answer;

    public FormAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(FormAnswer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
