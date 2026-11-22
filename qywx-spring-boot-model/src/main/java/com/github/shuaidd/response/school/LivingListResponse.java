package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 指定老师的直播id列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingListResponse extends AbstractBaseResponse {

    /**
     * 直播id列表
     */
    @JsonProperty("livingid_list")
    private List<String> livingidList;

    public List<String> getLivingidList() {
        return livingidList;
    }

    public void setLivingidList(List<String> livingidList) {
        this.livingidList = livingidList;
    }
}
