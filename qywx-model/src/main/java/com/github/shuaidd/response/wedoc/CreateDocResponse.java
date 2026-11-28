package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * @author ddshuai
 */
public class CreateDocResponse extends AbstractBaseResponse {

    /**
     * 新建文档的访问链接
     */
    @JsonProperty("url")
    private String url;
    /**
     * 新建文档的docid
     */
    @JsonProperty("docid")
    private String docId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
}
