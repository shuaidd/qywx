package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 更新家校部门的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UpdateSchoolDepartmentRequest {

    /**
     * 待更新的部门id，必须大于0
     */
    private Integer id;

    /**
     * 新部门名称；标准年级部门忽略该字段
     */
    private String name;

    /**
     * 父部门id
     */
    @JsonProperty("parentid")
    private Integer parentId;

    /**
     * 修改后的新部门id
     */
    @JsonProperty("new_id")
    private Integer newId;

    /**
     * 入学年份，格式YYYY，范围1970~2100，仅部门类型为年级时生效
     */
    @JsonProperty("register_year")
    private Integer registerYear;

    /**
     * 标准年级参数值，仅部门类型为年级时生效
     */
    @JsonProperty("standard_grade")
    private Integer standardGrade;

    /**
     * 在父部门中的次序值，值大的排序靠前
     */
    private Integer order;

    /**
     * 部门管理员变更列表
     */
    @JsonProperty("department_admins")
    private List<SchoolDepartmentUpdateAdminItem> departmentAdmins;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
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

    public List<SchoolDepartmentUpdateAdminItem> getDepartmentAdmins() {
        return departmentAdmins;
    }

    public void setDepartmentAdmins(List<SchoolDepartmentUpdateAdminItem> departmentAdmins) {
        this.departmentAdmins = departmentAdmins;
    }
}
