package com.github.shuaidd.resquest.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 可信企业设备导入项
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class TrustDeviceImportItem {
    /**
     * 设备的系统类型，仅支持Windows或Mac
     **/
    @JsonProperty("system")
    private String system;

    /**
     * 设备MAC地址列表，Windows必填，Mac选填，每个设备最多100个
     **/
    @JsonProperty("mac_addr")
    private List<String> macAddr;

    /**
     * Windows设备主板UUID
     **/
    @JsonProperty("motherboard_uuid")
    private String motherboardUuid;

    /**
     * Windows设备硬盘序列号列表，每个设备最多100个
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
     * Mac设备序列号，Mac设备必填
     **/
    @JsonProperty("seq_no")
    private String seqNo;
}
