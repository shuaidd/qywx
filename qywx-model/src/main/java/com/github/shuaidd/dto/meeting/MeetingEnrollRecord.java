package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * 会议报名记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingEnrollRecord {

    /**
     * 会议报名 ID
     **/
    @JsonProperty("enroll_id")
    private String enrollId;

    /**
     * 报名时间，UTC+8 格式文本
     **/
    @JsonProperty("enroll_time")
    private String enrollTime;

    /**
     * 报名来源：1 手动报名、2 批量导入
     **/
    @JsonProperty("enroll_source_type")
    private Integer enrollSourceType;

    /**
     * 报名昵称
     **/
    @JsonProperty("nick_name")
    private String nickName;

    /**
     * 审批状态：1 待审批、2 已拒绝、3 已批准
     **/
    @JsonProperty("status")
    private Integer status;

    /**
     * 企业内报名成员 userid
     **/
    @JsonProperty("userid")
    private String userId;

    /**
     * 当场会议的成员临时 ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;

    /**
     * 电话入会报名码
     **/
    @JsonProperty("enroll_code")
    private String enrollCode;

    /**
     * 报名答题详情列表
     **/
    @JsonProperty("answer_list")
    private List<MeetingAnswer> answerList;
}