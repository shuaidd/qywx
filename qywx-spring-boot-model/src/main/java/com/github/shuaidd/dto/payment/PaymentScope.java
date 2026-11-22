package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import lombok.experimental.Accessors;

/**
 * 企业支付商户号使用范围
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class PaymentScope {

    /**
     * 使用范围内的企业成员 userid 列表
     **/
    @JsonProperty("user")
    private List<String> user;

    /**
     * 使用范围内的部门 id 列表
     **/
    @JsonProperty("partyid")
    private List<Integer> partyId;

    /**
     * 使用范围内的标签 id 列表
     **/
    @JsonProperty("tagid")
    private List<Integer> tagId;
}
