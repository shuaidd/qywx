package com.github.shuaidd.dto.linkedcorp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-04 21:46
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {

    @JsonProperty("department_id")
    private String departmentId;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("parentid")
    private String parentId;

    private int order;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
