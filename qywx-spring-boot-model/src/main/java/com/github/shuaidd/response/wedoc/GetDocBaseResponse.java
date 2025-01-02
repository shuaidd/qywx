package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.DocBaseInfo;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * @author ddshuai
 */
public class GetDocBaseResponse extends AbstractBaseResponse {

    /**
     * docBaseInfo
     */
    @JsonProperty("doc_base_info")
    private DocBaseInfo docBaseInfo;

    public DocBaseInfo getDocBaseInfo() {
        return docBaseInfo;
    }

    public void setDocBaseInfo(DocBaseInfo docBaseInfo) {
        this.docBaseInfo = docBaseInfo;
    }
}
