package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 智能文档数据表信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocSheetInfo {

    /**
     * 数据表内容块 ID
     */
    @JsonProperty("block_id")
    private String blockId;

    /**
     * 数据表标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 数据表 ID
     */
    @JsonProperty("sheet_id")
    private String sheetId;

    /**
     * 智能表格文档 ID
     */
    @JsonProperty("ss_docid")
    private String ssDocId;

    /**
     * 排序位置，表示排在哪个内容块之后
     */
    @JsonProperty("after_id")
    private String afterId;

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getSsDocId() {
        return ssDocId;
    }

    public void setSsDocId(String ssDocId) {
        this.ssDocId = ssDocId;
    }

    public String getAfterId() {
        return afterId;
    }

    public void setAfterId(String afterId) {
        this.afterId = afterId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("blockId", blockId)
                .append("title", title)
                .append("sheetId", sheetId)
                .append("ssDocId", ssDocId)
                .append("afterId", afterId)
                .toString();
    }
}