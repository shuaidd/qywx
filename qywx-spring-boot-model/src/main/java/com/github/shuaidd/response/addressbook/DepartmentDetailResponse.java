package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.Department;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 获取单个部门详情响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class DepartmentDetailResponse extends AbstractBaseResponse {

    @JsonProperty("department")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
