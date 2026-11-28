package com.github.shuaidd.dto.oa;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-01 20:34
 **/
public class QuotaRuleItem {
    private Integer quota;
    private Integer begin;
    private Integer end;

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", QuotaRuleItem.class.getSimpleName() + "[", "]")
                .add("quota=" + quota)
                .add("begin=" + begin)
                .add("end=" + end)
                .toString();
    }
}
