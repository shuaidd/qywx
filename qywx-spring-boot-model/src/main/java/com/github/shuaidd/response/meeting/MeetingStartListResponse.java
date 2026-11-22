package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingStartRecord;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 员工会议发起记录响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingStartListResponse extends AbstractBaseResponse {

    /**
     * 会议发起记录列表
     **/
    @JsonProperty("meeting_list")
    private List<MeetingStartRecord> meetingList;
}