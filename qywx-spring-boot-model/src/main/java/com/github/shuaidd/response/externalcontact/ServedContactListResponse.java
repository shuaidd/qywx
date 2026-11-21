package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.ServedContact;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 获取已服务的外部联系人响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ServedContactListResponse extends AbstractBaseResponse {

    /**
     * 已服务的外部联系人列表
     */
    @JsonProperty("info_list")
    private List<ServedContact> infoList;

    /**
     * 分页游标，下次请求时填写以获取之后分页的记录；没有更多数据时返回空，有效期为4小时
     */
    @JsonProperty("next_cursor")
    private String nextCursor;

    public List<ServedContact> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<ServedContact> infoList) {
        this.infoList = infoList;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}
