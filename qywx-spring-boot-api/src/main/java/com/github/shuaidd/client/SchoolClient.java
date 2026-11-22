package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.resquest.school.BatchParentOperationRequest;
import com.github.shuaidd.resquest.school.CreateSchoolDepartmentRequest;
import com.github.shuaidd.resquest.school.BatchStudentOperationRequest;
import com.github.shuaidd.resquest.school.CreateParentRequest;
import com.github.shuaidd.resquest.school.CreateStudentRequest;
import com.github.shuaidd.resquest.school.ParentChildItem;
import com.github.shuaidd.resquest.school.ParentItem;
import com.github.shuaidd.resquest.school.SetArchSyncModeRequest;
import com.github.shuaidd.resquest.school.StudentItem;
import com.github.shuaidd.resquest.school.UpdateParentRequest;
import com.github.shuaidd.resquest.school.UpdateStudentRequest;
import com.github.shuaidd.resquest.school.UpdateSchoolDepartmentRequest;
import com.github.shuaidd.resquest.school.HealthReportStatRequest;
import com.github.shuaidd.resquest.school.HealthReportJobIdsRequest;
import com.github.shuaidd.resquest.school.HealthReportJobInfoRequest;
import com.github.shuaidd.resquest.school.HealthReportAnswerRequest;
import com.github.shuaidd.resquest.school.LivingListRequest;
import com.github.shuaidd.resquest.school.LivingStatV1Request;
import com.github.shuaidd.resquest.school.LivingStatV2Request;
import com.github.shuaidd.resquest.school.DeleteLivingReplayRequest;
import com.github.shuaidd.resquest.school.PaymentResultRequest;
import com.github.shuaidd.resquest.school.TradeQueryRequest;
import com.github.shuaidd.resquest.school.UserIdListRequest;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.school.BatchParentResultResponse;
import com.github.shuaidd.response.school.CreateSchoolDepartmentResponse;
import com.github.shuaidd.response.school.BatchStudentResultResponse;
import com.github.shuaidd.response.school.ChatCreateModeResponse;
import com.github.shuaidd.response.school.ParentListResponse;
import com.github.shuaidd.response.school.SchoolDepartmentListResponse;
import com.github.shuaidd.response.school.HealthReportStatResponse;
import com.github.shuaidd.response.school.HealthReportJobIdsResponse;
import com.github.shuaidd.response.school.HealthReportJobInfoResponse;
import com.github.shuaidd.response.school.HealthReportAnswerResponse;
import com.github.shuaidd.response.school.LivingListResponse;
import com.github.shuaidd.response.school.LivingInfoResponse;
import com.github.shuaidd.response.school.LivingWatchStatResponse;
import com.github.shuaidd.response.school.LivingUnwatchStatResponse;
import com.github.shuaidd.response.school.LivingWatchStatV2Response;
import com.github.shuaidd.response.school.LivingUnwatchStatV2Response;
import com.github.shuaidd.response.school.PaymentResultResponse;
import com.github.shuaidd.response.school.TradeResponse;
import com.github.shuaidd.response.school.SchoolUserDetailResponse;
import com.github.shuaidd.response.school.StudentListResponse;
import com.github.shuaidd.response.school.ExternalUserOpenIdResponse;
import com.github.shuaidd.response.school.SchoolAgentAllowScopeResponse;
import com.github.shuaidd.response.school.SchoolNotificationModeResponse;
import com.github.shuaidd.response.school.SchoolNotificationQrCodeResponse;
import com.github.shuaidd.response.school.SchoolUserInfoResponse;
import com.github.shuaidd.resquest.school.ConvertExternalUserToOpenIdRequest;
import com.github.shuaidd.resquest.school.SetChatCreateModeRequest;
import com.github.shuaidd.resquest.school.SetSchoolNotificationModeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 家校沟通服务端接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "SchoolClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface SchoolClient extends CommonClient {

    /**
     * 获取「学校通知」二维码
     *
     * @param app 应用名
     * @return 二维码链接
     */
    @GetMapping(value = "externalcontact/get_subscribe_qr_code", headers = HEAD)
    SchoolNotificationQrCodeResponse getNotificationQrCode(@RequestParam(HEAD_KEY) String app);

    /**
     * 设置关注「学校通知」的模式
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "externalcontact/set_subscribe_mode", headers = HEAD)
    BaseResponse setNotificationMode(SetSchoolNotificationModeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取关注「学校通知」的模式
     *
     * @param app 应用名
     * @return 关注模式
     */
    @GetMapping(value = "externalcontact/get_subscribe_mode", headers = HEAD)
    SchoolNotificationModeResponse getNotificationMode(@RequestParam(HEAD_KEY) String app);

    /**
     * 将微信外部联系人userid转为openid
     *
     * @param request 请求体
     * @param app     应用名
     * @return openid
     */
    @PostMapping(value = "externalcontact/convert_to_openid", headers = HEAD)
    ExternalUserOpenIdResponse convertExternalUserToOpenId(ConvertExternalUserToOpenIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取班级群创建方式
     *
     * @param app 应用名
     * @return 创建方式
     */
    @GetMapping(value = "school/get_chat_create_mode", headers = HEAD)
    ChatCreateModeResponse getChatCreateMode(@RequestParam(HEAD_KEY) String app);

    /**
     * 设置班级群创建方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "school/set_chat_create_mode", headers = HEAD)
    BaseResponse setChatCreateMode(SetChatCreateModeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取可在「学校通知-学校应用」使用应用的家长范围
     *
     * @param agentid 应用id
     * @param app     应用名
     * @return 可用范围
     */
    @GetMapping(value = "school/agent/get_allow_scope", headers = HEAD)
    SchoolAgentAllowScopeResponse getAgentAllowScope(@RequestParam("agentid") Integer agentid, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取家校访问用户身份
     *
     * @param code 授权码
     * @param app  应用名
     * @return 家长或学生身份
     */
    @GetMapping(value = "school/getuserinfo", headers = HEAD)
    SchoolUserInfoResponse getSchoolUserInfo(@RequestParam("code") String code, @RequestParam(HEAD_KEY) String app);
    /**
     * 创建学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "school/user/create_student", headers = HEAD)
    BaseResponse createStudent(CreateStudentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除学生
     *
     * @param userid 家校通讯录学生userid
     * @param app    应用名
     * @return 基础响应
     */
    @GetMapping(value = "school/user/delete_student", headers = HEAD)
    BaseResponse deleteStudent(@RequestParam("userid") String userid, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "school/user/update_student", headers = HEAD)
    BaseResponse updateStudent(UpdateStudentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量创建学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @PostMapping(value = "school/user/batch_create_student", headers = HEAD)
    BatchStudentResultResponse batchCreateStudent(BatchStudentOperationRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量删除学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @PostMapping(value = "school/user/batch_delete_student", headers = HEAD)
    BatchStudentResultResponse batchDeleteStudent(UserIdListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量更新学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @PostMapping(value = "school/user/batch_update_student", headers = HEAD)
    BatchStudentResultResponse batchUpdateStudent(BatchStudentOperationRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "school/user/create_parent", headers = HEAD)
    BaseResponse createParent(CreateParentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除家长
     *
     * @param userid 家校通讯录家长userid
     * @param app    应用名
     * @return 基础响应
     */
    @GetMapping(value = "school/user/delete_parent", headers = HEAD)
    BaseResponse deleteParent(@RequestParam("userid") String userid, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "school/user/update_parent", headers = HEAD)
    BaseResponse updateParent(UpdateParentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量创建家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @PostMapping(value = "school/user/batch_create_parent", headers = HEAD)
    BatchParentResultResponse batchCreateParent(BatchParentOperationRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量删除家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @PostMapping(value = "school/user/batch_delete_parent", headers = HEAD)
    BatchParentResultResponse batchDeleteParent(UserIdListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量更新家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @PostMapping(value = "school/user/batch_update_parent", headers = HEAD)
    BatchParentResultResponse batchUpdateParent(BatchParentOperationRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取家校通讯录成员详情
     *
     * @param userid 家长或学生的userid
     * @param app    应用名
     * @return 用户详情
     */
    @GetMapping(value = "school/user/get", headers = HEAD)
    SchoolUserDetailResponse getSchoolUser(@RequestParam("userid") String userid, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取班级下的学生列表
     *
     * @param departmentId 家校部门id
     * @param app          应用名
     * @return 学生列表
     */
    @GetMapping(value = "school/user/list", headers = HEAD)
    StudentListResponse listStudents(@RequestParam("department_id") Integer departmentId, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置家校通讯录自动同步模式
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "school/set_arch_sync_mode", headers = HEAD)
    BaseResponse setArchSyncMode(SetArchSyncModeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取班级下的家长列表
     *
     * @param departmentId 家校部门id
     * @param app          应用名
     * @return 家长列表
     */
    @GetMapping(value = "school/user/list_parent", headers = HEAD)
    ParentListResponse listParents(@RequestParam("department_id") Integer departmentId, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建家校部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return 创建结果
     */
    @PostMapping(value = "school/department/create", headers = HEAD)
    CreateSchoolDepartmentResponse createSchoolDepartment(CreateSchoolDepartmentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新家校部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "school/department/update", headers = HEAD)
    BaseResponse updateSchoolDepartment(UpdateSchoolDepartmentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除家校部门
     *
     * @param id  部门id
     * @param app 应用名
     * @return 基础响应
     */
    @GetMapping(value = "school/department/delete", headers = HEAD)
    BaseResponse deleteSchoolDepartment(@RequestParam("id") Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取家校部门列表
     *
     * @param id  部门id；不填获取全量组织架构
     * @param app 应用名
     * @return 部门列表
     */
    @GetMapping(value = "school/department/list", headers = HEAD)
    SchoolDepartmentListResponse listSchoolDepartments(@RequestParam(value = "id", required = false) Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取健康上报使用统计
     *
     * @param request 请求体
     * @param app     应用名
     * @return 使用统计
     */
    @PostMapping(value = "health/get_health_report_stat", headers = HEAD)
    HealthReportStatResponse getHealthReportStat(HealthReportStatRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取健康上报任务id列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return 任务id列表
     */
    @PostMapping(value = "health/get_report_jobids", headers = HEAD)
    HealthReportJobIdsResponse getReportJobIds(HealthReportJobIdsRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取指定的健康上报任务详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return 任务详情
     */
    @PostMapping(value = "health/get_report_job_info", headers = HEAD)
    HealthReportJobInfoResponse getReportJobInfo(HealthReportJobInfoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取指定的健康上报任务填写答案
     *
     * @param request 请求体
     * @param app     应用名
     * @return 填写答案列表
     */
    @PostMapping(value = "health/get_report_answer", headers = HEAD)
    HealthReportAnswerResponse getReportAnswer(HealthReportAnswerRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取指定老师的所有直播id
     *
     * @param request 请求体
     * @param app     应用名
     * @return 直播id列表
     */
    @PostMapping(value = "living/get_user_all_livingid", headers = HEAD)
    LivingListResponse getUserAllLivingId(LivingListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取上课直播详情
     *
     * @param livingid 直播id
     * @param app      应用名
     * @return 直播详情
     */
    @GetMapping(value = "school/living/get_living_info", headers = HEAD)
    LivingInfoResponse getLivingInfo(@RequestParam("livingid") String livingid, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取所有观看直播的人员统计（v1分页协议）
     *
     * @param request 请求体
     * @param app     应用名
     * @return 观看统计
     */
    @PostMapping(value = "school/living/get_watch_stat", headers = HEAD)
    LivingWatchStatResponse getWatchStat(LivingStatV1Request request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取未观看直播的学生统计（v1分页协议）
     *
     * @param request 请求体
     * @param app     应用名
     * @return 未观看统计
     */
    @PostMapping(value = "school/living/get_unwatch_stat", headers = HEAD)
    LivingUnwatchStatResponse getUnwatchStat(LivingStatV1Request request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除直播回放数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "living/delete_replay_data", headers = HEAD)
    BaseResponse deleteReplayData(DeleteLivingReplayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取所有观看直播的人员统计（v2分页协议）
     *
     * @param request 请求体
     * @param app     应用名
     * @return 观看统计
     */
    @PostMapping(value = "school/living/get_watch_stat_v2", headers = HEAD)
    LivingWatchStatV2Response getWatchStatV2(LivingStatV2Request request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取未观看直播的学生和家长统计（v2分页协议）
     *
     * @param request 请求体
     * @param app     应用名
     * @return 未观看统计
     */
    @PostMapping(value = "school/living/get_unwatch_stat_v2", headers = HEAD)
    LivingUnwatchStatV2Response getUnwatchStatV2(LivingStatV2Request request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取班级收款项目付款结果
     *
     * @param request 请求体
     * @param app     应用名
     * @return 付款结果
     */
    @PostMapping(value = "school/get_payment_result", headers = HEAD)
    PaymentResultResponse getPaymentResult(PaymentResultRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取班级收款订单详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return 订单详情
     */
    @PostMapping(value = "school/get_trade", headers = HEAD)
    TradeResponse getTrade(TradeQueryRequest request, @RequestParam(HEAD_KEY) String app);
}
