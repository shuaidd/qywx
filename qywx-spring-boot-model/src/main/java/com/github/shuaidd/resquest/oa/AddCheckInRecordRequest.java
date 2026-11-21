package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.github.shuaidd.dto.checkin.CheckInRecord;
import java.util.List;

/**
 * 添加打卡记录请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class AddCheckInRecordRequest {

    /**
     * 写入的打卡记录列表；官方一批最多允许200条
     */
    @JsonProperty("records")
    private List<CheckInRecord> records;

    public List<CheckInRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CheckInRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("records", records)
                .toString();
    }
}
