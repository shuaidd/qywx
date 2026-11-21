package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.checkin.HardwareCheckInRecord;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获取考勤设备打卡数据响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class HardwareCheckInDataResponse extends AbstractBaseResponse {

    /**
     * 考勤设备原始打卡记录列表
     */
    @JsonProperty("checkindata")
    private List<HardwareCheckInRecord> checkInDataList;

    public List<HardwareCheckInRecord> getCheckInDataList() {
        return checkInDataList;
    }

    public void setCheckInDataList(List<HardwareCheckInRecord> checkInDataList) {
        this.checkInDataList = checkInDataList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("checkInDataList", checkInDataList)
                .toString();
    }
}
