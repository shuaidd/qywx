package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 小程序收款下单场景信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class OrderSceneInfo {

    /**
     * 用户终端 IP
     **/
    @JsonProperty("payer_client_ip")
    private String payerClientIp;

    /**
     * 商户端设备号
     **/
    @JsonProperty("device_id")
    private String deviceId;

    /**
     * 门店信息
     **/
    @JsonProperty("store_info")
    private OrderStoreInfo storeInfo;
}
