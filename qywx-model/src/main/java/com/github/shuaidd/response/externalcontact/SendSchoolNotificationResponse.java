package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 发送学校通知响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class SendSchoolNotificationResponse extends AbstractBaseResponse {

    @JsonProperty("invalid_parent_userid")
    private List<String> invalidParentUserIds;

    @JsonProperty("invalid_student_userid")
    private List<String> invalidStudentUserIds;

    @JsonProperty("invalid_party")
    private List<String> invalidParty;

    public List<String> getInvalidParentUserIds() { return invalidParentUserIds; }
    public void setInvalidParentUserIds(List<String> invalidParentUserIds) { this.invalidParentUserIds = invalidParentUserIds; }
    public List<String> getInvalidStudentUserIds() { return invalidStudentUserIds; }
    public void setInvalidStudentUserIds(List<String> invalidStudentUserIds) { this.invalidStudentUserIds = invalidStudentUserIds; }
    public List<String> getInvalidParty() { return invalidParty; }
    public void setInvalidParty(List<String> invalidParty) { this.invalidParty = invalidParty; }
}