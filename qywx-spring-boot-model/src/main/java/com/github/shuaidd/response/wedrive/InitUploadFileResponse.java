package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-03 07:30
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class InitUploadFileResponse {

    @JsonProperty("hit_exist")
    private Boolean hitExist;

    @JsonProperty("upload_key")
    private String uploadKey;

    @JsonProperty("fileid")
    private String fileId;

    public Boolean isHitExist() {
        return hitExist;
    }

    public void setHitExist(Boolean hitExist) {
        this.hitExist = hitExist;
    }

    public String getUploadKey() {
        return uploadKey;
    }

    public void setUploadKey(String uploadKey) {
        this.uploadKey = uploadKey;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", InitUploadFileResponse.class.getSimpleName() + "[", "]")
                .add("hitExist=" + hitExist)
                .add("uploadKey='" + uploadKey + "'")
                .add("fileId='" + fileId + "'")
                .toString();
    }
}
