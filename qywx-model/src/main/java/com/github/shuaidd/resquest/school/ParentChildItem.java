package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 家长与学生关系的请求明细
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ParentChildItem {

    /**
     * 学生的UserID
     */
    @JsonProperty("student_userid")
    private String studentUserId;

    /**
     * 家长与学生的关系，最长32字节
     */
    private String relation;

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
}
