package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 观看直播统计信息（v2协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingWatchStatV2Info {

    /**
     * 观看直播的学生列表
     */
    @JsonProperty("students")
    private List<LivingWatchStudentV2> students;

    /**
     * 观看直播的家长列表
     */
    @JsonProperty("parents")
    private List<LivingWatchParent> parents;

    /**
     * 观看直播的游客列表
     */
    @JsonProperty("visitors")
    private List<LivingVisitor> visitors;

    public List<LivingWatchStudentV2> getStudents() {
        return students;
    }

    public void setStudents(List<LivingWatchStudentV2> students) {
        this.students = students;
    }

    public List<LivingWatchParent> getParents() {
        return parents;
    }

    public void setParents(List<LivingWatchParent> parents) {
        this.parents = parents;
    }

    public List<LivingVisitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<LivingVisitor> visitors) {
        this.visitors = visitors;
    }
}
