package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 申请小程序收款交易账单请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class GetBillRequest {

    /**
     * 账单日期，格式 yyyy-MM-dd
     **/
    @JsonProperty("bill_date")
    private String billDate;

    /**
     * 商户号
     **/
    @JsonProperty("mchid")
    private String mchId;

    /**
     * 账单类型：ALL、SUCCESS 或 REFUND
     **/
    @JsonProperty("bill_type")
    private String billType;

    /**
     * 压缩类型，空或 GZIP
     **/
    @JsonProperty("tar_type")
    private String tarType;
}
