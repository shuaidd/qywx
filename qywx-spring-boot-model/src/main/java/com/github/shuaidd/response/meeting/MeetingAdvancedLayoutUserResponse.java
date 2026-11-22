package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingAdvancedLayoutPage;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户高级布局响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingAdvancedLayoutUserResponse extends AbstractBaseResponse {
    /**
     * 会议应用的布局ID
     **/
    @JsonProperty("selected_layout_id")
    private String selectedLayoutId;
    /**
     * 布局名称
     **/
    @JsonProperty("layout_name")
    private String layoutName;
    /**
     * 布局类型：0 默认、2 自定义、3 个性布局
     **/
    @JsonProperty("layout_type")
    private Integer layoutType;
    /**
     * 布局单页列表
     **/
    @JsonProperty("page_list")
    private List<MeetingAdvancedLayoutPage> pageList;
}
