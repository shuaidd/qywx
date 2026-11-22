package com.github.shuaidd.resquest.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.hr.HrFileValue;
import com.github.shuaidd.dto.hr.HrMobileValue;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 员工花名册字段内容
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class StaffFieldValue {
    /**
     * 员工字段id
     **/
    @JsonProperty("fieldid")
    private Integer fieldId;

    /**
     * 可重复字段组中的字段下标；非可重复字段组填0
     **/
    @JsonProperty("sub_idx")
    private Integer subIdx;

    /**
     * 字符串类型字段值
     **/
    @JsonProperty("value_string")
    private String valueString;

    /**
     * 64位非负整数类型字段值
     **/
    @JsonProperty("value_uint64")
    private Long valueUint64;

    /**
     * 32位非负整数类型字段值
     **/
    @JsonProperty("value_uint32")
    private Integer valueUint32;

    /**
     * 64位整数类型字段值
     **/
    @JsonProperty("value_int64")
    private Long valueInt64;

    /**
     * 电话号码类型字段值
     **/
    @JsonProperty("value_mobile")
    private HrMobileValue valueMobile;

    /**
     * 文件类型字段值
     **/
    @JsonProperty("value_file")
    private HrFileValue valueFile;
}
