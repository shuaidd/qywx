package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms会议室录制配置
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsRecordSettings {
    /**
     * 云录制分享范围：0关闭、1全部成员、2登录成员、3同企业成员、4参会成员
     **/
    @JsonProperty("share_record")
    private Integer shareRecord;
    /**
     * 是否允许下载云录制
     **/
    @JsonProperty("download_record")
    private Boolean downloadRecord;
}
