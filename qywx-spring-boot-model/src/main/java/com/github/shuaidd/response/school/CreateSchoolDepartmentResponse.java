package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 创建家校部门的响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class CreateSchoolDepartmentResponse extends com.github.shuaidd.response.BaseResponse {

    /**
     * 创建成功的部门id
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
