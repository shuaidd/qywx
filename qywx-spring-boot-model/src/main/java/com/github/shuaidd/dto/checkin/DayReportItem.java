package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-15 13:20
 **/
public class DayReportItem {

    @JsonProperty("base_info")
    private DayReportBaseInfo baseInfo;

    @JsonProperty("summary_info")
    private DayReportSummaryInfo summaryInfo;

    @JsonProperty("holiday_infos")
    private List<DayReportHolidayInfo> holidayInfos;

    @JsonProperty("exception_infos")
    private List<CheckInException> exceptions;

    @JsonProperty("ot_info")
    private CheckInOverTimeInfo overTimeInfo;

    @JsonProperty("sp_items")
    private List<SpItem> spItems;

    @JsonProperty("overwork_info")
    private OverWorkInfo overWorkInfo;

    public DayReportBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(DayReportBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public DayReportSummaryInfo getSummaryInfo() {
        return summaryInfo;
    }

    public void setSummaryInfo(DayReportSummaryInfo summaryInfo) {
        this.summaryInfo = summaryInfo;
    }

    public List<DayReportHolidayInfo> getHolidayInfos() {
        return holidayInfos;
    }

    public void setHolidayInfos(List<DayReportHolidayInfo> holidayInfos) {
        this.holidayInfos = holidayInfos;
    }

    public List<CheckInException> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<CheckInException> exceptions) {
        this.exceptions = exceptions;
    }

    public CheckInOverTimeInfo getOverTimeInfo() {
        return overTimeInfo;
    }

    public void setOverTimeInfo(CheckInOverTimeInfo overTimeInfo) {
        this.overTimeInfo = overTimeInfo;
    }

    public List<SpItem> getSpItems() {
        return spItems;
    }

    public void setSpItems(List<SpItem> spItems) {
        this.spItems = spItems;
    }

    public OverWorkInfo getOverWorkInfo() {
        return overWorkInfo;
    }

    public void setOverWorkInfo(OverWorkInfo overWorkInfo) {
        this.overWorkInfo = overWorkInfo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DayReportItem.class.getSimpleName() + "[", "]")
                .add("baseInfo=" + baseInfo)
                .add("summaryInfo=" + summaryInfo)
                .add("holidayInfos=" + holidayInfos)
                .add("exceptions=" + exceptions)
                .add("overTimeInfo=" + overTimeInfo)
                .add("spItems=" + spItems)
                .add("overWorkInfo=" + overWorkInfo)
                .toString();
    }
}
