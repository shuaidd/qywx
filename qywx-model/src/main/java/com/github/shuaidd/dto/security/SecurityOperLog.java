package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 安全管理操作记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class SecurityOperLog {
    /**
     * 操作时间，Unix秒级时间戳
     **/
    @JsonProperty("time")
    private Long time;

    /**
     * 操作者userid
     **/
    @JsonProperty("userid")
    private String userid;

    /**
     * 操作类型
     **/
    @JsonProperty("oper_type")
    private Integer operType;

    /**
     * 操作相关数据描述
     **/
    @JsonProperty("detail_info")
    private String detailInfo;

    /**
     * 操作者IP地址
     **/
    @JsonProperty("ip")
    private String ip;
}