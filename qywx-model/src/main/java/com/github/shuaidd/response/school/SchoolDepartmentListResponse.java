package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 家校部门列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolDepartmentListResponse extends com.github.shuaidd.response.BaseResponse {

    /**
     * 家校部门列表
     */
    private List<SchoolDepartment> departments;

    public List<SchoolDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(List<SchoolDepartment> departments) {
        this.departments = departments;
    }
}
