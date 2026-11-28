package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 高级功能账号批量操作请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingVipUserBatchRequest {
    /**
     * 要分配或撤销分配高级功能的企业成员userid列表
     **/
    @JsonProperty("userid_list")
    private List<String> useridList;
}
