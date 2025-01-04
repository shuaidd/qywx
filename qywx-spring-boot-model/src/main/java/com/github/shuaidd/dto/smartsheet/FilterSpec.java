package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FilterSpec {

    @JsonProperty("conjunction")
    private String conjunction;

    @JsonProperty("conditions")
    private List<Condition> conditions;
}
