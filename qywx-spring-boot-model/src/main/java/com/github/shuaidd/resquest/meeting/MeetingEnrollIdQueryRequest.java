package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 按临时 ID 查询报名 ID 请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingEnrollIdQueryRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 多条报名记录排序规则
     **/
    @JsonProperty("sorting_rules")
    private Integer sortingRules;

    /**
     * 当场会议成员临时 ID 列表
     **/
    @JsonProperty("tmp_openid_list")
    private List<String> tmpOpenidList;
}