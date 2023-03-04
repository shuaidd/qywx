package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 安全管理
 *
 * @author ddshuai
 * date 2023-02-26
 */
@FeignClient(name = "SafetyManageClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface SafetyManageClient extends CommonClient {

    @PostMapping(value = "message/send", headers = HEAD)
    BaseResponse getFileOperatorRecord();

}
