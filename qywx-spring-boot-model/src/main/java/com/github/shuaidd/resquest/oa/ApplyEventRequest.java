package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.oa.ApplyData;
import com.github.shuaidd.dto.oa.ApproverAttr;
import com.github.shuaidd.dto.oa.SummaryInfo;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 13:32
 **/
public class ApplyEventRequest {

    @JsonProperty("creator_userid")
    private String creatorUserId;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("use_template_approver")
    private Integer useTemplateApprover;

    @JsonProperty("choose_department")
    private Integer chooseDepartment;

    @JsonProperty("approver")
    private List<ApproverAttr> approverAttrs;

    private List<String> notifyer;

    @JsonProperty("notify_type")
    private Integer notifyType;

    @JsonProperty("apply_data")
    private ApplyData applyData;

    @JsonProperty("summary_list")
    private List<SummaryInfo> summaryInfos;

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getUseTemplateApprover() {
        return useTemplateApprover;
    }

    public void setUseTemplateApprover(Integer useTemplateApprover) {
        this.useTemplateApprover = useTemplateApprover;
    }

    public Integer getChooseDepartment() {
        return chooseDepartment;
    }

    public void setChooseDepartment(Integer chooseDepartment) {
        this.chooseDepartment = chooseDepartment;
    }

    public List<ApproverAttr> getApproverAttrs() {
        return approverAttrs;
    }

    public void setApproverAttrs(List<ApproverAttr> approverAttrs) {
        this.approverAttrs = approverAttrs;
    }

    public List<String> getNotifyer() {
        return notifyer;
    }

    public void setNotifyer(List<String> notifyer) {
        this.notifyer = notifyer;
    }

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    public ApplyData getApplyData() {
        return applyData;
    }

    public void setApplyData(ApplyData applyData) {
        this.applyData = applyData;
    }

    public List<SummaryInfo> getSummaryInfos() {
        return summaryInfos;
    }

    public void setSummaryInfos(List<SummaryInfo> summaryInfos) {
        this.summaryInfos = summaryInfos;
    }
}
