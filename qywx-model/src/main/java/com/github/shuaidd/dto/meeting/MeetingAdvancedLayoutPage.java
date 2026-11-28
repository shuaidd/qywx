package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 会议高级布局单页
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingAdvancedLayoutPage {
    /**
     * 布局模板ID
     **/
    @JsonProperty("layout_template_id")
    private String layoutTemplateId;
    /**
     * 是否开启轮询
     **/
    @JsonProperty("enable_polling")
    private Boolean enablePolling;
    /**
     * 轮询参数设置
     **/
    @JsonProperty("polling_setting")
    private MeetingLayoutPollingSetting pollingSetting;
    /**
     * 用户座次列表
     **/
    @JsonProperty("user_seat_list")
    private List<MeetingAdvancedLayoutSeat> userSeatList;
}
