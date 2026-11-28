package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 未观看直播统计信息（v1协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingUnwatchStatInfo {

    /**
     * 未观看直播的学生列表
     */
    @JsonProperty("students")
    private List<LivingUnwatchStudent> students;

    public List<LivingUnwatchStudent> getStudents() {
        return students;
    }

    public void setStudents(List<LivingUnwatchStudent> students) {
        this.students = students;
    }
}
