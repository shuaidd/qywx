package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SortInfo {
    @JsonProperty("field_id")
    private String fieldId;
    @JsonProperty("desc")
    private Boolean desc;
}