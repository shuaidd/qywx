package com.github.shuaidd.response.linkedcorp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.linkedcorp.Department;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-04 21:45
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedCorpDeptResponse extends AbstractBaseResponse {

    @JsonProperty("department_list")
    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
