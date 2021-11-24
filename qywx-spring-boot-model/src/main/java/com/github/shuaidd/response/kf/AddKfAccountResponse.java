package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 添加客服账号
 *
 * @author ddshuai
 * @date 2021-11-24 16:14
 **/
public class AddKfAccountResponse extends AbstractBaseResponse {

    @JsonProperty("open_kfid")
    private String openKfId;

    public String getOpenKfId() {
        return openKfId;
    }

    public void setOpenKfId(String openKfId) {
        this.openKfId = openKfId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AddKfAccountResponse.class.getSimpleName() + "[", "]")
                .add("openKfId='" + openKfId + "'")
                .toString();
    }
}
