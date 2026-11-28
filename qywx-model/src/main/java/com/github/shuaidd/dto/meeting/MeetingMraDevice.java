package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议MRA设备操作对象
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingMraDevice {
    /**
     * 被操作MRA设备的会中临时ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;
}
