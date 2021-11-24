package com.github.shuaidd.service;

import com.github.shuaidd.dto.message.ChatInfo;
import com.github.shuaidd.response.BaseResponse;
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

    /**
     * 发送消息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return SendMessageResponse
     */
    public final SendMessageResponse sendMessage(SendMessageRequest request, String applicationName) {
        checkApplication(applicationName);
        SendMessageResponse response = null;
        if (Objects.nonNull(request)) {
            response = weChatClient.sendMessage(request, applicationName);
            if (isSuccess(response)) {
                logger.info("消息发送成功");
            }
        }

        return response;
    }

    /**
     * 发送群消息
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public final void sendAppChatMessage(SendAppChatRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.nonNull(request)) {
            BaseResponse response = weChatClient.sendAppChatMessage(request, applicationName);
            if (isSuccess(response)) {
                logger.info("消息发送成功");
            }
        }
    }

    /**
     * 创建群聊
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return String
     */
    public final String createAppChat(CreateAppChatRequest request, String applicationName) {
        checkApplication(applicationName);
        String chatId = "";
        if (Objects.nonNull(request)) {
            CreateAppChatResponse response = weChatClient.createAppChat(request, applicationName);
            if (isSuccess(response)) {
                chatId = response.getChatId();
            }
        }

        return chatId;
    }

    /**
     * 修改群聊会话
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public final void updateAppChat(UpdateAppChatRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.nonNull(request)) {
            BaseResponse response = weChatClient.updateAppChat(request, applicationName);
            if (isSuccess(response)) {
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
    public final ChatInfo searchAppChat(String chatId, String applicationName) {
        checkApplication(applicationName);
        ChatInfo chatInfo = null;
        if (StringUtils.isNotEmpty(chatId)) {
            SearchAppChatResponse response = weChatClient.searchAppChat(chatId, applicationName);
            if (isSuccess(response)) {
                chatInfo = response.getChatInfo();
                logger.info("查询到的群聊信息：{}", chatInfo);
            }
        }

        return chatInfo;
    }
}
