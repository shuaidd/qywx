package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.exmail.CreatePublicMailResponse;
import com.github.shuaidd.resquest.exmail.CreatePublicMailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 企业邮箱管理
 */
@FeignClient(name = "EnterpriseEmailClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface EnterpriseEmailClient extends CommonClient {

    /**
     * 创建业务邮箱
     *
     * @param request 请求
     * @param app     应用
     * @return 业务邮箱ID
     */
    @PostMapping(value = "exmail/publicmail/create", headers = HEAD)
    CreatePublicMailResponse createPublicMail(CreatePublicMailRequest request, @RequestParam(HEAD_KEY) String app);
}
