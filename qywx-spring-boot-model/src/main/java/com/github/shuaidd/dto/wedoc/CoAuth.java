package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CoAuthList
 */
@NoArgsConstructor
@Data
public class CoAuth {
    /**
     * type
     */
    @JsonProperty("type")
    private Integer type;
    /**
     * departmentId
     */
    @JsonProperty("departmentid")
    private Integer departmentId;
    /**
     * auth
     */
    @JsonProperty("auth")
    private Integer auth;
}
