package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表人员或部门范围
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormFillRange {

    /**
     * 企业成员userid列表
     */
    @JsonProperty("userids")
    private List<String> userids;

    /**
     * 部门id列表
     */
    @JsonProperty("departmentids")
    private List<Long> departmentids;

    public List<String> getUserids() {
        return userids;
    }

    public void setUserids(List<String> userids) {
        this.userids = userids;
    }

    public List<Long> getDepartmentids() {
        return departmentids;
    }

    public void setDepartmentids(List<Long> departmentids) {
        this.departmentids = departmentids;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userids", userids)
                .append("departmentids", departmentids)
                .toString();
    }
}
