package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingLayoutPage;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 修改会议基础布局请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingLayoutUpdateRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 布局ID
     **/
    @JsonProperty("layout_id")
    private String layoutId;
    /**
     * 布局单页列表
     **/
    @JsonProperty("page_list")
    private List<MeetingLayoutPage> pageList;
    /**
     * 是否设置为会议应用的布局
     **/
    @JsonProperty("enable_set_default")
    private Boolean enableSetDefault;
}
