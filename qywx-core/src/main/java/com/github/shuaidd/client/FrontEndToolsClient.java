package com.github.shuaidd.client;

import com.github.shuaidd.dto.tool.FrontEndTicket;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

/**
 * 前端SDK工具
 *
 * @author huangweilong
 **/
public interface FrontEndToolsClient extends CommonClient {
    /**
     * 获取应用 ticket
     *
     * @param applicationName 应用名
     * @return FrontEndTicket
     */
    @RequestLine("GET /get_jsapi_ticket?access_token={access_token}&app={app}")
    @Headers(HEAD)
    FrontEndTicket getAppTicket(@Param("access_token") String accessToken, @Param("app") String applicationName);
}
