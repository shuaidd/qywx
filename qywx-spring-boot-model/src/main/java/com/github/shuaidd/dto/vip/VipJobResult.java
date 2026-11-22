package com.github.shuaidd.dto.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 高级功能账号批量任务执行结果
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class VipJobResult {
    /**
     * 分配或撤销分配成功的userid列表
     **/
    @JsonProperty("succ_userid_list")
    private List<String> succUseridList;

    /**
     * 分配或撤销分配失败的userid列表
     **/
    @JsonProperty("fail_userid_list")
    private List<String> failUseridList;
}
