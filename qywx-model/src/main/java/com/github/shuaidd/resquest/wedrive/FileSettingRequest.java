package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-03 06:19
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileSettingRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("fileid")
    private String fileId;

    /**
     * 权限范围：1:指定人 2:企业内 3:企业外
     */
    @JsonProperty("auth_scope")
    private Integer authScope;

    /**
     * 权限信息
     * 普通文档： 1:仅浏览（可下载) 4:仅预览（仅专业版企业可设置）；如果不填充此字段为保持原有状态
     * 微文档： 1:仅浏览（可下载） 2:可编辑；如果不填充此字段为保持原有状态
     */
    private Integer auth;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getAuthScope() {
        return authScope;
    }

    public void setAuthScope(Integer authScope) {
        this.authScope = authScope;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }
}
