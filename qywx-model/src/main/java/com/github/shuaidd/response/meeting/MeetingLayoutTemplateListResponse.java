package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingLayoutTemplate;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会议布局模板列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingLayoutTemplateListResponse extends AbstractBaseResponse {
    /**
     * 布局模板列表
     **/
    @JsonProperty("layout_template_list")
    private List<MeetingLayoutTemplate> layoutTemplateList;
}
