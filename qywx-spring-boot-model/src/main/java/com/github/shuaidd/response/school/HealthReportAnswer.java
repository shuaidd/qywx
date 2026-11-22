package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 健康上报填写答案
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportAnswer {

    /**
     * id类型。1表示返回企业内部成员的userid，2表示返回家长和学生的userid
     */
    @JsonProperty("id_type")
    private Integer idType;

    /**
     * 企业内部成员的userid，id_type=1时返回
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 学生的userid，id_type=2时返回
     */
    @JsonProperty("student_userid")
    private String studentUserid;

    /**
     * 家长的userid，id_type=2时返回
     */
    @JsonProperty("parent_userid")
    private String parentUserid;

    /**
     * 用户填写时的时间戳
     */
    @JsonProperty("report_time")
    private Long reportTime;

    /**
     * 用户填写的答案列表
     */
    @JsonProperty("report_values")
    private List<HealthReportAnswerValue> reportValues;

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStudentUserid() {
        return studentUserid;
    }

    public void setStudentUserid(String studentUserid) {
        this.studentUserid = studentUserid;
    }

    public String getParentUserid() {
        return parentUserid;
    }

    public void setParentUserid(String parentUserid) {
        this.parentUserid = parentUserid;
    }

    public Long getReportTime() {
        return reportTime;
    }

    public void setReportTime(Long reportTime) {
        this.reportTime = reportTime;
    }

    public List<HealthReportAnswerValue> getReportValues() {
        return reportValues;
    }

    public void setReportValues(List<HealthReportAnswerValue> reportValues) {
        this.reportValues = reportValues;
    }
}
