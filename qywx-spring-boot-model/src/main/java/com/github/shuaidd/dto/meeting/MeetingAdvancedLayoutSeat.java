package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 会议高级布局座次
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingAdvancedLayoutSeat {
    /**
     * 宫格ID
     **/
    @JsonProperty("grid_id")
    private String gridId;
    /**
     * 宫格类型：1 视频画面、2 共享画面
     **/
    @JsonProperty("grid_type")
    private Integer gridType;
    /**
     * 视频画面来源：1 演讲者、2 自动填充、3 指定人员
     **/
    @JsonProperty("video_type")
    private Integer videoType;
    /**
     * 宫格中的用户列表
     **/
    @JsonProperty("user_list")
    private List<MeetingLayoutGridUser> userList;
}
