package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.SheetPrivInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UpdateSheetPrivRequest {

    /**
     * docid
     */
    @JsonProperty("docid")
    private String docid;
    /**
     * type
     */
    @JsonProperty("type")
    private Integer type;
    /**
     * ruleId
     */
    @JsonProperty("rule_id")
    private Integer ruleId;
    /**
     * name
     */
    @JsonProperty("name")
    private String name;

    @JsonProperty("priv_list")
    private List<SheetPrivInfo> privInfos;
}
