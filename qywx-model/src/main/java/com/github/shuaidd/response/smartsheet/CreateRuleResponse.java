package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CreateRuleResponse extends AbstractBaseResponse {

    /**
     * ruleId
     */
    @JsonProperty("rule_id")
    private Integer ruleId;
}
