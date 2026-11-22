package com.github.shuaidd.response.wedoc;

import com.github.shuaidd.dto.wedoc.SmartDocSheetInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 添加或更新智能文档数据表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocSheetResponse extends AbstractBaseResponse {

    /**
     * 添加或更新后的数据表信息
     */
    @JsonProperty("info")
    private SmartDocSheetInfo info;

    public SmartDocSheetInfo getInfo() {
        return info;
    }

    public void setInfo(SmartDocSheetInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("info", info)
                .toString();
    }
}