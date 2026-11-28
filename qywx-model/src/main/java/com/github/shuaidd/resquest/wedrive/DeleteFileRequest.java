package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-02 22:21
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteFileRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("fileid")
    private List<String> fileId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getFileId() {
        return fileId;
    }

    public void setFileId(List<String> fileId) {
        this.fileId = fileId;
    }
}
