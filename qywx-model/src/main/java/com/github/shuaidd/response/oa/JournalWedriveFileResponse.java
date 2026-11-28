package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 汇报下载微盘文件响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class JournalWedriveFileResponse extends AbstractBaseResponse {
    /**
     * 下载请求url，有效期2个小时
     **/
    @JsonProperty("download_url")
    private String downloadUrl;

    /**
     * 下载请求带cookie的key
     **/
    @JsonProperty("cookie_name")
    private String cookieName;

    /**
     * 下载请求带cookie的value
     **/
    @JsonProperty("cookie_value")
    private String cookieValue;
}