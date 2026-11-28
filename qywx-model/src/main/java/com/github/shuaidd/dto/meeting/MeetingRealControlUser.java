package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 会议会控被操作成员
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRealControlUser {

    /**
     * 被操作者在当前会议中的临时 ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;

    /**
     * 被操作者当前使用的终端设备类型，需和实际设备一致
     **/
    @JsonProperty("instance_id")
    private Integer instanceId;

    /**
     * 成员在会中显示的昵称，最长 20 个字符
     **/
    @JsonProperty("nickname")
    private String nickname;
}
