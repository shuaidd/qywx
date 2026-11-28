package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表其他选项答案
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormOptionExtendReply {

    /**
     * 其他选项的答案id
     */
    @JsonProperty("option_reply")
    private Integer optionReply;

    /**
     * 其他选项的答案内容
     */
    @JsonProperty("extend_text")
    private String extendText;

    public Integer getOptionReply() {
        return optionReply;
    }

    public void setOptionReply(Integer optionReply) {
        this.optionReply = optionReply;
    }

    public String getExtendText() {
        return extendText;
    }

    public void setExtendText(String extendText) {
        this.extendText = extendText;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("optionReply", optionReply)
                .append("extendText", extendText)
                .toString();
    }
}
