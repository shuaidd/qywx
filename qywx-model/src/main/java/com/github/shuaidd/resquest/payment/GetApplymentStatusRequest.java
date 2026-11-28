package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.github.shuaidd.dto.payment.*;

/**
 * 查询对外收款账户申请单状态请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class GetApplymentStatusRequest {

    /**
     * 业务申请编号
     **/
    @JsonProperty("out_request_no")
    private String outRequestNo;
}
