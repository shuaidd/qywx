package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-11 13:10
 **/
public class Expense {

    @JsonProperty("expense_type")
    private Integer expenseType;

    private String reason;

    @JsonProperty("item")
    private List<ExpenseItem> items;

    public Integer getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(Integer expenseType) {
        this.expenseType = expenseType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ExpenseItem> getItems() {
        return items;
    }

    public void setItems(List<ExpenseItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Expense.class.getSimpleName() + "[", "]")
                .add("expenseType=" + expenseType)
                .add("reason='" + reason + "'")
                .add("items=" + items)
                .toString();
    }
}
