package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingEnrollIdRef;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 删除会议报名信息请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingEnrollDeleteRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 要删除的报名 ID 列表
     **/
    @JsonProperty("enroll_id_list")
    private List<MeetingEnrollIdRef> enrollIdList;
}