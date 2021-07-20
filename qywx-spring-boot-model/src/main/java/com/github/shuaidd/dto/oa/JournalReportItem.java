package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UserId;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 10:17
 **/
public class JournalReportItem {

    private UserId user;

    @JsonProperty("itemlist")
    private List<JournalReportItemDetail> itemList;

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }

    public List<JournalReportItemDetail> getItemList() {
        return itemList;
    }

    public void setItemList(List<JournalReportItemDetail> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JournalReportItem.class.getSimpleName() + "[", "]")
                .add("user=" + user)
                .add("itemList=" + itemList)
                .toString();
    }
}
