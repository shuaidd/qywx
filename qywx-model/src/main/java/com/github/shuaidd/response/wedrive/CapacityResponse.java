package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-03 06:49
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapacityResponse extends AbstractBaseResponse {

    @JsonProperty("total_capacity_for_all")
    private Long totalCapacityForAll;

    @JsonProperty("total_capacity_for_vip")
    private Long totalCapacityForVip;

    @JsonProperty("rest_capacity_for_all")
    private Long restCapacityForAll;

    @JsonProperty("rest_capacity_for_vip")
    private Long restCapacityForVip;

    public Long getTotalCapacityForAll() {
        return totalCapacityForAll;
    }

    public void setTotalCapacityForAll(Long totalCapacityForAll) {
        this.totalCapacityForAll = totalCapacityForAll;
    }

    public Long getTotalCapacityForVip() {
        return totalCapacityForVip;
    }

    public void setTotalCapacityForVip(Long totalCapacityForVip) {
        this.totalCapacityForVip = totalCapacityForVip;
    }

    public Long getRestCapacityForAll() {
        return restCapacityForAll;
    }

    public void setRestCapacityForAll(Long restCapacityForAll) {
        this.restCapacityForAll = restCapacityForAll;
    }

    public Long getRestCapacityForVip() {
        return restCapacityForVip;
    }

    public void setRestCapacityForVip(Long restCapacityForVip) {
        this.restCapacityForVip = restCapacityForVip;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CapacityResponse.class.getSimpleName() + "[", "]")
                .add("totalCapacityForAll=" + totalCapacityForAll)
                .add("totalCapacityForVip=" + totalCapacityForVip)
                .add("restCapacityForAll=" + restCapacityForAll)
                .add("restCapacityForVip=" + restCapacityForVip)
                .toString();
    }
}
