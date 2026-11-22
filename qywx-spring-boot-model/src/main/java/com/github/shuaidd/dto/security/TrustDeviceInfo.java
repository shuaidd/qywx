package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 可信设备信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class TrustDeviceInfo {
    /**
     * 设备唯一编码
     **/
    @JsonProperty("device_code")
    private String deviceCode;

    /**
     * 设备系统类型，Windows或Mac
     **/
    @JsonProperty("system")
    private String system;

    /**
     * 设备MAC地址列表
     **/
    @JsonProperty("mac_addr")
    private List<String> macAddr;

    /**
     * Windows设备主板UUID
     **/
    @JsonProperty("motherboard_uuid")
    private String motherboardUuid;

    /**
     * Windows设备硬盘UUID列表
     **/
    @JsonProperty("harddisk_uuid")
    private List<String> harddiskUuid;

    /**
     * Windows域名
     **/
    @JsonProperty("domain")
    private String domain;

    /**
     * Windows计算机名
     **/
    @JsonProperty("pc_name")
    private String pcName;

    /**
     * Mac设备序列号
     **/
    @JsonProperty("seq_no")
    private String seqNo;

    /**
     * 设备最后登录时间，Unix秒级时间戳
     **/
    @JsonProperty("last_login_time")
    private Long lastLoginTime;

    /**
     * 设备最后登录成员userid
     **/
    @JsonProperty("last_login_userid")
    private String lastLoginUserid;

    /**
     * 设备归属或确认时间，Unix秒级时间戳
     **/
    @JsonProperty("confirm_timestamp")
    private Long confirmTimestamp;

    /**
     * 设备归属或确认成员userid
     **/
    @JsonProperty("confirm_userid")
    private String confirmUserid;

    /**
     * 通过设备申报的管理员userid
     **/
    @JsonProperty("approved_userid")
    private String approvedUserid;

    /**
     * 设备来源，0-未知，1-成员确认，2-管理员导入，3-成员自主申报
     **/
    @JsonProperty("source")
    private Integer source;

    /**
     * 设备状态，1-已导入未登录，2-待邀请，3-待管理员确认为企业设备，4-待管理员确认为个人设备，5-已确认为可信企业设备，6-已确认为可信个人设备
     **/
    @JsonProperty("status")
    private Integer status;
}
