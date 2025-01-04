package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateRuleRequest {

    /**
     * docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * name
     */
    @JsonProperty("name")
    private String name;
}
