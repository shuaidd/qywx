package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 家长关联的孩子信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ParentChild {

    /**
     * 学生的userid
     */
    @JsonProperty("student_userid")
    private String studentUserId;

    /**
     * 家长与学生的关系
     */
    private String relation;

    /**
     * 学生姓名
     */
    private String name;

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
