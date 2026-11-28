package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.SheetRule;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetSheetPrivResponse extends AbstractBaseResponse {

    @JsonProperty("rule_list")
    private List<SheetRule> rules;
}
