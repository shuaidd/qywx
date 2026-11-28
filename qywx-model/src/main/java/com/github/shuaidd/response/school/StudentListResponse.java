package com.github.shuaidd.response.school;

import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 家校班级下的学生列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class StudentListResponse extends AbstractBaseResponse {

    /**
     * 学生列表
     */
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
