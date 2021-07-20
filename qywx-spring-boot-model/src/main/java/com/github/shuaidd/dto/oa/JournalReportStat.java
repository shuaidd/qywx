package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-20 10:04
 **/
public class JournalReportStat {

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("template_name")
    private String templateName;

    @JsonProperty("cycle_begin_time")
    private Long cycleBeginTime;

    @JsonProperty("cycle_end_time")
    private Long cycleEndTime;

    @JsonProperty("stat_begin_time")
    private Long statBeginTime;

    @JsonProperty("stat_end_time")
    private Long statEndTime;

    @JsonProperty("report_type")
    private Integer reportType;

    @JsonProperty("report_range")
    private JournalReportRange reportRange;

    @JsonProperty("white_range")
    private JournalReportRange whiteRange;

    private JournalReportRange receivers;

    @JsonProperty("report_list")
    private List<JournalReportItem> reportList;

    @JsonProperty("unreport_list")
    private List<JournalReportItem> unReportList;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getCycleBeginTime() {
        return cycleBeginTime;
    }

    public void setCycleBeginTime(Long cycleBeginTime) {
        this.cycleBeginTime = cycleBeginTime;
    }

    public Long getCycleEndTime() {
        return cycleEndTime;
    }

    public void setCycleEndTime(Long cycleEndTime) {
        this.cycleEndTime = cycleEndTime;
    }

    public Long getStatBeginTime() {
        return statBeginTime;
    }

    public void setStatBeginTime(Long statBeginTime) {
        this.statBeginTime = statBeginTime;
    }

    public Long getStatEndTime() {
        return statEndTime;
    }

    public void setStatEndTime(Long statEndTime) {
        this.statEndTime = statEndTime;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public JournalReportRange getReportRange() {
        return reportRange;
    }

    public void setReportRange(JournalReportRange reportRange) {
        this.reportRange = reportRange;
    }

    public JournalReportRange getWhiteRange() {
        return whiteRange;
    }

    public void setWhiteRange(JournalReportRange whiteRange) {
        this.whiteRange = whiteRange;
    }

    public JournalReportRange getReceivers() {
        return receivers;
    }

    public void setReceivers(JournalReportRange receivers) {
        this.receivers = receivers;
    }

    public List<JournalReportItem> getReportList() {
        return reportList;
    }

    public void setReportList(List<JournalReportItem> reportList) {
        this.reportList = reportList;
    }

    public List<JournalReportItem> getUnReportList() {
        return unReportList;
    }

    public void setUnReportList(List<JournalReportItem> unReportList) {
        this.unReportList = unReportList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JournalReportStat.class.getSimpleName() + "[", "]")
                .add("templateId='" + templateId + "'")
                .add("templateName='" + templateName + "'")
                .add("cycleBeginTime=" + cycleBeginTime)
                .add("cycleEndTime=" + cycleEndTime)
                .add("statBeginTime=" + statBeginTime)
                .add("statEndTime=" + statEndTime)
                .add("reportType=" + reportType)
                .add("reportRange=" + reportRange)
                .add("whiteRange=" + whiteRange)
                .add("receivers=" + receivers)
                .add("reportList=" + reportList)
                .add("unReportList=" + unReportList)
                .toString();
    }
}
