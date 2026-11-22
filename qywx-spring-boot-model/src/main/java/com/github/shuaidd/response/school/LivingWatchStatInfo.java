package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 观看直播统计信息（v1协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingWatchStatInfo {

    /**
     * 观看直播的学生列表
     */
    @JsonProperty("students")
    private List<LivingWatchStudent> students;

    /**
     * 观看直播的游客列表
     */
    @JsonProperty("visitors")
    private List<LivingVisitor> visitors;

    public List<LivingWatchStudent> getStudents() {
        return students;
    }

    public void setStudents(List<LivingWatchStudent> students) {
        this.students = students;
    }

    public List<LivingVisitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<LivingVisitor> visitors) {
        this.visitors = visitors;
    }
}
