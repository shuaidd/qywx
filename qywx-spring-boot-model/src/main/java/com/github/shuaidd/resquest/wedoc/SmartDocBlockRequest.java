package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.SmartDocBlock;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 添加或更新智能文档内容块请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocBlockRequest {

    /**
     * 智能文档的文档 ID
     */
    @JsonProperty("docid")
    private String docId;

    /**
     * 页面 ID
     */
    @JsonProperty("page_id")
    private String pageId;

    /**
     * 需要添加或更新的内容块列表
     */
    @JsonProperty("blocks")
    private List<SmartDocBlock> blocks;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public List<SmartDocBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<SmartDocBlock> blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("pageId", pageId)
                .append("blocks", blocks)
                .toString();
    }
}