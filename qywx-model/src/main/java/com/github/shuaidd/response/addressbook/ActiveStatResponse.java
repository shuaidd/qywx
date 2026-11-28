package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 获取企业活跃成员数
 *
 * @author ddshuai
 * date 2021-11-24 13:10
 **/
public class ActiveStatResponse extends AbstractBaseResponse {

    @JsonProperty("active_cnt")
    private Integer activeCnt;

    public Integer getActiveCnt() {
        return activeCnt;
    }

    public void setActiveCnt(Integer activeCnt) {
        this.activeCnt = activeCnt;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActiveStatResponse.class.getSimpleName() + "[", "]")
                .add("activeCnt=" + activeCnt)
                .toString();
    }
}
