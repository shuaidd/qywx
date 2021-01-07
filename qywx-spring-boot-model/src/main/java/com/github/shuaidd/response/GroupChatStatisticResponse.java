package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.GroupChatStatistic;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-07 16:15
 **/
public class GroupChatStatisticResponse extends AbstractBaseResponse {
    private Integer total;
    @JsonProperty("next_offset")
    private Integer nextOffset;
    private List<GroupChatStatistic> items;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
    }

    public List<GroupChatStatistic> getItems() {
        return items;
    }

    public void setItems(List<GroupChatStatistic> items) {
        this.items = items;
    }
}
