package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 企业朋友圈不合法执行者列表
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishInvalidSenderList {

    /**
     * 不存在的成员 userid 或不在应用可见范围内的成员 userid
     */
    @JsonProperty("user_list")
    private List<String> userList;

    /**
     * 不存在或不在应用可见范围内的部门 ID
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