package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 申请小程序收款交易账单响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MiniBillResponse extends AbstractBaseResponse {

    /**
     * 账单下载地址，30 秒内有效
     **/
    @JsonProperty("download_url")
    private String downloadUrl;

    /**
     * 账单摘要哈希类型
     **/
    @JsonProperty("hash_type")
    private String hashType;

    /**
     * 账单摘要值
     **/
    @JsonProperty("hash_value")
    private String hashValue;

    /**
     * 下载账单使用的 HTTP 校验头
     **/
    @JsonProperty("auth")
    private String auth;
}
