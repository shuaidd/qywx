package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 智能表格自动化创建的群聊列表响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class AutomationGroupChatListResponse extends AbstractBaseResponse {

    /**
     * 是否还有更多数据
     */
    @JsonProperty("has_more")
    private Boolean hasMore;

    /**
     * 下一次请求的cursor值
     */
    @JsonProperty("next_cursor")
    private String nextCursor;

    /**
     * 群聊chatid列表
     */
    @JsonProperty("chat_id_list")
    private List<String> chatIdList;
}