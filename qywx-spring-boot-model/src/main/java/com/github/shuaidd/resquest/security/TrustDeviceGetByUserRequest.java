package com.github.shuaidd.resquest.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 成员使用设备查询请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class TrustDeviceGetByUserRequest {
    /**
     * 设备最后登录成员userid
     **/
    @JsonProperty("last_login_userid")
    private String lastLoginUserid;

    /**
     * 查询设备类型，1-可信企业设备，2-未知设备，3-可信个人设备
     **/
    @JsonProperty("type")
    private Integer type;
}
