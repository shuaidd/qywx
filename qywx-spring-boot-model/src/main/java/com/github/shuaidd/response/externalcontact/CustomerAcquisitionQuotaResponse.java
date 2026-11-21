package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionQuota;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获客助手剩余使用量响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionQuotaResponse extends AbstractBaseResponse {

    /**
     * 历史累计使用的获客额度数量
     */
    @JsonProperty("total")
    private Integer total;

    /**
     * 当前剩余可用的获客额度数量
     */
    @JsonProperty("balance")
    private Integer balance;

    /**
     * 即将过期的额度明细列表
     */
    @JsonProperty("quota_list")
    private List<CustomerAcquisitionQuota> quotaList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<CustomerAcquisitionQuota> getQuotaList() {
        return quotaList;
    }

    public void setQuotaList(List<CustomerAcquisitionQuota> quotaList) {
        this.quotaList = quotaList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("total", total)
                .append("balance", balance)
                .append("quotaList", quotaList)
                .toString();
    }
}