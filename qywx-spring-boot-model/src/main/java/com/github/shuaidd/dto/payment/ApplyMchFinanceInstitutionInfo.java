package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import lombok.experimental.Accessors;

/**
 * 商户申请金融机构许可证信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplyMchFinanceInstitutionInfo {

    /**
     * 金融机构类型
     **/
    @JsonProperty("finance_type")
    private String financeType;

    /**
     * 金融机构许可证照片 ID 列表
     **/
    @JsonProperty("finance_license_pics_open_wx_pay_media_id")
    private List<String> financeLicensePicMediaIds;
}
