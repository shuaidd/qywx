package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;
import java.util.List;

/**
 * 查询对外收款记录响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalBillListResponse extends AbstractBaseResponse {

    /**
     * 对外收款记录列表
     **/
    @JsonProperty("bill_list")
    private List<ExternalPaymentRecord> billList;
}
