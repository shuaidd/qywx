package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.FailedChatData;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 22:37
 **/
public class FailedChatResponse extends AbstractBaseResponse {

    @JsonProperty("failed_chat_list")
    private List<FailedChatData> failedChatList;

    public List<FailedChatData> getFailedChatList() {
        return failedChatList;
    }

    public void setFailedChatList(List<FailedChatData> failedChatList) {
        this.failedChatList = failedChatList;
    }
}
