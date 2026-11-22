package com.github.shuaidd.response.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.security.TrustDeviceImportResult;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 可信企业设备导入响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class TrustDeviceImportResponse extends AbstractBaseResponse {
    /**
     * 每条设备记录的导入结果列表
     **/
    @JsonProperty("result")
    private List<TrustDeviceImportResult> result;
}
