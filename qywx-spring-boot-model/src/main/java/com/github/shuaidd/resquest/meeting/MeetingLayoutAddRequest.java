package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingLayoutResult;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 添加会议基础布局请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingLayoutAddRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 布局对象列表
     **/
    @JsonProperty("layout_list")
    private List<MeetingLayoutResult> layoutList;
    /**
     * 需要应用的布局序号，从1开始
     **/
    @JsonProperty("default_layout_order")
    private Integer defaultLayoutOrder;
}
