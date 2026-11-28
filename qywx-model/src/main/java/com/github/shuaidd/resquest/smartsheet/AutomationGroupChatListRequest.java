package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 智能表格自动化创建的群聊列表请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class AutomationGroupChatListRequest {

    /**
     * 智能表格ID
     */
    @JsonProperty("docid")
    private String docId;

    /**
     * 分页查询游标
     */
    private String cursor;

    /**
     * 每次返回的数据上限，最大200
     */
    private Integer limit;
}