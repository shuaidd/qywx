package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-02 17:21
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateFileRequest extends CommonSpaceRequest {

    @JsonProperty("file_type")
    private Integer fileType;

    @JsonProperty("file_name")
    private String fileName;

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
