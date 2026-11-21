package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.SmartDocBlock;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获取智能文档内容块列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetSmartDocBlockListResponse extends AbstractBaseResponse {

    /**
     * 内容块列表
     */
    @JsonProperty("blocks")
    private List<SmartDocBlock> blocks;

    /**
     * 是否还有更多内容块；true 表示有，false 表示没有；官方返回字符串形式的布尔值
     */
    @JsonProperty("has_more")
    private String blockHasMore;

    /**
     * 下一批内容的起始点
     */
    @JsonProperty("next_start")
    private Integer nextStart;

    public List<SmartDocBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<SmartDocBlock> blocks) {
        this.blocks = blocks;
    }

    public String getBlockHasMore() {
        return blockHasMore;
    }

    public void setBlockHasMore(String blockHasMore) {
        this.blockHasMore = blockHasMore;
    }

    public Integer getNextStart() {
        return nextStart;
    }

    public void setNextStart(Integer nextStart) {
        this.nextStart = nextStart;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("blocks", blocks)
                .append("blockHasMore", blockHasMore)
                .append("nextStart", nextStart)
                .toString();
    }
}