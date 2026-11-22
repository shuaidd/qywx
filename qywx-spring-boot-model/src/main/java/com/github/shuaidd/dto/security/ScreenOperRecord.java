package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 截屏操作记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class ScreenOperRecord {
    /**
     * 操作时间，Unix秒级时间戳
     **/
    @JsonProperty("time")
    private Long time;

    /**
     * 截屏操作者userid
     **/
    @JsonProperty("userid")
    private String userid;

    /**
     * 截屏操作者部门id，应用可见范围内才返回
     **/
    @JsonProperty("department_id")
    private Integer departmentId;

    /**
     * 截屏内容类型，1-聊天，2-通讯录，3-邮件，4-文件，5-日程，6-其他
     **/
    @JsonProperty("screen_shot_type")
    private Integer screenShotType;

    /**
     * 截屏内容说明
     **/
    @JsonProperty("screen_shot_content")
    private String screenShotContent;

    /**
     * 截屏操作者使用的操作系统
     **/
    @JsonProperty("system")
    private String system;
}
