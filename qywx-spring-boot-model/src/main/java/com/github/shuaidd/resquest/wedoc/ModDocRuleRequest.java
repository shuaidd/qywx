package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.CoAuth;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class ModDocRuleRequest {

    /**
     * 操作的docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 是否允许企业内成员浏览文档, 有值则覆盖
     */
    @JsonProperty("enable_corp_internal")
    private Boolean enableCorpInternal;
    /**
     * 企业内成员主动查看文档后获得的权限类型 1:只读 2:读写（目前仅智能表可设置为读写）, 有值则覆盖
     */
    @JsonProperty("corp_internal_auth")
    private Integer corpInternalAuth;
    /**
     * 是否允许企业外成员浏览文档, 有值则覆盖
     */
    @JsonProperty("enable_corp_external")
    private Boolean enableCorpExternal;
    /**
     * 企业外成员主浏览文档后获得的权限类型 1:只读 2:读写（目前仅智能表可设置为读写）, 有值则覆盖
     */
    @JsonProperty("corp_external_auth")
    private Integer corpExternalAuth;
    /**
     * 企业内成员加入文档是否必须由管理员审批，enable_corp_internal为false时，只能为true，有值则覆盖。设置为true之前，文档需要有至少一个管理员。
     */
    @JsonProperty("corp_internal_approve_only_by_admin")
    private Boolean corpInternalApproveOnlyByAdmin;
    /**
     * 企业外成员加入文档是否必须由管理员审批，enable_corp_external和ban_share_external均为false时，该参数只能为true，有值则覆盖。设置为true之前，文档需要有至少一个管理员。
     */
    @JsonProperty("corp_external_approve_only_by_admin")
    private Boolean corpExternalApproveOnlyByAdmin;
    /**
     * 是否禁止文档分享到企业外, 有值则覆盖
     */
    @JsonProperty("ban_share_external")
    private Boolean banShareExternal;
    /**
     * 是否更新文档查看权限的特定部门, true时更新特定部门列表
     */
    @JsonProperty("update_co_auth_list")
    private Boolean updateCoAuthList;
    /**
     * coAuthList
     */
    @JsonProperty("co_auth_list")
    private List<CoAuth> coAuthList;
}
