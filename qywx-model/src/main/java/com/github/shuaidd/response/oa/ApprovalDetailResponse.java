package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.oa.ApprovalDetail;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 10:01
 **/
public class ApprovalDetailResponse extends AbstractBaseResponse {

    @JsonProperty("info")
    private ApprovalDetail approvalDetail;

    public ApprovalDetail getApprovalDetail() {
        return approvalDetail;
    }

    public void setApprovalDetail(ApprovalDetail approvalDetail) {
        this.approvalDetail = approvalDetail;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApprovalDetailResponse.class.getSimpleName() + "[", "]")
                .add("approvalDetail=" + approvalDetail)
                .toString();
    }
}
