package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 描述 新通教育
 *
 * @author ddshuai
 * @date 2021-12-22 16:00
 **/
@FeignClient(name = "MiniProgramClient", url = "https://api.weixin.qq.com", path = "${qywx.public-path:cgi-bin}")
public interface MiniProgramClient {

    /**
     * 获取授权token
     * @param appId APPID
     * @param secret 密钥
     * @return token
     */
    @GetMapping("/token?grant_type=client_credential")
    AccessTokenResponse getToken(@RequestParam("appid") String appId, @RequestParam("secret") String secret);

    @PostMapping("message/custom/send")
    void sendMsg(Map<String,?> map,@RequestParam("access_token") String token);
}
