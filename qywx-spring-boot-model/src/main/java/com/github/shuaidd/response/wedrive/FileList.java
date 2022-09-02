package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-02 16:22
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileList {
    private List<FileItem> item;

    public List<FileItem> getItem() {
        return item;
    }

    public void setItem(List<FileItem> item) {
        this.item = item;
    }
}
