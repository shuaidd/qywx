package com.github.shuaidd.response.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 员工字段配置响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class StaffFieldConfigResponse extends AbstractBaseResponse {
    /**
     * 字段组配置信息列表
     **/
    @JsonProperty("group_list")
    private List<HrFieldGroup> groupList;
}
