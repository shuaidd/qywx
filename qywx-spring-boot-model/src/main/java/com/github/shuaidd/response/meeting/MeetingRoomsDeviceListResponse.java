package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.RoomsDeviceInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Rooms设备列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsDeviceListResponse extends AbstractBaseResponse {
    /**
     * Rooms设备列表
     **/
    @JsonProperty("device_info_list")
    private List<RoomsDeviceInfo> deviceInfoList;
}
