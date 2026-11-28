package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingBackgroundImage;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 添加会议背景请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingBackgroundAddRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 背景图片列表
     **/
    @JsonProperty("image_list")
    private List<MeetingBackgroundImage> imageList;
    /**
     * 需要使用的背景图片序号，从1开始
     **/
    @JsonProperty("default_image_order")
    private Integer defaultImageOrder;
}
