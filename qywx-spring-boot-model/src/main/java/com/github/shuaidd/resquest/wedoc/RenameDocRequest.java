package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 */
public class RenameDocRequest {

    /**
     * 文档docid（docid、formid只能填其中一个） ，仅可修改应用自己创建的文档
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 收集表id（docid、formid只能填其中一个） ，仅可修改应用自己创建的收集表
     */
    @JsonProperty("formid")
    private String formId;
    /**
     * 重命名后的文档名 （注意：文档名最多填255个字符, 英文算1个, 汉字算2个, 超过255个字符会被截断）
     */
    @JsonProperty("new_name")
    private String newName;

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

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
