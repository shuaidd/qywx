package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商户申请经营场景证明
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplyMchSalesSceneInfo {

    /**
     * 经营场景：1 线下门店，2 线上店铺
     **/
    @JsonProperty("type")
    private Integer type;

    /**
     * 线上店铺地址
     **/
    @JsonProperty("store_url")
    private String storeUrl;

    /**
     * 线上店铺二维码照片 ID
     **/
    @JsonProperty("store_pic_open_wx_pay_media_id")
    private String storePicMediaId;

    /**
     * 线下门店省市区编码
     **/
    @JsonProperty("address_code")
    private String addressCode;

    /**
     * 线下门店详细地址
     **/
    @JsonProperty("offline_address")
    private String offlineAddress;

    /**
     * 线下门店门头照片 ID
     **/
    @JsonProperty("entrance_pic_open_wx_pay_media_id")
    private String entrancePicMediaId;

    /**
     * 线下门店内部照片 ID
     **/
    @JsonProperty("indoor_pic_open_wx_pay_media_id")
    private String indoorPicMediaId;
}
