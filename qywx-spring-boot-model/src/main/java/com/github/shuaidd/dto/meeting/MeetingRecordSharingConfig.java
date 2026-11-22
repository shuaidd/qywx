package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MeetingRecordSharingConfig
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingRecordSharingConfig {
    /**
     * 是否开启录制共享
     **/
    @JsonProperty("enable_sharing")
    private Boolean enableSharing;

    /**
     * 共享权限类型，0到5
     **/
    @JsonProperty("sharing_auth_type")
    private Integer sharingAuthType;

    /**
     * 是否开启访问密码
     **/
    @JsonProperty("enable_password")
    private Boolean enablePassword;

    /**
     * 共享访问密码
     **/
    @JsonProperty("password")
    private String password;

    /**
     * 是否开启共享链接有效期
     **/
    @JsonProperty("enable_sharing_expire")
    private Boolean enableSharingExpire;

    /**
     * 共享链接有效期，Unix毫秒时间戳
     **/
    @JsonProperty("sharing_expire")
    private Long sharingExpire;

    /**
     * 是否允许下载
     **/
    @JsonProperty("allow_download")
    private Boolean allowDownload;

}
