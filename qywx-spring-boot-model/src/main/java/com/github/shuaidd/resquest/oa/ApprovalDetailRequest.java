package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-19 15:29
 **/
public class ApprovalDetailRequest {

    @JsonProperty("sp_no")
    private String spNo;

    public String getSpNo() {
        return spNo;
    }

    public void setSpNo(String spNo) {
        this.spNo = spNo;
    }
}
