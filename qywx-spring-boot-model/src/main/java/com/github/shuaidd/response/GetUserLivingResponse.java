package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-12 13:23
 **/
public class GetUserLivingResponse extends AbstractBaseResponse {
    @JsonProperty("livingid_list")
    private List<String> livingIdList;

    public List<String> getLivingIdList() {
        return livingIdList;
    }

    public void setLivingIdList(List<String> livingIdList) {
        this.livingIdList = livingIdList;
    }
}
