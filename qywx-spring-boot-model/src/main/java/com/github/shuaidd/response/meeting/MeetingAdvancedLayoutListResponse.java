package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingAdvancedLayout;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会议高级布局列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingAdvancedLayoutListResponse extends AbstractBaseResponse {
    /**
     * 会议应用的布局ID
     **/
    @JsonProperty("selected_layout_id")
    private String selectedLayoutId;
    /**
     * 基础和高级布局列表
     **/
    @JsonProperty("layout_list")
    private List<MeetingAdvancedLayout> layoutList;
}
