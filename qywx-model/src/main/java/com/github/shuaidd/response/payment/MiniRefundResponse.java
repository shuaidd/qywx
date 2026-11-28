package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;
import java.util.List;

/**
 * 申请小程序收款退款响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MiniRefundResponse extends AbstractBaseResponse {

    /**
     * 商户退款单号
     **/
    @JsonProperty("out_refund_no")
    private String outRefundNo;

    /**
     * 退款金额信息
     **/
    @JsonProperty("amount")
    private RefundAmount amount;

    /**
     * 优惠退款详情列表
     **/
    @JsonProperty("promotion_detail")
    private List<PromotionDetail> promotionDetail;
}
