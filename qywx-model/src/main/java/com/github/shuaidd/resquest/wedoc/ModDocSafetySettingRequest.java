package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.Watermark;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class ModDocSafetySettingRequest {

    /**
     * 操作的文档id
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 是否允许只读成员复制、下载文档，有值则覆盖
     */
    @JsonProperty("enable_readonly_copy")
    private Boolean enableReadonlyCopy;
    /**
     * 水印设置
     */
    @JsonProperty("watermark")
    private Watermark watermark;
}
