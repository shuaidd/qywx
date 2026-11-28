package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 家校学生信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class Student {

    /**
     * 学生UserID
     */
    @JsonProperty("student_userid")
    private String studentUserId;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生所在班级id列表，最多20个
     */
    private List<Integer> department;

    /**
     * 学生家长列表
     */
    private List<ParentRelation> parents;

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public List<ParentRelation> getParents() {
        return parents;
    }

    public void setParents(List<ParentRelation> parents) {
        this.parents = parents;
    }
}
