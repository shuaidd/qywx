package com.github.shuaidd.service;

import com.github.shuaidd.client.MessageClient;
import com.github.shuaidd.dto.message.ChatInfo;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.message.CreateAppChatResponse;
import com.github.shuaidd.response.message.SearchAppChatResponse;
import com.github.shuaidd.response.message.SendMessageResponse;
import com.github.shuaidd.resquest.message.CreateAppChatRequest;
import com.github.shuaidd.resquest.message.SendAppChatRequest;
import com.github.shuaidd.resquest.message.SendMessageRequest;
import com.github.shuaidd.resquest.message.UpdateAppChatRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 描述 消息管理
 *
 * @author ddshuai
 * date 2019-04-08 16:51
 **/
@Service
public class MessageService extends AbstractBaseService {

    private final MessageClient messageClient;

    public MessageService(MessageClient messageClient) {
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
