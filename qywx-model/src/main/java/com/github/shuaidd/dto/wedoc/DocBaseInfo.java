package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocBaseInfo {
    /**
     * 文档docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 文档名字
     */
    @JsonProperty("doc_name")
    private String docName;
    /**
     * 文档创建时间
     */
    @JsonProperty("create_time")
    private Integer createTime;
    /**
     * 文档最后修改时间
     */
    @JsonProperty("modify_time")
    private Integer modifyTime;
    /**
     * 3: 文档 4: 表格 10:智能表格
     */
    @JsonProperty("doc_type")
    private Integer docType;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDocType() {
        return docType;
    }

    public void setDocType(Integer docType) {
        this.docType = docType;
    }
}
