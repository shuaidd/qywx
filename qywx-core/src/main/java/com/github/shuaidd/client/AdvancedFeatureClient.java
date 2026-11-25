package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.advanced.ApprovalInfoResponse;
import com.github.shuaidd.response.advanced.ApplyIdListResponse;
import com.github.shuaidd.resquest.advanced.GetApplyIdListRequest;
import com.github.shuaidd.resquest.advanced.GetApprovalInfoRequest;
import com.github.shuaidd.resquest.advanced.SetApprovalDetailRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 高级功能成员申请管理接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public interface AdvancedFeatureClient extends CommonClient {

    /**
     * 设置审批单审批信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /advanced_feature/set_approval_detail?app={app}")
    @Headers(HEAD)
    BaseResponse setApprovalDetail(SetApprovalDetailRequest request, @Param("app") String app);

    /**
     * 批量获取申请单ID
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单ID列表
     */
    @RequestLine("POST /advanced_feature/get_apply_id_list?app={app}")
    @Headers(HEAD)
    ApplyIdListResponse getApplyIdList(GetApplyIdListRequest request, @Param("app") String app);

    /**
     * 获取申请单详细信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单详细信息
     */
    @RequestLine("POST /advanced_feature/get_approval_info?app={app}")
    @Headers(HEAD)
    ApprovalInfoResponse getApprovalInfo(GetApprovalInfoRequest request, @Param("app") String app);
}
