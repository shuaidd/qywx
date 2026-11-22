package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 智能文档发布页可见权限
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocPublishAuth {

    /**
     * 可见对象类型：1-成员，2-部门
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 可见成员 userid
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 可见部门 ID
     */
    @JsonProperty("departmentid")
    private Long departmentId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .append("userid", userid)
                .append("departmentId", departmentId)
                .toString();
    }
}