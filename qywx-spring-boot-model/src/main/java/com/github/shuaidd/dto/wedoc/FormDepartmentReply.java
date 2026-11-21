package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表部门答案集合
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormDepartmentReply {

    /**
     * 选择的部门列表
     */
    @JsonProperty("list")
    private List<FormDepartmentReplyItem> list;

    public List<FormDepartmentReplyItem> getList() {
        return list;
    }

    public void setList(List<FormDepartmentReplyItem> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("list", list)
                .toString();
    }
}
