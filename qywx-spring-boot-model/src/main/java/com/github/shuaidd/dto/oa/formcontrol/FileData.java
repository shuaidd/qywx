package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 17:25
 **/
public class FileData {

    @JsonProperty("file_id")
    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FileData.class.getSimpleName() + "[", "]")
                .add("fileId='" + fileId + "'")
                .toString();
    }
}
