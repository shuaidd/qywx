package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 16:37
 **/
public class ExternalProfile {
    @JsonProperty("external_attr")
    private List<ExtAttr> externalAttr;

    public List<ExtAttr> getExternalAttr() {
        return externalAttr;
    }

    public void setExternalAttr(List<ExtAttr> externalAttr) {
        this.externalAttr = externalAttr;
    }
}
