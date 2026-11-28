package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 更新学生的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UpdateStudentRequest {

    /**
     * 学生UserID
     */
    @JsonProperty("student_userid")
    private String studentUserId;

    /**
     * 学生手机号
     */
    private String mobile;

    /**
     * 要变更的学生UserID，每个学生仅能修改一次
     */
    @JsonProperty("new_student_userid")
    private String newStudentUserId;

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

    public String getNewStudentUserId() {
        return newStudentUserId;
    }

    public void setNewStudentUserId(String newStudentUserId) {
        this.newStudentUserId = newStudentUserId;
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
