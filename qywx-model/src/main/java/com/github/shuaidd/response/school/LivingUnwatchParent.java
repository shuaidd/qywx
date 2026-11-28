package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 未观看直播的家长统计（v2协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingUnwatchParent {

    /**
     * 家长的userid
     */
    @JsonProperty("parent_userid")
    private String parentUserid;

    /**
     * 家长对应学生的userid
     */
    @JsonProperty("student_userid")
    private String studentUserid;

    /**
     * 家长对应学生所在的班级id列表
     */
    @JsonProperty("partyids")
    private List<Integer> partyids;

    public String getParentUserid() {
        return parentUserid;
    }

    public void setParentUserid(String parentUserid) {
        this.parentUserid = parentUserid;
    }

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
