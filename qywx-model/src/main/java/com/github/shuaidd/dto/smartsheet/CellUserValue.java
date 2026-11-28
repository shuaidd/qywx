package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CellUserValue {

    /**
     * 成员ID
     */
    @JsonProperty("user_id")
    private String userId;
}
