package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.dto.externalcontact.OwnerFilter;
import com.github.shuaidd.json.Date2LongSerializer;
import com.github.shuaidd.resquest.OffsetPageRequest;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-07 16:06
 **/

public class GroupChatStatisticRequest extends OffsetPageRequest {

    @JsonProperty("day_begin_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date dayBeginTime;

    @JsonProperty("day_end_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date dayEndTime;

    /**
     * 排序方式。
     * 1 - 新增群的数量
     * 2 - 群总数
     * 3 - 新增群人数
     * 4 - 群总人数
     * <p>
     * 默认为1
     */
    @JsonProperty("order_by")
    private Integer orderBy;

    /**
     * 是否升序。0-否；1-是。默认降序
     */
    @JsonProperty("order_asc")
    private Integer orderAsc;

    @JsonProperty("owner_filter")
    private OwnerFilter ownerFilter;

    public Date getDayBeginTime() {
        return dayBeginTime;
    }

    public void setDayBeginTime(Date dayBeginTime) {
        this.dayBeginTime = dayBeginTime;
    }

    public Date getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(Date dayEndTime) {
        this.dayEndTime = dayEndTime;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getOrderAsc() {
        return orderAsc;
    }

    public void setOrderAsc(Integer orderAsc) {
        this.orderAsc = orderAsc;
    }

    public OwnerFilter getOwnerFilter() {
        return ownerFilter;
    }

    public void setOwnerFilter(OwnerFilter ownerFilter) {
        this.ownerFilter = ownerFilter;
    }
}
