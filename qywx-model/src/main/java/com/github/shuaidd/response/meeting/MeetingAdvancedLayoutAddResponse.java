package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingAdvancedLayout;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会议高级布局添加响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingAdvancedLayoutAddResponse extends AbstractBaseResponse {
    /**
     * 高级布局列表
     **/
    @JsonProperty("layout_list")
    private List<MeetingAdvancedLayout> layoutList;
}
