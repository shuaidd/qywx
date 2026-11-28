package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedrive.AuthItem;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-03 06:12
 **/
public class FileAclRequest {

    @JsonProperty(value = "userid")
    private String userId;

    @JsonProperty(value = "fileid")
    private String fileId;

    @JsonProperty(value = "auth_info")
    private List<AuthItem> authInfo;

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

    public List<AuthItem> getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(List<AuthItem> authInfo) {
        this.authInfo = authInfo;
    }
}
