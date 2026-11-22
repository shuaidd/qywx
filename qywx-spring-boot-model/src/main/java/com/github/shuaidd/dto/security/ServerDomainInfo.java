package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 企业微信服务域名信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class ServerDomainInfo {
    /**
     * 企业微信使用的具体域名
     **/
    @JsonProperty("domain")
    private String domain;

    /**
     * 企业微信使用的泛域名，官方响应字段拼写为universal_domian
     **/
    @JsonProperty("universal_domian")
    private String universalDomian;

    /**
     * 域名使用的网络协议，如TCP、UDP
     **/
    @JsonProperty("protocol")
    private String protocol;

    /**
     * 域名使用的端口号列表
     **/
    @JsonProperty("port")
    private List<Integer> port;

    /**
     * 是否必要域名：0-否，1-是
     **/
    @JsonProperty("is_necessary")
    private Integer isNecessary;

    /**
     * 域名涉及功能的描述信息
     **/
    @JsonProperty("description")
    private String description;
}