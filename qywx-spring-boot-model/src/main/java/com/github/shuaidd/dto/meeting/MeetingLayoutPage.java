package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 会议基础布局单页
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingLayoutPage {
    /**
     * 布局模板ID
     **/
    @JsonProperty("layout_template_id")
    private String layoutTemplateId;
    /**
     * 用户座次列表
     **/
    @JsonProperty("user_seat_list")
    private List<MeetingLayoutSeat> userSeatList;
}
