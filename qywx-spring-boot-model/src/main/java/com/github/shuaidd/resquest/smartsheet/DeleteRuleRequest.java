package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DeleteRuleRequest {

    /**
     * docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * ruleIdList
     */
    @JsonProperty("rule_id_list")
    private List<Integer> ruleIdList;
}
