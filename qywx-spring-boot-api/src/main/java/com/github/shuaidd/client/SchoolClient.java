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
import com.github.shuaidd.resquest.school.UserIdListRequest;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.school.BatchParentResultResponse;
import com.github.shuaidd.response.school.CreateSchoolDepartmentResponse;
import com.github.shuaidd.response.school.BatchStudentResultResponse;
import com.github.shuaidd.response.school.ChatCreateModeResponse;
import com.github.shuaidd.response.school.ParentListResponse;
import com.github.shuaidd.response.school.SchoolDepartmentListResponse;
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
}
