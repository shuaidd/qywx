package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.MemberRange;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ModRuleMemberRequest {

    /**
     * docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * ruleId
     */
    @JsonProperty("rule_id")
    private Integer ruleId;
    /**
     * addMemberRange
     */
    @JsonProperty("add_member_range")
    private MemberRange addMemberRange;
    /**
     * delMemberRange
     */
    @JsonProperty("del_member_range")
    private MemberRange delMemberRange;
}
