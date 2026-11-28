package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对外收款联系人信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ExternalContactInfo {

    /**
     * 联系人姓名
     **/
    @JsonProperty("name")
    private String name;

    /**
     * 联系人手机号
     **/
    @JsonProperty("phone")
    private String phone;

    /**
     * 联系地址
     **/
    @JsonProperty("address")
    private String address;
}
