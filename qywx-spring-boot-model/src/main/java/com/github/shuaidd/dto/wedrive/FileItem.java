package com.github.shuaidd.dto.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;

import java.util.Date;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-02 16:22
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileItem {

    @JsonProperty("fileid")
    private String fileId;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("spaceid")
    private String spaceId;

    @JsonProperty("fatherid")
    private String fatherId;

    @JsonProperty("file_size")
    private int fileSize;

    @JsonDeserialize(using = Long2DateDeserializer.class)
    private Date ctime;

    @JsonDeserialize(using = Long2DateDeserializer.class)
    private Date mtime;

    @JsonProperty("file_type")
    private int fileType;

    @JsonProperty("file_status")
    private int fileStatus;

    @JsonProperty("create_userid")
    private String createUserId;

    @JsonProperty("update_userid")
    private String updateUserId;

    private String sha;

    private String md5;

    private String url;


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FileItem.class.getSimpleName() + "[", "]")
                .add("fileId='" + fileId + "'")
                .add("fileName='" + fileName + "'")
                .add("spaceId='" + spaceId + "'")
                .add("fatherId='" + fatherId + "'")
                .add("fileSize=" + fileSize)
                .add("ctime='" + ctime + "'")
                .add("mtime='" + mtime + "'")
                .add("fileType=" + fileType)
                .add("fileStatus=" + fileStatus)
                .add("createUserId='" + createUserId + "'")
                .add("updateUserId='" + updateUserId + "'")
                .add("sha='" + sha + "'")
                .add("md5='" + md5 + "'")
                .add("url='" + url + "'")
                .toString();
    }
}
