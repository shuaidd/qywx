package com.github.shuaidd.client;

import com.github.shuaidd.resquest.school.BatchParentOperationRequest;
import com.github.shuaidd.resquest.school.CreateSchoolDepartmentRequest;
import com.github.shuaidd.resquest.school.BatchStudentOperationRequest;
import com.github.shuaidd.resquest.school.CreateParentRequest;
import com.github.shuaidd.resquest.school.CreateStudentRequest;
import com.github.shuaidd.resquest.school.SetArchSyncModeRequest;
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
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 家校沟通服务端接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public interface SchoolClient extends CommonClient {

    /**
     * 获取「学校通知」二维码
     *
     * @param app 应用名
     * @return 二维码链接
     */
    @RequestLine("GET /externalcontact/get_subscribe_qr_code?app={app}")
    @Headers(HEAD)
    SchoolNotificationQrCodeResponse getNotificationQrCode(@Param("app") String app);

    /**
     * 设置关注「学校通知」的模式
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /externalcontact/set_subscribe_mode?app={app}")
    @Headers(HEAD)
    BaseResponse setNotificationMode(SetSchoolNotificationModeRequest request, @Param("app") String app);

    /**
     * 获取关注「学校通知」的模式
     *
     * @param app 应用名
     * @return 关注模式
     */
    @RequestLine("GET /externalcontact/get_subscribe_mode?app={app}")
    @Headers(HEAD)
    SchoolNotificationModeResponse getNotificationMode(@Param("app") String app);

    /**
     * 将微信外部联系人userid转为openid
     *
     * @param request 请求体
     * @param app     应用名
     * @return openid
     */
    @RequestLine("POST /externalcontact/convert_to_openid?app={app}")
    @Headers(HEAD)
    ExternalUserOpenIdResponse convertExternalUserToOpenId(ConvertExternalUserToOpenIdRequest request, @Param("app") String app);

    /**
     * 获取班级群创建方式
     *
     * @param app 应用名
     * @return 创建方式
     */
    @RequestLine("GET /school/get_chat_create_mode?app={app}")
    @Headers(HEAD)
    ChatCreateModeResponse getChatCreateMode(@Param("app") String app);

    /**
     * 设置班级群创建方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /school/set_chat_create_mode?app={app}")
    @Headers(HEAD)
    BaseResponse setChatCreateMode(SetChatCreateModeRequest request, @Param("app") String app);

    /**
     * 获取可在「学校通知-学校应用」使用应用的家长范围
     *
     * @param agentid 应用id
     * @param app     应用名
     * @return 可用范围
     */
    @RequestLine("GET /school/agent/get_allow_scope?agentid={agentid}&app={app}")
    @Headers(HEAD)
    SchoolAgentAllowScopeResponse getAgentAllowScope(@Param("agentid") Integer agentid, @Param("app") String app);

    /**
     * 获取家校访问用户身份
     *
     * @param code 授权码
     * @param app  应用名
     * @return 家长或学生身份
     */
    @RequestLine("GET /school/getuserinfo?code={code}&app={app}")
    @Headers(HEAD)
    SchoolUserInfoResponse getSchoolUserInfo(@Param("code") String code, @Param("app") String app);
    /**
     * 创建学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /school/user/create_student?app={app}")
    @Headers(HEAD)
    BaseResponse createStudent(CreateStudentRequest request, @Param("app") String app);

    /**
     * 删除学生
     *
     * @param userid 家校通讯录学生userid
     * @param app    应用名
     * @return 基础响应
     */
    @RequestLine("GET /school/user/delete_student?userid={userid}&app={app}")
    @Headers(HEAD)
    BaseResponse deleteStudent(@Param("userid") String userid, @Param("app") String app);

    /**
     * 更新学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /school/user/update_student?app={app}")
    @Headers(HEAD)
    BaseResponse updateStudent(UpdateStudentRequest request, @Param("app") String app);

    /**
     * 批量创建学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @RequestLine("POST /school/user/batch_create_student?app={app}")
    @Headers(HEAD)
    BatchStudentResultResponse batchCreateStudent(BatchStudentOperationRequest request, @Param("app") String app);

    /**
     * 批量删除学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @RequestLine("POST /school/user/batch_delete_student?app={app}")
    @Headers(HEAD)
    BatchStudentResultResponse batchDeleteStudent(UserIdListRequest request, @Param("app") String app);

    /**
     * 批量更新学生
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @RequestLine("POST /school/user/batch_update_student?app={app}")
    @Headers(HEAD)
    BatchStudentResultResponse batchUpdateStudent(BatchStudentOperationRequest request, @Param("app") String app);

    /**
     * 创建家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /school/user/create_parent?app={app}")
    @Headers(HEAD)
    BaseResponse createParent(CreateParentRequest request, @Param("app") String app);

    /**
     * 删除家长
     *
     * @param userid 家校通讯录家长userid
     * @param app    应用名
     * @return 基础响应
     */
    @RequestLine("GET /school/user/delete_parent?userid={userid}&app={app}")
    @Headers(HEAD)
    BaseResponse deleteParent(@Param("userid") String userid, @Param("app") String app);

    /**
     * 更新家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /school/user/update_parent?app={app}")
    @Headers(HEAD)
    BaseResponse updateParent(UpdateParentRequest request, @Param("app") String app);

    /**
     * 批量创建家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @RequestLine("POST /school/user/batch_create_parent?app={app}")
    @Headers(HEAD)
    BatchParentResultResponse batchCreateParent(BatchParentOperationRequest request, @Param("app") String app);

    /**
     * 批量删除家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @RequestLine("POST /school/user/batch_delete_parent?app={app}")
    @Headers(HEAD)
    BatchParentResultResponse batchDeleteParent(UserIdListRequest request, @Param("app") String app);

    /**
     * 批量更新家长
     *
     * @param request 请求体
     * @param app     应用名
     * @return 批量结果
     */
    @RequestLine("POST /school/user/batch_update_parent?app={app}")
    @Headers(HEAD)
    BatchParentResultResponse batchUpdateParent(BatchParentOperationRequest request, @Param("app") String app);

    /**
     * 获取家校通讯录成员详情
     *
     * @param userid 家长或学生的userid
     * @param app    应用名
     * @return 用户详情
     */
    @RequestLine("GET /school/user/get?userid={userid}&app={app}")
    @Headers(HEAD)
    SchoolUserDetailResponse getSchoolUser(@Param("userid") String userid, @Param("app") String app);

    /**
     * 获取班级下的学生列表
     *
     * @param departmentId 家校部门id
     * @param app          应用名
     * @return 学生列表
     */
    @RequestLine("GET /school/user/list?department_id={department_id}&app={app}")
    @Headers(HEAD)
    StudentListResponse listStudents(@Param("department_id") Integer departmentId, @Param("app") String app);

    /**
     * 设置家校通讯录自动同步模式
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /school/set_arch_sync_mode?app={app}")
    @Headers(HEAD)
    BaseResponse setArchSyncMode(SetArchSyncModeRequest request, @Param("app") String app);

    /**
     * 获取班级下的家长列表
     *
     * @param departmentId 家校部门id
     * @param app          应用名
     * @return 家长列表
     */
    @RequestLine("GET /school/user/list_parent?department_id={department_id}&app={app}")
    @Headers(HEAD)
    ParentListResponse listParents(@Param("department_id") Integer departmentId, @Param("app") String app);

    /**
     * 创建家校部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return 创建结果
     */
    @RequestLine("POST /school/department/create?app={app}")
    @Headers(HEAD)
    CreateSchoolDepartmentResponse createSchoolDepartment(CreateSchoolDepartmentRequest request, @Param("app") String app);

    /**
     * 更新家校部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /school/department/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateSchoolDepartment(UpdateSchoolDepartmentRequest request, @Param("app") String app);

    /**
     * 删除家校部门
     *
     * @param id  部门id
     * @param app 应用名
     * @return 基础响应
     */
    @RequestLine("GET /school/department/delete?id={id}&app={app}")
    @Headers(HEAD)
    BaseResponse deleteSchoolDepartment(@Param("id") Integer id, @Param("app") String app);

    /**
     * 获取家校部门列表
     *
     * @param id  部门id；不填获取全量组织架构
     * @param app 应用名
     * @return 部门列表
     */
    @RequestLine("GET /school/department/list?id={id}&app={app}")
    @Headers(HEAD)
    SchoolDepartmentListResponse listSchoolDepartments(@Param("id") Integer id, @Param("app") String app);

    /**
     * 获取健康上报使用统计
     *
     * @param request 请求体
     * @param app     应用名
     * @return 使用统计
     */
    @RequestLine("POST /health/get_health_report_stat?app={app}")
    @Headers(HEAD)
    HealthReportStatResponse getHealthReportStat(HealthReportStatRequest request, @Param("app") String app);

    /**
     * 获取健康上报任务id列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return 任务id列表
     */
    @RequestLine("POST /health/get_report_jobids?app={app}")
    @Headers(HEAD)
    HealthReportJobIdsResponse getReportJobIds(HealthReportJobIdsRequest request, @Param("app") String app);

    /**
     * 获取指定的健康上报任务详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return 任务详情
     */
    @RequestLine("POST /health/get_report_job_info?app={app}")
    @Headers(HEAD)
    HealthReportJobInfoResponse getReportJobInfo(HealthReportJobInfoRequest request, @Param("app") String app);

    /**
     * 获取指定的健康上报任务填写答案
     *
     * @param request 请求体
     * @param app     应用名
     * @return 填写答案列表
     */
    @RequestLine("POST /health/get_report_answer?app={app}")
    @Headers(HEAD)
    HealthReportAnswerResponse getReportAnswer(HealthReportAnswerRequest request, @Param("app") String app);

    /**
     * 获取指定老师的所有直播id
     *
     * @param request 请求体
     * @param app     应用名
     * @return 直播id列表
     */
    @RequestLine("POST /living/get_user_all_livingid?app={app}")
    @Headers(HEAD)
    LivingListResponse getUserAllLivingId(LivingListRequest request, @Param("app") String app);

    /**
     * 获取上课直播详情
     *
     * @param livingid 直播id
     * @param app      应用名
     * @return 直播详情
     */
    @RequestLine("GET /school/living/get_living_info?livingid={livingid}&app={app}")
    @Headers(HEAD)
    LivingInfoResponse getLivingInfo(@Param("livingid") String livingid, @Param("app") String app);

    /**
     * 获取所有观看直播的人员统计（v1分页协议）
     *
     * @param request 请求体
     * @param app     应用名
     * @return 观看统计
     */
    @RequestLine("POST /school/living/get_watch_stat?app={app}")
    @Headers(HEAD)
    LivingWatchStatResponse getWatchStat(LivingStatV1Request request, @Param("app") String app);

    /**
     * 获取未观看直播的学生统计（v1分页协议）
     *
     * @param request 请求体
     * @param app     应用名
     * @return 未观看统计
     */
    @RequestLine("POST /school/living/get_unwatch_stat?app={app}")
    @Headers(HEAD)
    LivingUnwatchStatResponse getUnwatchStat(LivingStatV1Request request, @Param("app") String app);

    /**
     * 删除直播回放数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /living/delete_replay_data?app={app}")
    @Headers(HEAD)
    BaseResponse deleteReplayData(DeleteLivingReplayRequest request, @Param("app") String app);

    /**
     * 获取所有观看直播的人员统计（v2分页协议）
     *
     * @param request 请求体
     * @param app     应用名
     * @return 观看统计
     */
    @RequestLine("POST /school/living/get_watch_stat_v2?app={app}")
    @Headers(HEAD)
    LivingWatchStatV2Response getWatchStatV2(LivingStatV2Request request, @Param("app") String app);

    /**
     * 获取未观看直播的学生和家长统计（v2分页协议）
     *
     * @param request 请求体
     * @param app     应用名
     * @return 未观看统计
     */
    @RequestLine("POST /school/living/get_unwatch_stat_v2?app={app}")
    @Headers(HEAD)
    LivingUnwatchStatV2Response getUnwatchStatV2(LivingStatV2Request request, @Param("app") String app);

    /**
     * 获取班级收款项目付款结果
     *
     * @param request 请求体
     * @param app     应用名
     * @return 付款结果
     */
    @RequestLine("POST /school/get_payment_result?app={app}")
    @Headers(HEAD)
    PaymentResultResponse getPaymentResult(PaymentResultRequest request, @Param("app") String app);

    /**
     * 获取班级收款订单详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return 订单详情
     */
    @RequestLine("POST /school/get_trade?app={app}")
    @Headers(HEAD)
    TradeResponse getTrade(TradeQueryRequest request, @Param("app") String app);
}
