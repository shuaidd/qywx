package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商户申请人证件信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplyMchIdCardInfo {

    /**
     * 证件人像面或正面照片 ID
     **/
    @JsonProperty("id_card_copy_open_wx_pay_media_id")
    private String idCardCopyMediaId;

    /**
     * 证件国徽面或反面照片 ID
     **/
    @JsonProperty("id_card_national_open_wx_pay_media_id")
    private String idCardNationalMediaId;

    /**
     * 证件姓名
     **/
    @JsonProperty("id_card_name")
    private String idCardName;

    /**
     * 证件号码
     **/
    @JsonProperty("id_card_number")
    private String idCardNumber;

    /**
     * 证件居住地址
     **/
    @JsonProperty("id_card_address")
    private String idCardAddress;

    /**
     * 证件有效期开始日期
     **/
    @JsonProperty("id_card_valid_time_begin")
    private String idCardValidTimeBegin;

    /**
     * 证件有效期结束日期
     **/
    @JsonProperty("id_card_valid_time")
    private String idCardValidTime;

    /**
     * 证件类型
     **/
    @JsonProperty("id_doc_type")
    private Integer idDocType;
}
