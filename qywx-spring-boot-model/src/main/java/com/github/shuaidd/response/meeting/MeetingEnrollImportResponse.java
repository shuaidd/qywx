package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingEnrollMember;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 会议报名导入响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingEnrollImportResponse extends AbstractBaseResponse {

    /**
     * 成功导入的报名信息条数
     **/
    @JsonProperty("total_count")
    private Integer totalCount;

    /**
     * 导入后的报名成员列表
     **/
    @JsonProperty("enroll_list")
    private List<MeetingEnrollMember> enrollList;
}