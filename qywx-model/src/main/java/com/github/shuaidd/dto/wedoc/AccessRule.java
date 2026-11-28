package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AccessRule
 */
@NoArgsConstructor
@Data
public class AccessRule {
    /**
     * enableCorpInternal
     */
    @JsonProperty("enable_corp_internal")
    private Boolean enableCorpInternal;
    /**
     * corpInternalAuth
     */
    @JsonProperty("corp_internal_auth")
    private Integer corpInternalAuth;
    /**
     * enableCorpExternal
     */
    @JsonProperty("enable_corp_external")
    private Boolean enableCorpExternal;
    /**
     * corpExternalAuth
     */
    @JsonProperty("corp_external_auth")
    private Integer corpExternalAuth;
    /**
     * corpInternalApproveOnlyByAdmin
     */
    @JsonProperty("corp_internal_approve_only_by_admin")
    private Boolean corpInternalApproveOnlyByAdmin;
    /**
     * corpExternalApproveOnlyByAdmin
     */
    @JsonProperty("corp_external_approve_only_by_admin")
    private Boolean corpExternalApproveOnlyByAdmin;
    /**
     * banShareExternal
     */
    @JsonProperty("ban_share_external")
    private Boolean banShareExternal;
}
