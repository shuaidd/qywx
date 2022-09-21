package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-03 06:45
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProInfoResponse extends AbstractBaseResponse {

    @JsonProperty("is_pro")
    private boolean isPro;

    @JsonProperty("total_vip_acct_num")
    private Integer  totalVipAcctNum;

    @JsonProperty("use_vip_acct_num")
    private Integer  useVipAcctNum;

    @JsonProperty("pro_expire_time")
    private Integer  proExpireTime;

    public boolean isIsPro() {
        return isPro;
    }

    public void setIsPro(boolean isPro) {
        this.isPro = isPro;
    }

    public Integer  getTotalVipAcctNum() {
        return totalVipAcctNum;
    }

    public void setTotalVipAcctNum(Integer  totalVipAcctNum) {
        this.totalVipAcctNum = totalVipAcctNum;
    }

    public Integer  getUseVipAcctNum() {
        return useVipAcctNum;
    }

    public void setUseVipAcctNum(Integer  useVipAcctNum) {
        this.useVipAcctNum = useVipAcctNum;
    }

    public Integer  getProExpireTime() {
        return proExpireTime;
    }

    public void setProExpireTime(Integer  proExpireTime) {
        this.proExpireTime = proExpireTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProInfoResponse.class.getSimpleName() + "[", "]")
                .add("isPro=" + isPro)
                .add("totalVipAcctNum=" + totalVipAcctNum)
                .add("useVipAcctNum=" + useVipAcctNum)
                .add("proExpireTime=" + proExpireTime)
                .toString();
    }
}
