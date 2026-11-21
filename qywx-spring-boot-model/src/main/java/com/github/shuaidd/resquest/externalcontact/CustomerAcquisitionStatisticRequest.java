package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 获客链接使用详情查询请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionStatisticRequest {

    /**
     * 获客链接 ID；必须是当前应用创建的链接
     */
    @JsonProperty("link_id")
    private String linkId;

    /**
     * 统计起始时间；时间戳单位秒，官方会转换为对应自然日后查询
     */
    @JsonProperty("start_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date startTime;

    /**
     * 统计结束时间；时间戳单位秒，官方会转换为对应自然日后查询
     */
    @JsonProperty("end_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endTime;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("linkId", linkId)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .toString();
    }
}