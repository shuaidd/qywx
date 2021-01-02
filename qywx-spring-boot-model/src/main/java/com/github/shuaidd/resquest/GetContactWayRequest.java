package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 15:36
 **/
public class GetContactWayRequest {

    @JsonProperty("config_id")
    private String configId;
}
