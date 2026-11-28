package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 即将过期的获客助手额度明细
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionQuota {

    /**
     * 当前批次额度的过期时间戳，单位秒；对应过期日零点
     */
    @JsonProperty("expire_date")
    private Long expireDate;

    /**
     * 当前批次将在过期日失效的剩余额度数量
     */
    @JsonProperty("balance")
    private Integer balance;

    public Long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("expireDate", expireDate)
                .append("balance", balance)
                .toString();
    }
}