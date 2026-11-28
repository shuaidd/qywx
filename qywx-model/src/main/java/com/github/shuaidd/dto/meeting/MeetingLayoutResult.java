package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 会议基础布局结果
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingLayoutResult {
    /**
     * 布局ID
     **/
    @JsonProperty("layout_id")
    private String layoutId;
    /**
     * 布局单页列表
     **/
    @JsonProperty("page_list")
    private List<MeetingLayoutPage> pageList;
}
