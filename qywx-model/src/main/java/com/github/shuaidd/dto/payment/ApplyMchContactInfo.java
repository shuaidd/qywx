package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商户申请超级管理员信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplyMchContactInfo {

    /**
     * 超级管理员类型：65 法人，66 经办人
     **/
    @JsonProperty("contact_type")
    private String contactType;

    /**
     * 超级管理员证件信息
     **/
    @JsonProperty("contact_info")
    private ApplyMchIdCardInfo contactInfo;

    /**
     * 业务办理授权函照片 ID
     **/
    @JsonProperty("business_authorization_letter_open_wx_pay_media_id")
    private String businessAuthorizationLetterMediaId;

    /**
     * 超级管理员手机号
     **/
    @JsonProperty("mobile_phone")
    private String mobilePhone;

    /**
     * 超级管理员邮箱
     **/
    @JsonProperty("contact_email")
    private String contactEmail;
}
