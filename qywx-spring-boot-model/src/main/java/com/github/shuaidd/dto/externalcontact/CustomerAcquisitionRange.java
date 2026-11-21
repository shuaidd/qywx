package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获客链接可使用的成员和部门范围
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionRange {

    /**
     * 可使用获客链接的企业成员 userid 列表
     */
    @JsonProperty("user_list")
    private List<String> userList;

    /**
     * 可使用获客链接的企业部门 ID 列表；部门下的成员继承该使用权限
     */
    @JsonProperty("department_list")
    private List<Integer> departmentList;

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public List<Integer> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Integer> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userList", userList)
                .append("departmentList", departmentList)
                .toString();
    }
}