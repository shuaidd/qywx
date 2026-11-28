package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 13:47
 **/
public class ApplyEventResponse extends AbstractBaseResponse {

    @JsonProperty("sp_no")
    private String spNo;

    public String getSpNo() {
        return spNo;
    }

    public void setSpNo(String spNo) {
        this.spNo = spNo;
    }
}
