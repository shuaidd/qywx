package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商户申请营业执照或登记证书信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplyMchBusinessLicenseInfo {

    /**
     * 登记证书类型，社会组织主体必填
     **/
    @JsonProperty("cert_type")
    private Integer certType;

    /**
     * 营业执照或登记证书照片 ID
     **/
    @JsonProperty("business_license_copy_open_wx_pay_media_id")
    private String businessLicenseCopyMediaId;

    /**
     * 营业执照或登记证书注册号
     **/
    @JsonProperty("business_license_number")
    private String businessLicenseNumber;

    /**
     * 证照上的商户名称
     **/
    @JsonProperty("merchant_name")
    private String merchantName;

    /**
     * 经营者或法定代表人姓名
     **/
    @JsonProperty("legal_person")
    private String legalPerson;

    /**
     * 注册地址
     **/
    @JsonProperty("company_address")
    private String companyAddress;

    /**
     * 营业期限开始日期，格式 yyyy-MM-dd
     **/
    @JsonProperty("business_time_begin_time")
    private String businessTimeBeginTime;

    /**
     * 营业期限结束日期，长期可填写“长期”
     **/
    @JsonProperty("business_time_end_time")
    private String businessTimeEndTime;
}
