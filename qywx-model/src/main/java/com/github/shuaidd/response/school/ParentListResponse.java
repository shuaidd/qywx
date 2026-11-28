package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 家校班级下的家长列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ParentListResponse extends AbstractBaseResponse {

    /**
     * 家长列表
     */
    private List<Parent> parents;

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
}
