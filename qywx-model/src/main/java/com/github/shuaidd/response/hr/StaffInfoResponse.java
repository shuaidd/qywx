package com.github.shuaidd.response.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 员工花名册信息响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class StaffInfoResponse extends AbstractBaseResponse {
    /**
     * 获取到的字段信息列表
     **/
    @JsonProperty("field_info")
    private List<StaffFieldValueResult> fieldInfo;
}
