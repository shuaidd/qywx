package com.github.shuaidd.client;

import com.github.shuaidd.response.hr.StaffFieldConfigResponse;
import com.github.shuaidd.response.hr.StaffInfoResponse;
import com.github.shuaidd.response.hr.UpdateStaffInfoResponse;
import com.github.shuaidd.resquest.hr.GetStaffInfoRequest;
import com.github.shuaidd.resquest.hr.UpdateStaffInfoRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 人事助手花名册接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public interface HrClient extends CommonClient {

    /**
     * 获取员工字段配置
     *
     * @param app 应用名
     * @return 字段配置
     */
    @RequestLine("GET /hr/get_fields?app={app}")
    @Headers(HEAD)
    StaffFieldConfigResponse getStaffFields(@Param("app") String app);

    /**
     * 获取员工花名册信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 员工花名册信息
     */
    @RequestLine("POST /hr/get_staff_info?app={app}")
    @Headers(HEAD)
    StaffInfoResponse getStaffInfo(GetStaffInfoRequest request, @Param("app") String app);

    /**
     * 更新员工花名册信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 更新结果
     */
    @RequestLine("POST /hr/update_staff_info?app={app}")
    @Headers(HEAD)
    UpdateStaffInfoResponse updateStaffInfo(UpdateStaffInfoRequest request, @Param("app") String app);
}
