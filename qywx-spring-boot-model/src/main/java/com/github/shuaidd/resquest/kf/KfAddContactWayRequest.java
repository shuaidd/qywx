package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-11-24 17:28
 **/
public class KfAddContactWayRequest {

    /**
     * 场景值，字符串类型，由开发者自定义。
     * 不多于32字节
     * 字符串取值范围(正则表达式)：[0-9a-zA-Z_-]*
     */
    private String scene;

    @JsonProperty("open_kfid")
    private String openKfId;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getOpenKfId() {
        return openKfId;
    }

    public void setOpenKfId(String openKfId) {
        this.openKfId = openKfId;
    }
}
