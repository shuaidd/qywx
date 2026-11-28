package com.github.shuaidd.dto.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-08-31 12:54
 **/
public class MemberRange {

    /**
     * 专员userid列表
     */
    @JsonProperty(value = "userid_list")
    private List<String> userIds;

    /**
     * 专员部门列表
     */
    @JsonProperty(value = "department_id_list")
    private List<Integer> departmentIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MemberRange.class.getSimpleName() + "[", "]")
                .add("userIds=" + userIds)
                .add("departmentIds=" + departmentIds)
                .toString();
    }
}
