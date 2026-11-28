package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 删除智能文档数据表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class DeleteSmartDocSheetRequest {

    /**
     * 智能文档的文档 ID
     */
    @JsonProperty("docid")
    private String docId;

    /**
     * 要删除的数据表内容块 ID
     */
    @JsonProperty("block_id")
    private String blockId;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("blockId", blockId)
                .toString();
    }
}