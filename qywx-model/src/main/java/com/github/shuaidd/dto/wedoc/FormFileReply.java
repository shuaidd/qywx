package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表文件答案
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormFileReply {

    /**
     * 文件名
     */
    @JsonProperty("name")
    private String name;

    /**
     * 文件id
     */
    @JsonProperty("fileid")
    private String fileId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("fileId", fileId)
                .toString();
    }
}
