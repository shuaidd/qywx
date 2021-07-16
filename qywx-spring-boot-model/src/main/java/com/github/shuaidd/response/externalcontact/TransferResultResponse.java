package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 22:22
 **/
public class TransferResultResponse extends AbstractBaseResponse {
    /**
     * 接替状态， 1-接替完毕 2-等待接替 3-客户拒绝 4-接替成员客户达到上限 5-无接替记录
     */
    private Integer status;

    /**
     * 接替客户的时间，如果是等待接替状态，则为未来的自动接替时间
     */
    @JsonProperty("takeover_time")
    private Date takeoverTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTakeoverTime() {
        return takeoverTime;
    }

    public void setTakeoverTime(Date takeoverTime) {
        this.takeoverTime = takeoverTime;
    }
}
