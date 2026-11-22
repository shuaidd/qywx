package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对外收款商品信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ExternalCommodity {

    /**
     * 商品描述
     **/
    @JsonProperty("description")
    private String description;

    /**
     * 商品数量
     **/
    @JsonProperty("amount")
    private Integer amount;
}
