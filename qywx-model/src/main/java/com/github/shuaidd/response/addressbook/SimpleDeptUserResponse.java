package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.DeptUser;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-08-31 10:26
 **/
public class SimpleDeptUserResponse extends AbstractBaseResponse {

    @JsonProperty(value = "dept_user")
    private List<DeptUser> deptUsers;

    public List<DeptUser> getDeptUsers() {
        return deptUsers;
    }

    public void setDeptUsers(List<DeptUser> deptUsers) {
        this.deptUsers = deptUsers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleDeptUserResponse.class.getSimpleName() + "[", "]")
                .add("deptUsers=" + deptUsers)
                .toString();
    }
}
