package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 资金流水规则组
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class FundFlowGroup {

    /**
     * 资金流水所属规则组名称
     **/
    @JsonProperty("group_name")
    private String groupName;
}
