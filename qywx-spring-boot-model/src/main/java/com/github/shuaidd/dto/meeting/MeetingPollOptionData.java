package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 会议投票选项统计结果
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingPollOptionData {

    /**
     * 投票选项 ID
     **/
    @JsonProperty("option_id")
    private Integer optionId;

    /**
     * 投票选项描述
     **/
    @JsonProperty("option_desc")
    private String optionDesc;

    /**
     * 该选项收到的投票数
     **/
    @JsonProperty("option_num")
    private Integer optionNum;

    /**
     * 该选项投票占比
     **/
    @JsonProperty("rate")
    private Integer rate;

    /**
     * 选择该选项的投票人列表
     **/
    @JsonProperty("option_user")
    private List<MeetingPollOptionUser> optionUser;
}
