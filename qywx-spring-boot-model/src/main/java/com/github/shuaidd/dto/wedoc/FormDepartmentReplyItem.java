package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表部门答案
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormDepartmentReplyItem {

    /**
     * 部门id
     */
    @JsonProperty("department_id")
    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("departmentId", departmentId)
                .toString();
    }
}
