package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表成员答案集合
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormMemberReply {

    /**
     * 选择的成员列表
     */
    @JsonProperty("list")
    private List<FormMemberReplyItem> list;

    public List<FormMemberReplyItem> getList() {
        return list;
    }

    public void setList(List<FormMemberReplyItem> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("list", list)
                .toString();
    }
}
