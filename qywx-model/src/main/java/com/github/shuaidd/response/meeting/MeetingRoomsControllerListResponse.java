package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.RoomsControllerInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Rooms控制器列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsControllerListResponse extends AbstractBaseResponse {
    /**
     * Rooms控制器列表
     **/
    @JsonProperty("controller_info_list")
    private List<RoomsControllerInfo> controllerInfoList;
}
