package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 未观看直播的学生统计（v2协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingUnwatchStudentV2 {

    /**
     * 学生的userid
     */
    @JsonProperty("student_userid")
    private String studentUserid;

    /**
     * 学生所在的班级id列表
     */
    @JsonProperty("partyids")
    private List<Integer> partyids;

    public String getStudentUserid() {
        return studentUserid;
    }

    public void setStudentUserid(String studentUserid) {
        this.studentUserid = studentUserid;
    }

    public List<Integer> getPartyids() {
        return partyids;
    }

    public void setPartyids(List<Integer> partyids) {
        this.partyids = partyids;
    }
}
