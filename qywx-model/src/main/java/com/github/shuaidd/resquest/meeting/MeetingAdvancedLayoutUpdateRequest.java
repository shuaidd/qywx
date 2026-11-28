package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingAdvancedLayoutPage;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 修改会议高级布局请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingAdvancedLayoutUpdateRequest {
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
     * 布局名称
     **/
    @JsonProperty("layout_name")
    private String layoutName;
    /**
     * 布局单页列表，全量更新
     **/
    @JsonProperty("page_list")
    private List<MeetingAdvancedLayoutPage> pageList;
}
