package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.dto.tool.FrontEndTicket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 前端SDK工具
 *
 * @author huangweilong
 **/
@FeignClient(name = "frontEndToolsClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface FrontEndToolsClient extends CommonClient {
    /**
     * 获取应用 ticket
     *
     * @param applicationName 应用名
     * @return FrontEndTicket
     */
    @GetMapping(value = "/get_jsapi_ticket", headers = HEAD)
    FrontEndTicket getAppTicket(@RequestParam("access_token") String accessToken, @RequestParam(HEAD_KEY) String applicationName);
}
