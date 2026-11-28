package com.github.shuaidd.resquest.school;

import java.util.List;

/**
 * 批量创建或更新学生的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class BatchStudentOperationRequest {

    /**
     * 学生列表，每次最多100个
     */
    private List<StudentItem> students;

    public List<StudentItem> getStudents() {
        return students;
    }

    public void setStudents(List<StudentItem> students) {
        this.students = students;
    }
}
