package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 小程序收款门店信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class OrderStoreInfo {

    /**
     * 商户侧门店编号
     **/
    @JsonProperty("id")
    private String id;

    /**
     * 门店名称
     **/
    @JsonProperty("name")
    private String name;

    /**
     * 门店地区编码
     **/
    @JsonProperty("area_code")
    private String areaCode;

    /**
     * 门店详细地址
     **/
    @JsonProperty("address")
    private String address;
}
