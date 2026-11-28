package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DocMemberList
 */
@NoArgsConstructor
@Data
public class DocMember {
    /**
     * type
     */
    @JsonProperty("type")
    private Integer type;
    /**
     * userId
     */
    @JsonProperty("userid")
    private String userId;
    /**
     * auth
     */
    @JsonProperty("auth")
    private Integer auth;
    /**
     * tmpExternalUserId
     */
    @JsonProperty("tmp_external_userid")
    private String tmpExternalUserId;
}
