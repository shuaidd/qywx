package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * @author ddshuai
 */
public class DocumentContentResponse extends AbstractBaseResponse {

    /**
     * version
     */
    @JsonProperty("version")
    private Integer version;
    /**
     * document
     */
    @JsonProperty("document")
    private JsonNode document;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public JsonNode getDocument() {
        return document;
    }

    public void setDocument(JsonNode document) {
        this.document = document;
    }
}
