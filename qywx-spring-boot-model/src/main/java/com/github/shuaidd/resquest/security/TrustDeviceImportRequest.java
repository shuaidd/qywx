package com.github.shuaidd.resquest.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 可信企业设备导入请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class TrustDeviceImportRequest {
    /**
     * 待导入的设备列表，单次最多100条
     **/
    @JsonProperty("device_list")
    private List<TrustDeviceImportItem> deviceList;
}
