package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.*;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ddshuai
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class DocAuthResponse extends AbstractBaseResponse {

    /**
     * accessRule
     */
    @JsonProperty("access_rule")
    private AccessRule accessRule;
    /**
     * secureSetting
     */
    @JsonProperty("secure_setting")
    private SecureSetting secureSetting;
    /**
     * docMemberList
     */
    @JsonProperty("doc_member_list")
    private List<DocMember> docMemberList;
    /**
     * coAuthList
     */
    @JsonProperty("co_auth_list")
    private List<CoAuth> coAuthList;
    /**
     * watermark
     */
    @JsonProperty("watermark")
    private Watermark watermark;
}
