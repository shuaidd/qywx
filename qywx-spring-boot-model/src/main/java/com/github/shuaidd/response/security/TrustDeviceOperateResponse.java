package com.github.shuaidd.response.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 可信设备批量确认或驳回响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class TrustDeviceOperateResponse extends AbstractBaseResponse {
    /**
     * 操作成功的设备编码列表
     **/
    @JsonProperty("success_list")
    private List<String> successList;

    /**
     * 操作失败的设备编码列表
     **/
    @JsonProperty("fail_list")
    private List<String> failList;
}
