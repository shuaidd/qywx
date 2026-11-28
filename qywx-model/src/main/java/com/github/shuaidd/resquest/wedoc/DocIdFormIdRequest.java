package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 */
public class DocIdFormIdRequest {


    /**
     * 文档docid（docid、formid只能填其中一个），仅可删除应用自己创建的文档
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 收集表id（docid、formid只能填其中一个），仅可删除应用自己创建的收集表
     */
    @JsonProperty("formid")
    private String formId;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
