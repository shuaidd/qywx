package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-03 07:36
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileUploadPartRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("upload_key")
    private String uploadKey;

    private int index;

    @JsonProperty("file_base64_content")
    private String fileBase64Content;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUploadKey() {
        return uploadKey;
    }

    public void setUploadKey(String uploadKey) {
        this.uploadKey = uploadKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFileBase64Content() {
        return fileBase64Content;
    }

    public void setFileBase64Content(String fileBase64Content) {
        this.fileBase64Content = fileBase64Content;
    }
}
