package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 企业微信服务IP信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class ServerIpInfo {
    /**
     * 企业微信使用的IP地址
     **/
    @JsonProperty("ip")
    private String ip;

    /**
     * IP使用的网络协议，如TCP、UDP
     **/
    @JsonProperty("protocol")
    private String protocol;

    /**
     * IP使用的端口号列表
     **/
    @JsonProperty("port")
    private List<Integer> port;

    /**
     * 是否必要IP：0-否，1-是
     **/
    @JsonProperty("is_necessary")
    private Integer isNecessary;

    /**
     * IP涉及功能的描述信息
     **/
    @JsonProperty("description")
    private String description;
}