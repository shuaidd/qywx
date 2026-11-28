package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms会议室PMI信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsPmiInfo {
    /**
     * Rooms会议室专属ID
     **/
    @JsonProperty("pmi_code")
    private String pmiCode;
    /**
     * 入会密码，base64编码
     **/
    @JsonProperty("pmi_pwd")
    private String pmiPwd;
}
