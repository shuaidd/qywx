package com.github.shuaidd.response.wedoc;

import com.github.shuaidd.dto.wedoc.SmartDocPageInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获取智能文档页面结构响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetSmartDocPageHierarchyResponse extends AbstractBaseResponse {

    /**
     * 页面层级列表
     */
    @JsonProperty("pages")
    private List<SmartDocPageInfo> pages;

    public List<SmartDocPageInfo> getPages() {
        return pages;
    }

    public void setPages(List<SmartDocPageInfo> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pages", pages)
                .toString();
    }
}
