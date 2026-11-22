package com.github.shuaidd.dto.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 花名册文件字段值
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class HrFileValue {
    /**
     * 可通过获取临时素材接口下载的media_id列表
     **/
    @JsonProperty("media_id")
    private List<String> mediaId;
}
