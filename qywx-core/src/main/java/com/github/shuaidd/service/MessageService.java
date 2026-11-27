package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.client.MessageClient;
import com.github.shuaidd.dto.message.ChatInfo;
import com.github.shuaidd.enums.MsgType;
import com.github.shuaidd.exception.ParamCheckException;
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
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 描述 消息管理
 *
 * @author ddshuai
 * date 2019-04-08 16:51
 **/
public class MessageService extends AbstractBaseService {

    private final MessageClient messageClient;

    public MessageService(QywxContext context, MessageClient messageClient) {
        super(context);
        this.messageClient = messageClient;
    }

    /**
     * 发送消息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return SendMessageResponse
     */
    public SendMessageResponse sendMessage(SendMessageRequest request, String applicationName) {

        if (Objects.nonNull(request)) {
            return messageClient.sendMessage(request, applicationName);
        }

        throw new ParamCheckException("请求 不能为空，请检查！！！");
    }

    /**
     * 更新模版卡片消息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return UpdateTemplateCardResponse
     */
    public UpdateTemplateCardResponse updateTemplateCard(UpdateTemplateCardRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getAgentid()) || StringUtils.isEmpty(request.getResponseCode())) {
            throw new ParamCheckException("agentid、response_code 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getButton()) && Objects.isNull(request.getTemplateCard())) {
            throw new ParamCheckException("button 或 template_card 至少填写一项，请检查！！！");
        }
        return messageClient.updateTemplateCard(request, applicationName);
    }

    /**
     * 发送群消息
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void sendAppChatMessage(SendAppChatRequest request, String applicationName) {
        if (Objects.nonNull(request)) {
            messageClient.sendAppChatMessage(request, applicationName);
        }
    }

    /**
     * 撤回应用消息
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void recallMessage(RecallMessageRequest request, String applicationName) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getMsgid())) {
            throw new ParamCheckException("msgid 不能为空，请检查！！！");
        }
        messageClient.recallMessage(request, applicationName);
    }

    /**
     * 智能机器人主动回复消息
     *
     * @param request         请求
     * @param responseCode    回调返回的响应码
     * @param applicationName 应用名称
     * @return BaseResponse
     */
    public BaseResponse responseAiBot(AiBotResponseRequest request, String responseCode, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getMsgType()) || StringUtils.isEmpty(responseCode)) {
            throw new ParamCheckException("request、msgtype、response_code 不能为空，请检查！！！");
        }
        if (request.getMsgType() == MsgType.MARKDOWN && (Objects.isNull(request.getMarkdown()) || StringUtils.isEmpty(request.getMarkdown().getContent()))) {
            throw new ParamCheckException("markdown.content 不能为空，请检查！！！");
        }
        if (request.getMsgType() == MsgType.TEMPLATE_CARD && Objects.isNull(request.getTemplateCard())) {
            throw new ParamCheckException("template_card 不能为空，请检查！！！");
        }
        return messageClient.responseAiBot(request, responseCode, applicationName);
    }

    /**
     * 创建群聊
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return String
     */
    public String createAppChat(CreateAppChatRequest request, String applicationName) {
        if (Objects.nonNull(request)) {
            CreateAppChatResponse response = messageClient.createAppChat(request, applicationName);
            return response.getChatId();
        } else {
            throw new ParamCheckException("请求 不能为空，请检查！！！");
        }
    }

    /**
     * 修改群聊会话
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateAppChat(UpdateAppChatRequest request, String applicationName) {
        if (Objects.nonNull(request)) {
            messageClient.updateAppChat(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("更新群聊成功");
            }
        }
    }

    /**
     * 获取群聊会话
     *
     * @param chatId          群聊编号
     * @param applicationName 应用名称
     * @return ChatInfo
     */
    public ChatInfo searchAppChat(String chatId, String applicationName) {

        ChatInfo chatInfo = null;
        if (StringUtils.isNotEmpty(chatId)) {
            SearchAppChatResponse response = messageClient.searchAppChat(chatId, applicationName);
            chatInfo = response.getChatInfo();
            if (logger.isInfoEnabled()) {
                logger.info("查询到的群聊信息：{}", chatInfo);
            }
        }

        return chatInfo;
    }
}
