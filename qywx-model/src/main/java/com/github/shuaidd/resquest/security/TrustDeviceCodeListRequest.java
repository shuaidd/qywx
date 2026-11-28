package com.github.shuaidd.resquest.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 可信设备编码批量操作请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class TrustDeviceCodeListRequest {
    /**
     * 待确认或驳回的设备编码列表，单次最多100个
     **/
    @JsonProperty("device_code_list")
    private List<String> deviceCodeList;
}
