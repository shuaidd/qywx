package com.github.shuaidd.response;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-06 06:52
 **/
public class CreateDepartmentResponse extends AbstractBaseResponse {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", CreateDepartmentResponse.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }
}
