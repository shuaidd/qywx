package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.SmartDocBlock;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 添加或更新智能文档内容块响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocBlockResponse extends AbstractBaseResponse {

    /**
     * 添加或更新成功后的内容块列表
     */
    @JsonProperty("blocks")
    private List<SmartDocBlock> blocks;

    public List<SmartDocBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<SmartDocBlock> blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("blocks", blocks)
                .toString();
    }
}