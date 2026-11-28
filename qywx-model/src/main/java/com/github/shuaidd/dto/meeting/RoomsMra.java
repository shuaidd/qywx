package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms呼叫MRA地址信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsMra {
    /**
     * 信令协议：1 SIP、2 H.323
     **/
    @JsonProperty("protocol")
    private Integer protocol;
    /**
     * SIP或H.323信令地址
     **/
    @JsonProperty("dial_string")
    private String dialString;
}
