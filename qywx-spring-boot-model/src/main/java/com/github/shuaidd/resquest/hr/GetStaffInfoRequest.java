package com.github.shuaidd.resquest.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 获取员工花名册信息请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class GetStaffInfoRequest {
    /**
     * 需要获取花名册信息的员工userid
     **/
    @JsonProperty("userid")
    private String userid;

    /**
     * 是否获取全部字段信息；不填默认为否
     **/
    @JsonProperty("get_all")
    private Boolean getAll;

    /**
     * 需要获取的字段信息；get_all为false或不填时不能为空
     **/
    @JsonProperty("fieldids")
    private List<StaffFieldId> fieldIds;
}
