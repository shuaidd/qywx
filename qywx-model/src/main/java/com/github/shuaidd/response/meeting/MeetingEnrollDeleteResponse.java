package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会议报名删除响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingEnrollDeleteResponse extends AbstractBaseResponse {

    /**
     * 成功删除的报名信息数量
     **/
    @JsonProperty("total_count")
    private Integer totalCount;
}