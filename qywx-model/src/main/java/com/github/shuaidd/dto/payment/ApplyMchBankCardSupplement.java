package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import lombok.experimental.Accessors;

/**
 * 商户申请银行卡补充资料
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplyMchBankCardSupplement {

    /**
     * 结算证明照片 ID
     **/
    @JsonProperty("settlement_certificate_open_wx_pay_media_id")
    private String settlementCertificateMediaId;

    /**
     * 关系证明照片 ID
     **/
    @JsonProperty("relationship_certificate_open_wx_pay_media_id")
    private String relationshipCertificateMediaId;

    /**
     * 其他关系证明照片 ID 列表
     **/
    @JsonProperty("other_certificate_open_wx_pay_media_id")
    private List<String> otherCertificateMediaIds;
}
