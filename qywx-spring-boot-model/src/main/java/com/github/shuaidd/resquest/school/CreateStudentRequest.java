package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 创建学生的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class CreateStudentRequest {

    /**
     * 学生UserID，学校内必须唯一
     */
    @JsonProperty("student_userid")
    private String studentUserId;

    /**
     * 学生手机号
     */
    private String mobile;

    /**
     * 是否发起邀请，默认为true
     */
    @JsonProperty("to_invite")
    private Boolean toInvite;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生所在班级id列表，最多20个
     */
    private java.util.List<Integer> department;

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getToInvite() {
        return toInvite;
    }

    public void setToInvite(Boolean toInvite) {
        this.toInvite = toInvite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(java.util.List<Integer> department) {
        this.department = department;
    }
}
