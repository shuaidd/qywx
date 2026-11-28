package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingEnrollIdMapping;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 会议报名 ID 查询响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingEnrollIdListResponse extends AbstractBaseResponse {

    /**
     * 成员临时 ID 与报名 ID 映射列表
     **/
    @JsonProperty("enroll_id_list")
    private List<MeetingEnrollIdMapping> enrollIdList;
}