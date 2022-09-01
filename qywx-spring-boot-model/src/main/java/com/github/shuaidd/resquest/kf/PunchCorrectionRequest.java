package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;

import java.util.Date;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-01 21:06
 **/
public class PunchCorrectionRequest {

    /**
     * 需要补卡的成员userid
     */
    @JsonProperty(value = "userid")
    private String userId;

    /**
     * 应打卡日期，为当天0点的Unix时间戳。
     */
    @JsonProperty(value = "schedule_date_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date scheduleDateTime;

    /**
     * 应打卡时间点，相对应打卡日期0点的偏移秒数，如9点整则为32400。
     * 可通过获取员工打卡规则获取对应的规则打卡时间点，如work_sec/off_work_sec。
     * 对于没有规则对应的打卡时间点，如休息日打卡、无规则打卡、自由上下班，该参数不用填。
     */
    @JsonProperty(value = "schedule_checkin_time")
    private Integer scheduleCheckinTime;

    /**
     * 实际打卡时间，Unix时间戳。相对于schedule_checkin_time的实际打卡时间，具体可以表现为正常/迟到/早退
     */
    @JsonProperty(value = "checkin_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date checkinTime;

    /**
     * 备注信息 不超过512字节
     */
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(Date scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public Integer getScheduleCheckinTime() {
        return scheduleCheckinTime;
    }

    public void setScheduleCheckinTime(Integer scheduleCheckinTime) {
        this.scheduleCheckinTime = scheduleCheckinTime;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PunchCorrectionRequest.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("scheduleDateTime=" + scheduleDateTime)
                .add("scheduleCheckinTime=" + scheduleCheckinTime)
                .add("checkinTime=" + checkinTime)
                .add("remark='" + remark + "'")
                .toString();
    }
}
