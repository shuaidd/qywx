package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建对外收款账户申请单响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplyMchResponse extends AbstractBaseResponse {

    /**
     * 返回的具体错误信息
     **/
    @JsonProperty("error_description")
    private String errorDescription;
}
