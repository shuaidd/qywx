package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对外收款关联小程序信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ExternalMiniprogramInfo {

    /**
     * 小程序 AppID
     **/
    @JsonProperty("appid")
    private String appId;

    /**
     * 小程序名称
     **/
    @JsonProperty("name")
    private String name;
}
