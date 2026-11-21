package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 读取收集表答案请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormAnswerRequest {

    /**
     * 操作的收集表周期id
     */
    @JsonProperty("repeated_id")
    private String repeatedId;

    /**
     * 需要拉取的答案id列表，批次最大100
     */
    @JsonProperty("answer_ids")
    private List<Long> answerIds;

    public String getRepeatedId() {
        return repeatedId;
    }

    public void setRepeatedId(String repeatedId) {
        this.repeatedId = repeatedId;
    }

    public List<Long> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(List<Long> answerIds) {
        this.answerIds = answerIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("repeatedId", repeatedId)
                .append("answerIds", answerIds)
                .toString();
    }
}
