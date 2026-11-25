package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.message.CreateAppChatResponse;
import com.github.shuaidd.response.message.SearchAppChatResponse;
import com.github.shuaidd.response.message.UpdateTemplateCardResponse;
import com.github.shuaidd.response.message.SendMessageResponse;
import com.github.shuaidd.resquest.message.AiBotResponseRequest;
import com.github.shuaidd.resquest.message.CreateAppChatRequest;
import com.github.shuaidd.resquest.message.SendAppChatRequest;
import com.github.shuaidd.resquest.message.RecallMessageRequest;
import com.github.shuaidd.resquest.message.SendMessageRequest;
import com.github.shuaidd.resquest.message.UpdateAppChatRequest;
import com.github.shuaidd.resquest.message.UpdateTemplateCardRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 企业微信消息相关接口
 *
 * @author ddshuai
 * date 2022-09-01 09:27
 **/
public interface MessageClient extends CommonClient {

    /**
     * 发送消息
     *
     * @param request 请求体
     * @param app     应用名
     * @return SendMessageResponse
     */
    @RequestLine("POST /message/send?app={app}")
    @Headers(HEAD)
    SendMessageResponse sendMessage(SendMessageRequest request, @Param("app") String app);

    /**
     * 撤回应用消息
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /message/recall?app={app}")
    @Headers(HEAD)
    BaseResponse recallMessage(RecallMessageRequest request, @Param("app") String app);

    /**
     * 智能机器人主动回复消息
     *
     * @param request      请求体
     * @param responseCode 回调返回的响应码
     * @param app          应用名
     * @return BaseResponse
     */
    @RequestLine("POST /aibot/response?response_code={response_code}&app={app}")
    @Headers(HEAD)
    BaseResponse responseAiBot(AiBotResponseRequest request, @Param("response_code") String responseCode, @Param("app") String app);

    /**
     * 创建群聊会话
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateAppChatResponse
     */
    @RequestLine("POST /appchat/create?app={app}")
    @Headers(HEAD)
    CreateAppChatResponse createAppChat(CreateAppChatRequest request, @Param("app") String app);

    /**
     * 修改群聊会话
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /appchat/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateAppChat(UpdateAppChatRequest request, @Param("app") String app);

    /**
     * 获取群聊会话
     *
     * @param chatId 群聊编号
     * @param app    应用名
     * @return SearchAppChatResponse
     */
    @RequestLine("GET /appchat/get?chatid={chatid}&app={app}")
    @Headers(HEAD)
    SearchAppChatResponse searchAppChat(@Param("chatid") String chatId, @Param("app") String app);

    /**
     * 更新模版卡片消息
     *
     * @param request 请求体
     * @param app     应用名
     * @return UpdateTemplateCardResponse
     */
    @RequestLine("POST /message/update_template_card?app={app}")
    @Headers(HEAD)
    UpdateTemplateCardResponse updateTemplateCard(UpdateTemplateCardRequest request, @Param("app") String app);

    /**
     * 发送群消息
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /appchat/send?app={app}")
    @Headers(HEAD)
    BaseResponse sendAppChatMessage(SendAppChatRequest request, @Param("app") String app);
}
