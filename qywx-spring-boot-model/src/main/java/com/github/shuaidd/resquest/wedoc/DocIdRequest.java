package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 */
public class DocIdRequest {

    /**
     * 文档docid
     */
    @JsonProperty("docid")
    private String docId;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
}
