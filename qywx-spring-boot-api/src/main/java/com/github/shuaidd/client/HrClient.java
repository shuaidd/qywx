package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.hr.StaffFieldConfigResponse;
import com.github.shuaidd.response.hr.StaffInfoResponse;
import com.github.shuaidd.response.hr.UpdateStaffInfoResponse;
import com.github.shuaidd.resquest.hr.GetStaffInfoRequest;
import com.github.shuaidd.resquest.hr.UpdateStaffInfoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 人事助手花名册接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "HrClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface HrClient extends CommonClient {

    /**
     * 获取员工字段配置
     *
     * @param app 应用名
     * @return 字段配置
     */
    @GetMapping(value = "hr/get_fields", headers = HEAD)
    StaffFieldConfigResponse getStaffFields(@RequestParam(HEAD_KEY) String app);

    /**
     * 获取员工花名册信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 员工花名册信息
     */
    @PostMapping(value = "hr/get_staff_info", headers = HEAD)
    StaffInfoResponse getStaffInfo(GetStaffInfoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新员工花名册信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 更新结果
     */
    @PostMapping(value = "hr/update_staff_info", headers = HEAD)
    UpdateStaffInfoResponse updateStaffInfo(UpdateStaffInfoRequest request, @RequestParam(HEAD_KEY) String app);
}
