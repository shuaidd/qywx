package com.github.shuaidd.dto.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-08-31 10:23
 **/
public class DeptUser {

    /**
     * 用户userid，当用户在多个部门下时会有多条记录
     */
    @JsonProperty(value = "userid")
    private String userId;

    /**
     * 用户所属部门
     */
    private Integer department;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeptUser.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("department=" + department)
                .toString();
    }
}
