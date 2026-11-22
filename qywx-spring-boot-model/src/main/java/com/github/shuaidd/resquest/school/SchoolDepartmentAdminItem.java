package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 家校部门管理员请求明细
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolDepartmentAdminItem {

    /**
     * 管理端账号，企业内必须唯一
     */
    private String userid;

    /**
     * 部门管理员类型：1校区负责人、2年级负责人、3班主任、4任课老师、5学段负责人
     */
    private Integer type;

    /**
     * 教师科目，仅班主任和任课老师可设置，最多15个字符
     */
    private String subject;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
