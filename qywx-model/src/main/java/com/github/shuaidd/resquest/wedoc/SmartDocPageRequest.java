package com.github.shuaidd.resquest.wedoc;

import com.github.shuaidd.dto.wedoc.SmartDocPageInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 添加或更新智能文档页面请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocPageRequest {

    /**
     * 智能文档的文档 ID
     */
    @JsonProperty("docid")
    private String docId;

    /**
     * 需要添加或更新的页面信息
     */
    @JsonProperty("info")
    private SmartDocPageInfo info;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public SmartDocPageInfo getInfo() {
        return info;
    }

    public void setInfo(SmartDocPageInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("info", info)
                .toString();
    }
}
