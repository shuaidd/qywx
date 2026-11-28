package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 未观看直播统计信息（v2协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingUnwatchStatV2Info {

    /**
     * 未观看直播的学生列表
     */
    @JsonProperty("students")
    private List<LivingUnwatchStudentV2> students;

    /**
     * 未观看直播的家长列表
     */
    @JsonProperty("parents")
    private List<LivingUnwatchParent> parents;

    public List<LivingUnwatchStudentV2> getStudents() {
        return students;
    }

    public void setStudents(List<LivingUnwatchStudentV2> students) {
        this.students = students;
    }

    public List<LivingUnwatchParent> getParents() {
        return parents;
    }

    public void setParents(List<LivingUnwatchParent> parents) {
        this.parents = parents;
    }
}
