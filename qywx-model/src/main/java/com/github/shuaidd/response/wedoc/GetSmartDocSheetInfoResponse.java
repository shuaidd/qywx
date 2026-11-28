package com.github.shuaidd.response.wedoc;

import com.github.shuaidd.response.AbstractBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取智能文档数据源响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetSmartDocSheetInfoResponse extends AbstractBaseResponse {

    /**
     * 智能表格文档 ID
     */
    @JsonProperty("ss_docid")
    private String ssDocId;

    public String getSsDocId() {
        return ssDocId;
    }

    public void setSsDocId(String ssDocId) {
        this.ssDocId = ssDocId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ssDocId", ssDocId)
                .toString();
    }
}