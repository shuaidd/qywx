package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.advanced.ApprovalInfoResponse;
import com.github.shuaidd.response.advanced.ApplyIdListResponse;
import com.github.shuaidd.resquest.advanced.GetApplyIdListRequest;
import com.github.shuaidd.resquest.advanced.GetApprovalInfoRequest;
import com.github.shuaidd.resquest.advanced.SetApprovalDetailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 高级功能成员申请管理接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "AdvancedFeatureClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface AdvancedFeatureClient extends CommonClient {

    /**
     * 设置审批单审批信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "advanced_feature/set_approval_detail", headers = HEAD)
    BaseResponse setApprovalDetail(SetApprovalDetailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量获取申请单ID
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单ID列表
     */
    @PostMapping(value = "advanced_feature/get_apply_id_list", headers = HEAD)
    ApplyIdListResponse getApplyIdList(GetApplyIdListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取申请单详细信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单详细信息
     */
    @PostMapping(value = "advanced_feature/get_approval_info", headers = HEAD)
    ApprovalInfoResponse getApprovalInfo(GetApprovalInfoRequest request, @RequestParam(HEAD_KEY) String app);
}