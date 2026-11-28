package com.github.shuaidd.resquest.linkedcorp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-04 21:36
 **/
public class LinkedCorpDeptRequest {

    @JsonProperty("department_id")
    private String departmentId;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
