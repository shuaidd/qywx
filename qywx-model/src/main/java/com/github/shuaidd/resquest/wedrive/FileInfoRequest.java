package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-03 05:59
 **/
public class FileInfoRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("fileid")
    private String fileId;

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
}
