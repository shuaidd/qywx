package com.github.shuaidd.client;

import com.github.shuaidd.response.linkedcorp.LinkedCorpDeptResponse;
import com.github.shuaidd.response.linkedcorp.LinkedCorpDeptUserListResponse;
import com.github.shuaidd.response.linkedcorp.LinkedCorpDeptUserResponse;
import com.github.shuaidd.response.linkedcorp.LinkedCorpUserResponse;
import com.github.shuaidd.response.linkedcorp.PermListResponse;
import com.github.shuaidd.resquest.linkedcorp.LinkedCorpDeptRequest;
import com.github.shuaidd.resquest.linkedcorp.LinkedCropUserRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 互联企业管理
 *
 * @author ddshuai
 * date 2022-09-04 21:19
 **/
public interface LinkedCorpClient extends CommonClient {

    /**
     * 获取应用的可见范围
     * 本接口只返回互联企业中非本企业内的成员和部门的信息
     *
     * @param app 应用
     * @return 范围信息
     */
    @RequestLine("POST /linkedcorp/agent/get_perm_list?app={app}")
    @Headers(HEAD)
    PermListResponse getPermList(@Param("app") String app);

    /**
     * 获取互联企业成员详细信息
     *
     * @param request 请求
     * @param app     应用
     * @return 成员详情
     */
    @RequestLine("POST /linkedcorp/user/get?app={app}")
    @Headers(HEAD)
    LinkedCorpUserResponse getUserDetail(LinkedCropUserRequest request, @Param("app") String app);

    /**
     * 获取互联企业部门成员
     *
     * @param request 请求
     * @param app     应用
     * @return 部门成员详情
     */
    @RequestLine("POST /linkedcorp/user/simplelist?app={app}")
    @Headers(HEAD)
    LinkedCorpDeptUserResponse getDeptUser(LinkedCorpDeptRequest request, @Param("app") String app);

    /**
     * 获取互联企业部门成员详情
     *
     * @param request 请求
     * @param app     应用
     * @return 部门成员详情
     */
    @RequestLine("POST /linkedcorp/user/list?app={app}")
    @Headers(HEAD)
    LinkedCorpDeptUserListResponse getDeptUserList(LinkedCorpDeptRequest request, @Param("app") String app);

    /**
     * 获取互联企业部门列表
     *
     * @param request 请求
     * @param app     应用
     * @return 部门成员详情
     */
    @RequestLine("POST /linkedcorp/department/list?app={app}")
    @Headers(HEAD)
    LinkedCorpDeptResponse getDeptList(LinkedCorpDeptRequest request, @Param("app") String app);
}
