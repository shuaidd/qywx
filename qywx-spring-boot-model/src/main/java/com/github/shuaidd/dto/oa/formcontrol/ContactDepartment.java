package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 17:22
 **/
public class ContactDepartment {

    @JsonProperty("openapi_id")
    private Integer openApiId;

    private String name;

    public Integer getOpenApiId() {
        return openApiId;
    }

    public void setOpenApiId(Integer openApiId) {
        this.openApiId = openApiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContactDepartment.class.getSimpleName() + "[", "]")
                .add("openApiId=" + openApiId)
                .add("name='" + name + "'")
                .toString();
    }
}
