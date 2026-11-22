package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 家校网页授权用户身份响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolUserInfoResponse extends AbstractBaseResponse {

    /**
     * 手机设备号，删除重装会改变，升级不受影响
     */
    @JsonProperty("DeviceId")
    private String deviceId;

    /**
     * 家校通讯录里家长的userid
     */
    @JsonProperty("parent_userid")
    private String parentUserId;

    /**
     * 家校通讯录里学生的userid
     */
    @JsonProperty("student_userid")
    private String studentUserId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId;
    }
}
