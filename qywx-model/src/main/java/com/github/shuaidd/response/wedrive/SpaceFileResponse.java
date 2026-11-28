package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedrive.FileList;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * @author ddshuai
 * date 2022-09-02 16:08
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpaceFileResponse extends AbstractBaseResponse {

    @JsonProperty("next_start")
    private Integer nextStart;

    @JsonProperty("file_list")
    private FileList fileList;

    public Integer getNextStart() {
        return nextStart;
    }

    public void setNextStart(Integer nextStart) {
        this.nextStart = nextStart;
    }

    public FileList getFileList() {
        return fileList;
    }

    public void setFileList(FileList fileList) {
        this.fileList = fileList;
    }
}
