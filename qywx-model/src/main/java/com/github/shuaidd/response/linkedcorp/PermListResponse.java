package com.github.shuaidd.response.linkedcorp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-04 21:22
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermListResponse extends AbstractBaseResponse {


    @JsonProperty("userids")
    private List<String> userIds;

    @JsonProperty("department_ids")
    private List<String> departmentIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<String> departmentIds) {
        this.departmentIds = departmentIds;
    }
}
