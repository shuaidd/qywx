package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 家校部门信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolDepartment {

    /**
     * 部门id，根部门固定为1
     */
    private Integer id;

    /**
     * 部门类型：1班级、2年级、3学段、4校区、5学校根部门
     */
    private Integer type;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父部门id，根部门为0
     */
    @JsonProperty("parentid")
    private Integer parentId;

    /**
     * 入学年份，仅标准年级返回，格式YYYY
     */
    @JsonProperty("register_year")
    private Integer registerYear;

    /**
     * 标准年级参数值，仅标准年级返回
     */
    @JsonProperty("standard_grade")
    private Integer standardGrade;

    /**
     * 在父部门中的次序值，值大的排序靠前
     */
    private Integer order;

    /**
     * 部门管理员列表
     */
    @JsonProperty("department_admins")
    private List<SchoolDepartmentAdmin> departmentAdmins;

    /**
     * 是否开启班级群：1开启，0关闭，仅班级类型返回
     */
    @JsonProperty("open_group_chat")
    private Integer openGroupChat;

    /**
     * 班级群id，仅开启班级群的班级返回
     */
    @JsonProperty("group_chat_id")
    private String groupChatId;

    /**
     * 是否已毕业：1是，0不是，仅年级类型返回
     */
    @JsonProperty("is_graduated")
    private Integer isGraduated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(Integer registerYear) {
        this.registerYear = registerYear;
    }

    public Integer getStandardGrade() {
        return standardGrade;
    }

    public void setStandardGrade(Integer standardGrade) {
        this.standardGrade = standardGrade;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<SchoolDepartmentAdmin> getDepartmentAdmins() {
        return departmentAdmins;
    }

    public void setDepartmentAdmins(List<SchoolDepartmentAdmin> departmentAdmins) {
        this.departmentAdmins = departmentAdmins;
    }

    public Integer getOpenGroupChat() {
        return openGroupChat;
    }

    public void setOpenGroupChat(Integer openGroupChat) {
        this.openGroupChat = openGroupChat;
    }

    public String getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(String groupChatId) {
        this.groupChatId = groupChatId;
    }

    public Integer getIsGraduated() {
        return isGraduated;
    }

    public void setIsGraduated(Integer isGraduated) {
        this.isGraduated = isGraduated;
    }
}
