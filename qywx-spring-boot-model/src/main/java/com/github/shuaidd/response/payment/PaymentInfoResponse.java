package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;
import java.util.List;

/**
 * 查询收款项目商户单号响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentInfoResponse extends AbstractBaseResponse {

    /**
     * 收款项目关联的商户单号列表
     **/
    @JsonProperty("bill_list")
    private List<MerchantOrderNumber> billList;
}
