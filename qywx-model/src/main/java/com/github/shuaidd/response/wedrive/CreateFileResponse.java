package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-02 17:23
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateFileResponse extends AbstractBaseResponse {


    @JsonProperty("fileid")
    private String fileId;

    private String url;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateFileResponse.class.getSimpleName() + "[", "]")
                .add("fileId='" + fileId + "'")
                .add("url='" + url + "'")
                .toString();
    }
}
