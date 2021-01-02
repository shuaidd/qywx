package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 13:11
 **/
public class ExpenseItem {

    @JsonProperty("expenseitem_type")
    private Integer expenseItemType;
    private Long time;
    private Integer sums;
    private String reason;

    public Integer getExpenseItemType() {
        return expenseItemType;
    }

    public void setExpenseItemType(Integer expenseItemType) {
        this.expenseItemType = expenseItemType;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getSums() {
        return sums;
    }

    public void setSums(Integer sums) {
        this.sums = sums;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ExpenseItem.class.getSimpleName() + "[", "]")
                .add("expenseItemType=" + expenseItemType)
                .add("time=" + time)
                .add("sums=" + sums)
                .add("reason='" + reason + "'")
                .toString();
    }
}
