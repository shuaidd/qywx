package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 未观看直播学生统计响应（v1分页协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingUnwatchStatResponse extends AbstractBaseResponse {

    /**
     * 是否结束。0表示还有更多数据需要继续拉取，1表示已经拉取完所有数据
     */
    @JsonProperty("ending")
    private Integer ending;

    /**
     * 当前数据最后一个key值，下次调用带上该值则从该key值往后拉取
     */
    @JsonProperty("next_key")
    private String nextKey;

    /**
     * 未观看直播统计信息
     */
    @JsonProperty("stat_info")
    private LivingUnwatchStatInfo statInfo;

    public Integer getEnding() {
        return ending;
    }

    public void setEnding(Integer ending) {
        this.ending = ending;
    }

    public String getNextKey() {
        return nextKey;
    }

    public void setNextKey(String nextKey) {
        this.nextKey = nextKey;
    }

    public LivingUnwatchStatInfo getStatInfo() {
        return statInfo;
    }

    public void setStatInfo(LivingUnwatchStatInfo statInfo) {
        this.statInfo = statInfo;
    }
}
