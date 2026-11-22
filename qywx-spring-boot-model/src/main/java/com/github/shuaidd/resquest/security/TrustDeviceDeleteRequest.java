package com.github.shuaidd.resquest.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 可信设备删除请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class TrustDeviceDeleteRequest {
    /**
     * 删除设备类型，1-可信企业设备，2-未知设备，3-可信个人设备
     **/
    @JsonProperty("type")
    private Integer type;

    /**
     * 待删除的设备编码列表，单次最多100个
     **/
    @JsonProperty("device_code_list")
    private List<String> deviceCodeList;
}
