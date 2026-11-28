package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 创建家校部门的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class CreateSchoolDepartmentRequest {

    /**
     * 部门名称，长度1~32个字符；设置入学年份和标准年级时忽略
     */
    private String name;

    /**
     * 父部门id
     */
    @JsonProperty("parentid")
    private Integer parentId;

    /**
     * 部门id，不填则自动生成；指定时必须大于1
     */
    private Integer id;

    /**
     * 部门类型：1班级、2年级、3学段、4校区
     */
    private Integer type;

    /**
     * 标准年级参数值，仅部门类型为年级时生效
     */
    @JsonProperty("standard_grade")
    private Integer standardGrade;

    /**
     * 入学年份，格式YYYY，范围1970~2100，仅部门类型为年级时生效
     */
    @JsonProperty("register_year")
    private Integer registerYear;

    /**
     * 在父部门中的次序值，值大的排序靠前
     */
    private Integer order;

    /**
     * 部门管理员列表
     */
    @JsonProperty("department_admins")
    private List<SchoolDepartmentAdminItem> departmentAdmins;

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

    public Integer getStandardGrade() {
        return standardGrade;
    }

    public void setStandardGrade(Integer standardGrade) {
        this.standardGrade = standardGrade;
    }

    public Integer getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(Integer registerYear) {
        this.registerYear = registerYear;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<SchoolDepartmentAdminItem> getDepartmentAdmins() {
        return departmentAdmins;
    }

    public void setDepartmentAdmins(List<SchoolDepartmentAdminItem> departmentAdmins) {
        this.departmentAdmins = departmentAdmins;
    }
}
