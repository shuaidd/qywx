package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-02 22:14
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveFileRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("fatherid")
    private String fatherId;

    private boolean replace;

    @JsonProperty("fileid")
    private List<String> fileId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public boolean isReplace() {
        return replace;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }

    public List<String> getFileId() {
        return fileId;
    }

    public void setFileId(List<String> fileId) {
        this.fileId = fileId;
    }
}
