package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingAdvancedLayout;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 添加会议高级布局请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingAdvancedLayoutAddRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 高级布局列表
     **/
    @JsonProperty("layout_list")
    private List<MeetingAdvancedLayout> layoutList;
}
