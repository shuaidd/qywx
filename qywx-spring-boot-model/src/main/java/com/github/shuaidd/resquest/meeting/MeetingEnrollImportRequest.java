package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingEnrollMember;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 导入会议报名信息请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingEnrollImportRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 要导入的报名成员列表
     **/
    @JsonProperty("enroll_list")
    private List<MeetingEnrollMember> enrollList;
}