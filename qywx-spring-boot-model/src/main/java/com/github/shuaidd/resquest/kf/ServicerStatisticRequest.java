package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 获取客服帐号接待人员明细数据请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ServicerStatisticRequest {

    /**
     * 客服帐号ID；用于指定要查询接待人员明细数据的微信客服帐号
     */
    @JsonProperty("open_kfid")
    private String openKfid;

    /**
     * 接待人员 userid；不填时查询该客服帐号下的全部接待人员
     */
    @JsonProperty("servicer_userid")
    private String servicerUserid;

    /**
     * 统计起始时间；Unix时间戳，单位秒，官方按自然日归一化后查询
     */
    @JsonProperty("start_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date startTime;

    /**
     * 统计结束时间；Unix时间戳，单位秒，官方按自然日归一化后查询
     */
    @JsonProperty("end_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endTime;

    public String getOpenKfid() {
        return openKfid;
    }

    public void setOpenKfid(String openKfid) {
        this.openKfid = openKfid;
    }

    public String getServicerUserid() {
        return servicerUserid;
    }

    public void setServicerUserid(String servicerUserid) {
        this.servicerUserid = servicerUserid;
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
                .append("openKfid", openKfid)
                .append("servicerUserid", servicerUserid)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .toString();
    }
}
