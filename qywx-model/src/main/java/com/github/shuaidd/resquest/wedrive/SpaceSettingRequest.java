package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-02 12:42
 **/
public class SpaceSettingRequest {

    @JsonProperty(value = "userid")
    private String userId;

    @JsonProperty(value = "spaceid")
    private String spaceId;

    /**
     * 本字段仅专业版企业可设置）启用水印。false:关 true:开 ;如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "enable_watermark")
    private Boolean enableWatermark;

    /**
     * 仅管理员可增减空间成员和修改文件分享设置。false:关 true:开 ；如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "add_member_only_admin")
    private Boolean addMemberOnlyAdmin;

    /**
     * 启用成员邀请链接。false:关 true:开 ；如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "enable_share_url")
    private Boolean enableShareUrl;

    /**
     * 通过链接加入空间无需审批。false:关； true:开； 如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "share_url_no_approve")
    private Boolean shareUrlNoApprove;

    /**
     * 邀请链接默认权限。1:仅下载 2:可编辑 4:仅预览 5:可上传下载 200:自定义权限；如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "share_url_no_approve_default_auth")
    private Integer shareUrlNoApproveDefaultAuth;

    /**
     * 是否开启保密模式。false:关 true:开 如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "enable_confidential_mode")
    private Boolean enableConfidentialMode;

    /**
     * 文件默认可查看范围。0:企业内；1:仅成员；2:企业外。如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "default_file_scope")
    private Integer defaultFileScope;

    /**
     * “是否禁止外部联系人加入空间”。false:关 true:开 ;如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "ban_space_add_external_member")
    private Boolean banSpaceAddExternalMember;

    /**
     * 是否禁止文件分享到企业外｜false:关 true:开 如果不填充此字段为保持原有状态
     */
    @JsonProperty(value = "ban_share_external")
    private Boolean banShareExternal;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public Boolean getEnableWatermark() {
        return enableWatermark;
    }

    public void setEnableWatermark(Boolean enableWatermark) {
        this.enableWatermark = enableWatermark;
    }

    public Boolean getAddMemberOnlyAdmin() {
        return addMemberOnlyAdmin;
    }

    public void setAddMemberOnlyAdmin(Boolean addMemberOnlyAdmin) {
        this.addMemberOnlyAdmin = addMemberOnlyAdmin;
    }

    public Boolean getEnableShareUrl() {
        return enableShareUrl;
    }

    public void setEnableShareUrl(Boolean enableShareUrl) {
        this.enableShareUrl = enableShareUrl;
    }

    public Boolean getShareUrlNoApprove() {
        return shareUrlNoApprove;
    }

    public void setShareUrlNoApprove(Boolean shareUrlNoApprove) {
        this.shareUrlNoApprove = shareUrlNoApprove;
    }

    public Integer getShareUrlNoApproveDefaultAuth() {
        return shareUrlNoApproveDefaultAuth;
    }

    public void setShareUrlNoApproveDefaultAuth(Integer shareUrlNoApproveDefaultAuth) {
        this.shareUrlNoApproveDefaultAuth = shareUrlNoApproveDefaultAuth;
    }

    public Boolean getEnableConfidentialMode() {
        return enableConfidentialMode;
    }

    public void setEnableConfidentialMode(Boolean enableConfidentialMode) {
        this.enableConfidentialMode = enableConfidentialMode;
    }

    public Integer getDefaultFileScope() {
        return defaultFileScope;
    }

    public void setDefaultFileScope(Integer defaultFileScope) {
        this.defaultFileScope = defaultFileScope;
    }

    public Boolean getBanSpaceAddExternalMember() {
        return banSpaceAddExternalMember;
    }

    public void setBanSpaceAddExternalMember(Boolean banSpaceAddExternalMember) {
        this.banSpaceAddExternalMember = banSpaceAddExternalMember;
    }

    public Boolean getBanShareExternal() {
        return banShareExternal;
    }

    public void setBanShareExternal(Boolean banShareExternal) {
        this.banShareExternal = banShareExternal;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SpaceSettingRequest.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("spaceId='" + spaceId + "'")
                .add("enableWatermark=" + enableWatermark)
                .add("addMemberOnlyAdmin=" + addMemberOnlyAdmin)
                .add("enableShareUrl=" + enableShareUrl)
                .add("shareUrlNoApprove=" + shareUrlNoApprove)
                .add("shareUrlNoApproveDefaultAuth=" + shareUrlNoApproveDefaultAuth)
                .add("enableConfidentialMode=" + enableConfidentialMode)
                .add("defaultFileScope=" + defaultFileScope)
                .add("banSpaceAddExternalMember=" + banSpaceAddExternalMember)
                .add("banShareExternal=" + banShareExternal)
                .toString();
    }
}
