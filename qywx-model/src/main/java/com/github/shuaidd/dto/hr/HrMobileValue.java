package com.github.shuaidd.dto.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 花名册电话号码字段值
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class HrMobileValue {
    /**
     * 电话号码区号
     **/
    @JsonProperty("value_country_code")
    private String valueCountryCode;

    /**
     * 电话号码
     **/
    @JsonProperty("value_mobile")
    private String valueMobile;
}
