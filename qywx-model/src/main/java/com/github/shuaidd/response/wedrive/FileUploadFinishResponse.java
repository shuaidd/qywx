package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-03 07:41
 **/
public class FileUploadFinishResponse extends AbstractBaseResponse {

    @JsonProperty("fileid")
    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FileUploadFinishResponse.class.getSimpleName() + "[", "]")
                .add("fileId='" + fileId + "'")
                .toString();
    }
}
