package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 批量删除会议布局请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingLayoutBatchDeleteRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 待删除布局ID列表
     **/
    @JsonProperty("layout_id_list")
    private List<String> layoutIdList;
}
