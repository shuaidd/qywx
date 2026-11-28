package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 可信企业设备导入结果
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class TrustDeviceImportResult {
    /**
     * 导入设备记录标识，对应请求中设备顺序，从1开始
     **/
    @JsonProperty("device_index")
    private Integer deviceIndex;

    /**
     * 导入成功时返回的设备唯一编码
     **/
    @JsonProperty("device_code")
    private String deviceCode;

    /**
     * 重复导入时与当前记录冲突的设备编码
     **/
    @JsonProperty("duplicated_device_code")
    private String duplicatedDeviceCode;

    /**
     * 导入结果，1-成功，2-重复导入，3-不支持的设备，4-数据格式错误
     **/
    @JsonProperty("status")
    private Integer status;
}
