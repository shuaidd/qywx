package com.github.shuaidd.resquest.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 更新员工花名册信息请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class UpdateStaffInfoRequest {
    /**
     * 需要更新花名册信息的员工userid
     **/
    @JsonProperty("userid")
    private String userid;

    /**
     * 需要更新、新增或清空内容的字段列表
     **/
    @JsonProperty("update_items")
    private List<StaffFieldValue> updateItems;

    /**
     * 需要整组删除的可重复字段组列表
     **/
    @JsonProperty("remove_items")
    private List<StaffGroupDelete> removeItems;

    /**
     * 需要整组新增的可重复字段组列表
     **/
    @JsonProperty("insert_items")
    private List<StaffGroupInsert> insertItems;
}
