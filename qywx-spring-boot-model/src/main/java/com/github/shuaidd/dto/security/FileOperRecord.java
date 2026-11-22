package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 文件操作记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class FileOperRecord {
    /**
     * 操作时间，Unix秒级时间戳
     **/
    @JsonProperty("time")
    private Long time;

    /**
     * 企业内部操作者userid，操作者为企业内部用户时返回
     **/
    @JsonProperty("userid")
    private String userid;

    /**
     * 企业外部操作者信息，操作者为企业外部用户时返回
     **/
    @JsonProperty("external_user")
    private SecurityExternalUser externalUser;

    /**
     * 文件操作类型信息
     **/
    @JsonProperty("operation")
    private SecurityOperation operation;

    /**
     * 文件操作说明
     **/
    @JsonProperty("file_info")
    private String fileInfo;

    /**
     * 文件大小，单位字节
     **/
    @JsonProperty("file_size")
    private Integer fileSize;

    /**
     * 文件MD5值
     **/
    @JsonProperty("file_md5")
    private String fileMd5;

    /**
     * 下载申请人名称，对应操作类型返回
     **/
    @JsonProperty("applicant_name")
    private String applicantName;

    /**
     * 设备类型，1-企业可信设备，2-个人可信设备
     **/
    @JsonProperty("device_type")
    private Integer deviceType;

    /**
     * 设备编码，对应下载操作返回
     **/
    @JsonProperty("device_code")
    private String deviceCode;
}
