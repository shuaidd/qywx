package com.github.shuaidd.response.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.security.ServerDomainInfo;
import com.github.shuaidd.dto.security.ServerIpInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业微信域名IP信息响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerDomainIpResponse extends AbstractBaseResponse {
    /**
     * 企业微信域名信息列表
     **/
    @JsonProperty("domain_list")
    private List<ServerDomainInfo> domainList;

    /**
     * 企业微信IP信息列表
     **/
    @JsonProperty("ip_list")
    private List<ServerIpInfo> ipList;
}