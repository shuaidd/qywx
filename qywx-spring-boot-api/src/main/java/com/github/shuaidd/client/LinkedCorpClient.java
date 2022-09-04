package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.linkedcorp.*;
import com.github.shuaidd.resquest.linkedcorp.LinkedCorpDeptRequest;
import com.github.shuaidd.resquest.linkedcorp.LinkedCropUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 互联企业管理
 *
 * @author ddshuai
 * date 2022-09-04 21:19
 **/
@FeignClient(name = "LinkedCorpClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface LinkedCorpClient extends CommonClient {

    /**
     * 获取应用的可见范围
     * 本接口只返回互联企业中非本企业内的成员和部门的信息
     *
     * @param app 应用
     * @return 范围信息
     */
    @PostMapping(value = "linkedcorp/agent/get_perm_list", headers = HEAD)
    PermListResponse getPermList(@RequestParam(HEAD_KEY) String app);

    /**
     * 获取互联企业成员详细信息
     *
     * @param request 请求
     * @param app     应用
     * @return 成员详情
     */
    @PostMapping(value = "linkedcorp/user/get", headers = HEAD)
    LinkedCorpUserResponse getUserDetail(LinkedCropUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取互联企业部门成员
     *
     * @param request 请求
     * @param app     应用
     * @return 部门成员详情
     */
    @PostMapping(value = "linkedcorp/user/simplelist", headers = HEAD)
    LinkedCorpDeptUserResponse getDeptUser(LinkedCorpDeptRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取互联企业部门成员详情
     *
     * @param request 请求
     * @param app     应用
     * @return 部门成员详情
     */
    @PostMapping(value = "linkedcorp/user/list", headers = HEAD)
    LinkedCorpDeptUserListResponse getDeptUserList(LinkedCorpDeptRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取互联企业部门列表
     *
     * @param request 请求
     * @param app     应用
     * @return 部门成员详情
     */
    @PostMapping(value = "linkedcorp/department/list", headers = HEAD)
    LinkedCorpDeptResponse getDeptList(LinkedCorpDeptRequest request, @RequestParam(HEAD_KEY) String app);
}
