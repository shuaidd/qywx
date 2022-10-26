package com.github.shuaidd.resquest.media;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadByUrlRequest {

    /**
     * scene
     */
    @JsonProperty("scene")
    private Integer scene;
    /**
     * type
     */
    @JsonProperty("type")
    private String type;
    /**
     * fileName
     */
    @JsonProperty("filename")
    private String fileName;
    /**
     * url
     */
    @JsonProperty("url")
    private String url;
    /**
     * md5
     */
    @JsonProperty("md5")
    private String md5;

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
