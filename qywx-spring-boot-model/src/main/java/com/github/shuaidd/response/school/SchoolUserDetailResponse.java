package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 家校通讯录用户详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolUserDetailResponse extends AbstractBaseResponse {

    /**
     * 用户类型：1-学生；2-家长
     */
    @JsonProperty("user_type")
    private Integer userType;

    /**
     * 学生信息，user_type为1时返回
     */
    private Student student;

    /**
     * 家长信息，user_type为2时返回
     */
    private Parent parent;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
