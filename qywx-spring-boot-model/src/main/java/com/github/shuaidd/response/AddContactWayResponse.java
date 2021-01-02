package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述 配置客户联系「联系我」方式
 *
 * @author ddshuai
 * @date 2021-01-02 10:04
 **/
public class AddContactWayResponse extends AbstractBaseResponse {

    /**
     * 新增联系方式的配置id
     */
    @JsonProperty("config_id")
    private String configId;

    /**
     * 联系我二维码链接，仅在scene为2时返回
     */
    @JsonProperty("qr_code")
    private String qrCode;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
