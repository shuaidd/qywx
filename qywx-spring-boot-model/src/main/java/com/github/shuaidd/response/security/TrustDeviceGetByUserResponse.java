package com.github.shuaidd.response.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.security.TrustDeviceInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 成员使用设备列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class TrustDeviceGetByUserResponse extends AbstractBaseResponse {
    /**
     * 成员使用的设备信息列表
     **/
    @JsonProperty("device_list")
    private List<TrustDeviceInfo> deviceList;
}
