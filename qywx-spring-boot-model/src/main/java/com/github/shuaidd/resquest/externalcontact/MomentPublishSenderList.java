package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 企业朋友圈发表任务执行者列表
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishSenderList {

    /**
     * 执行发表任务的成员 userid 列表；最多支持 10 万个
     */
    @JsonProperty("user_list")
    private List<String> userList;

    /**
     * 执行发表任务的部门 ID 列表
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