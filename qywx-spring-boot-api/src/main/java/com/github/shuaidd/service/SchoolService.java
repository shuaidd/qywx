package com.github.shuaidd.service;

import com.github.shuaidd.client.SchoolClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.resquest.school.BatchParentOperationRequest;
import com.github.shuaidd.resquest.school.BatchStudentOperationRequest;
import com.github.shuaidd.resquest.school.ConvertExternalUserToOpenIdRequest;
import com.github.shuaidd.resquest.school.CreateSchoolDepartmentRequest;
import com.github.shuaidd.resquest.school.CreateParentRequest;
import com.github.shuaidd.resquest.school.CreateStudentRequest;
import com.github.shuaidd.resquest.school.ParentChildItem;
import com.github.shuaidd.resquest.school.ParentItem;
import com.github.shuaidd.resquest.school.SchoolDepartmentAdminItem;
import com.github.shuaidd.resquest.school.SchoolDepartmentUpdateAdminItem;
import com.github.shuaidd.resquest.school.SetArchSyncModeRequest;
import com.github.shuaidd.resquest.school.UpdateSchoolDepartmentRequest;
import com.github.shuaidd.resquest.school.StudentItem;
import com.github.shuaidd.resquest.school.UpdateParentRequest;
import com.github.shuaidd.resquest.school.UpdateStudentRequest;
import com.github.shuaidd.resquest.school.UserIdListRequest;
import com.github.shuaidd.resquest.school.SetChatCreateModeRequest;
import com.github.shuaidd.resquest.school.SetSchoolNotificationModeRequest;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.school.BatchParentResultResponse;
import com.github.shuaidd.response.school.BatchStudentResultResponse;
import com.github.shuaidd.response.school.ChatCreateModeResponse;
import com.github.shuaidd.response.school.CreateSchoolDepartmentResponse;
import com.github.shuaidd.response.school.ParentListResponse;
import com.github.shuaidd.response.school.SchoolDepartmentListResponse;
import com.github.shuaidd.response.school.SchoolDepartment;
import com.github.shuaidd.response.school.SchoolUserDetailResponse;
import com.github.shuaidd.response.school.StudentListResponse;
import com.github.shuaidd.response.school.ExternalUserOpenIdResponse;
import com.github.shuaidd.response.school.SchoolAgentAllowScopeResponse;
import com.github.shuaidd.response.school.SchoolNotificationModeResponse;
import com.github.shuaidd.response.school.SchoolNotificationQrCodeResponse;
import com.github.shuaidd.response.school.SchoolUserInfoResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 家校沟通服务。REST 请求前完成基础业务参数校验，真实权限仍由企业微信平台控制。
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Service
public class SchoolService extends AbstractBaseService {

    private final SchoolClient schoolClient;

    public SchoolService(SchoolClient schoolClient) {
        this.schoolClient = schoolClient;
    }

    /**
     * 获取「学校通知」二维码
     *
     * @param applicationName 应用名称
     * @return 二维码链接
     */
    public SchoolNotificationQrCodeResponse getNotificationQrCode(String applicationName) {
        checkApplication(applicationName);
        return schoolClient.getNotificationQrCode(applicationName);
    }

    /**
     * 设置关注「学校通知」的模式
     *
     * @param subscribeMode   关注模式
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setNotificationMode(Integer subscribeMode, String applicationName) {
        checkNotificationMode(subscribeMode);
        SetSchoolNotificationModeRequest request = new SetSchoolNotificationModeRequest();
        request.setSubscribeMode(subscribeMode);
        return schoolClient.setNotificationMode(request, applicationName);
    }

    /**
     * 获取关注「学校通知」的模式
     *
     * @param applicationName 应用名称
     * @return 关注模式
     */
    public SchoolNotificationModeResponse getNotificationMode(String applicationName) {
        checkApplication(applicationName);
        return schoolClient.getNotificationMode(applicationName);
    }

    /**
     * 将微信外部联系人userid转为openid
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return openid
     */
    public ExternalUserOpenIdResponse convertExternalUserToOpenId(ConvertExternalUserToOpenIdRequest request, String applicationName) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getExternalUserId())) {
            throw new ParamCheckException("external_userid 不能为空，请检查！！！");
        }
        return schoolClient.convertExternalUserToOpenId(request, applicationName);
    }

    /**
     * 获取班级群创建方式
     *
     * @param applicationName 应用名称
     * @return 创建方式
     */
    public ChatCreateModeResponse getChatCreateMode(String applicationName) {
        checkApplication(applicationName);
        return schoolClient.getChatCreateMode(applicationName);
    }

    /**
     * 设置班级群创建方式
     *
     * @param createMode      创建方式
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setChatCreateMode(Integer createMode, String applicationName) {
        if (Objects.isNull(createMode) || createMode < 0 || createMode > 1) {
            throw new ParamCheckException("create_mode 只能为0或1，请检查！！！");
        }
        SetChatCreateModeRequest request = new SetChatCreateModeRequest();
        request.setCreateMode(createMode);
        return schoolClient.setChatCreateMode(request, applicationName);
    }

    /**
     * 获取可在「学校通知-学校应用」使用应用的家长范围
     *
     * @param agentid         应用id
     * @param applicationName 应用名称
     * @return 可用范围
     */
    public SchoolAgentAllowScopeResponse getAgentAllowScope(Integer agentid, String applicationName) {
        if (Objects.isNull(agentid) || agentid <= 0) {
            throw new ParamCheckException("agentid 不能为空且必须大于0，请检查！！！");
        }
        return schoolClient.getAgentAllowScope(agentid, applicationName);
    }

    /**
     * 获取家校访问用户身份
     *
     * @param code            授权码
     * @param applicationName 应用名称
     * @return 家长或学生身份
     */
    public SchoolUserInfoResponse getSchoolUserInfo(String code, String applicationName) {
        if (StringUtils.isEmpty(code)) {
            throw new ParamCheckException("code 不能为空，请检查！！！");
        }
        if (code.length() > 512) {
            throw new ParamCheckException("code 长度不能超过512字节，请检查！！！");
        }
        return schoolClient.getSchoolUserInfo(code, applicationName);
    }

    /**
     * 创建学生
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse createStudent(CreateStudentRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求不能为空，请检查！！！");
        }
        checkUserId(request.getStudentUserId(), "student_userid");
        if (StringUtils.isEmpty(request.getName())) {
            throw new ParamCheckException("学生姓名不能为空，请检查！！！");
        }
        checkDepartmentList(request.getDepartment());
        return schoolClient.createStudent(request, applicationName);
    }

    /**
     * 删除学生
     *
     * @param userId          家校通讯录学生userid
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse deleteStudent(String userId, String applicationName) {
        checkApplication(applicationName);
        checkUserId(userId, "userid");
        return schoolClient.deleteStudent(userId, applicationName);
    }

    /**
     * 更新学生
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateStudent(UpdateStudentRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求不能为空，请检查！！！");
        }
        checkUserId(request.getStudentUserId(), "student_userid");
        return schoolClient.updateStudent(request, applicationName);
    }

    /**
     * 批量创建学生
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量结果
     */
    public BatchStudentResultResponse batchCreateStudent(BatchStudentOperationRequest request, String applicationName) {
        checkApplication(applicationName);
        checkStudentOperationRequest(request, true);
        return schoolClient.batchCreateStudent(request, applicationName);
    }

    /**
     * 批量删除学生
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量结果
     */
    public BatchStudentResultResponse batchDeleteStudent(UserIdListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkUserIdList(request);
        return schoolClient.batchDeleteStudent(request, applicationName);
    }

    /**
     * 批量更新学生
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量结果
     */
    public BatchStudentResultResponse batchUpdateStudent(BatchStudentOperationRequest request, String applicationName) {
        checkApplication(applicationName);
        checkStudentOperationRequest(request, false);
        return schoolClient.batchUpdateStudent(request, applicationName);
    }

    /**
     * 创建家长
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse createParent(CreateParentRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getParentUserId())) {
            throw new ParamCheckException("parent_userid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getMobile())) {
            throw new ParamCheckException("家长手机号不能为空，请检查！！！");
        }
        checkParentChildren(request.getChildren());
        return schoolClient.createParent(request, applicationName);
    }

    /**
     * 删除家长
     *
     * @param userId          家校通讯录家长userid
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse deleteParent(String userId, String applicationName) {
        checkApplication(applicationName);
        checkUserId(userId, "userid");
        return schoolClient.deleteParent(userId, applicationName);
    }

    /**
     * 更新家长
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateParent(UpdateParentRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getParentUserId())) {
            throw new ParamCheckException("parent_userid 不能为空，请检查！！！");
        }
        checkParentChildren(request.getChildren());
        return schoolClient.updateParent(request, applicationName);
    }

    /**
     * 批量创建家长
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量结果
     */
    public BatchParentResultResponse batchCreateParent(BatchParentOperationRequest request, String applicationName) {
        checkApplication(applicationName);
        checkParentOperationRequest(request, true);
        return schoolClient.batchCreateParent(request, applicationName);
    }

    /**
     * 批量删除家长
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量结果
     */
    public BatchParentResultResponse batchDeleteParent(UserIdListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkUserIdList(request);
        return schoolClient.batchDeleteParent(request, applicationName);
    }

    /**
     * 批量更新家长
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量结果
     */
    public BatchParentResultResponse batchUpdateParent(BatchParentOperationRequest request, String applicationName) {
        checkApplication(applicationName);
        checkParentOperationRequest(request, false);
        return schoolClient.batchUpdateParent(request, applicationName);
    }

    /**
     * 获取家校通讯录成员详情
     *
     * @param userId          家长或学生的userid
     * @param applicationName 应用名称
     * @return 用户详情
     */
    public SchoolUserDetailResponse getSchoolUser(String userId, String applicationName) {
        checkApplication(applicationName);
        checkUserId(userId, "userid");
        return schoolClient.getSchoolUser(userId, applicationName);
    }

    /**
     * 获取班级下的学生列表
     *
     * @param departmentId    家校部门id
     * @param applicationName 应用名称
     * @return 学生列表
     */
    public StudentListResponse listStudents(Integer departmentId, String applicationName) {
        checkApplication(applicationName);
        checkDepartmentId(departmentId);
        return schoolClient.listStudents(departmentId, applicationName);
    }

    /**
     * 设置家校通讯录自动同步模式
     *
     * @param archSyncMode    同步模式
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setArchSyncMode(Integer archSyncMode, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(archSyncMode) || archSyncMode < 1 || archSyncMode > 3) {
            throw new ParamCheckException("arch_sync_mode 只能为1、2或3，请检查！！！");
        }
        SetArchSyncModeRequest request = new SetArchSyncModeRequest();
        request.setArchSyncMode(archSyncMode);
        return schoolClient.setArchSyncMode(request, applicationName);
    }

    /**
     * 获取班级下的家长列表
     *
     * @param departmentId    家校部门id
     * @param applicationName 应用名称
     * @return 家长列表
     */
    public ParentListResponse listParents(Integer departmentId, String applicationName) {
        checkApplication(applicationName);
        checkDepartmentId(departmentId);
        return schoolClient.listParents(departmentId, applicationName);
    }

    /**
     * 创建家校部门
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 创建结果
     */
    public CreateSchoolDepartmentResponse createSchoolDepartment(CreateSchoolDepartmentRequest request, String applicationName) {
        checkApplication(applicationName);
        checkCreateSchoolDepartmentRequest(request);
        return schoolClient.createSchoolDepartment(request, applicationName);
    }

    /**
     * 更新家校部门
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateSchoolDepartment(UpdateSchoolDepartmentRequest request, String applicationName) {
        checkApplication(applicationName);
        checkUpdateSchoolDepartmentRequest(request);
        return schoolClient.updateSchoolDepartment(request, applicationName);
    }

    /**
     * 删除家校部门
     *
     * @param departmentId    家校部门id
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse deleteSchoolDepartment(Integer departmentId, String applicationName) {
        checkApplication(applicationName);
        checkDepartmentId(departmentId);
        return schoolClient.deleteSchoolDepartment(departmentId, applicationName);
    }

    /**
     * 获取家校部门列表
     *
     * @param departmentId    家校部门id；为空时获取全量组织架构
     * @param applicationName 应用名称
     * @return 部门列表
     */
    public SchoolDepartmentListResponse listSchoolDepartments(Integer departmentId, String applicationName) {
        checkApplication(applicationName);
        if (Objects.nonNull(departmentId) && departmentId <= 0) {
            throw new ParamCheckException("department_id 必须大于0，请检查！！！");
        }
        return schoolClient.listSchoolDepartments(departmentId, applicationName);
    }

    /**
     * 获取家校标准年级对照表
     *
     * @return 标准年级参数值和名称
     */
    public Map<Integer, String> getStandardGrades() {
        Map<Integer, String> grades = new LinkedHashMap<Integer, String>();
        grades.put(0, "非标准年级");
        grades.put(1, "幼儿园小小班");
        grades.put(2, "幼儿园小班");
        grades.put(3, "幼儿园中班");
        grades.put(4, "幼儿园大班");
        grades.put(5, "幼儿园学前班");
        grades.put(31, "小学一年级");
        grades.put(32, "小学二年级");
        grades.put(33, "小学三年级");
        grades.put(34, "小学四年级");
        grades.put(35, "小学五年级");
        grades.put(36, "小学六年级");
        grades.put(37, "小学七年级");
        grades.put(38, "小学八年级");
        grades.put(39, "小学九年级");
        grades.put(61, "初中一年级");
        grades.put(62, "初中二年级");
        grades.put(63, "初中三年级");
        grades.put(64, "初中四年级");
        grades.put(91, "高中一年级");
        grades.put(92, "高中二年级");
        grades.put(93, "高中三年级");
        grades.put(94, "高中四年级");
        grades.put(121, "自定义学段一年级");
        grades.put(122, "自定义学段二年级");
        grades.put(123, "自定义学段三年级");
        grades.put(124, "自定义学段四年级");
        grades.put(125, "自定义学段五年级");
        grades.put(126, "自定义学段六年级");
        grades.put(127, "自定义学段七年级");
        grades.put(128, "自定义学段八年级");
        grades.put(129, "自定义学段九年级");
        grades.put(130, "自定义学段十年级");
        grades.put(131, "自定义学段十一年级");
        grades.put(132, "自定义学段十二年级");
        return Collections.unmodifiableMap(grades);
    }

    private void checkCreateSchoolDepartmentRequest(CreateSchoolDepartmentRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getParentId()) || request.getParentId() <= 0) {
            throw new ParamCheckException("parentid 不能为空且必须大于0，请检查！！！");
        }
        if (Objects.isNull(request.getType()) || request.getType() < 1 || request.getType() > 4) {
            throw new ParamCheckException("type 只能为1、2、3或4，请检查！！！");
        }
        if (Objects.nonNull(request.getId()) && request.getId() <= 1) {
            throw new ParamCheckException("指定id时必须大于1，请检查！！！");
        }
        checkSchoolDepartmentAdmins(request.getDepartmentAdmins());
    }

    private void checkUpdateSchoolDepartmentRequest(UpdateSchoolDepartmentRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getId()) || request.getId() <= 0) {
            throw new ParamCheckException("id 不能为空且必须大于0，请检查！！！");
        }
        if (Objects.nonNull(request.getDepartmentAdmins())) {
            for (SchoolDepartmentUpdateAdminItem admin : request.getDepartmentAdmins()) {
                if (Objects.isNull(admin) || Objects.isNull(admin.getOp()) || admin.getOp() < 0 || admin.getOp() > 1
                        || StringUtils.isEmpty(admin.getUserid())) {
                    throw new ParamCheckException("department_admins.op和userid不能为空，op只能为0或1，请检查！！！");
                }
            }
        }
    }

    private void checkSchoolDepartmentAdmins(List<SchoolDepartmentAdminItem> admins) {
        if (Objects.isNull(admins)) {
            return;
        }
        for (SchoolDepartmentAdminItem admin : admins) {
            if (Objects.isNull(admin) || StringUtils.isEmpty(admin.getUserid())
                    || Objects.isNull(admin.getType()) || admin.getType() < 1 || admin.getType() > 5) {
                throw new ParamCheckException("department_admins.userid和type不能为空，type只能为1~5，请检查！！！");
            }
        }
    }

    private void checkUserId(String userId, String fieldName) {
        if (StringUtils.isEmpty(userId) || userId.length() > 64) {
            throw new ParamCheckException(fieldName + " 不能为空且长度为1~64个字符，请检查！！！");
        }
    }

    private void checkDepartmentList(List<Integer> departments) {
        if (Objects.isNull(departments) || departments.isEmpty() || departments.size() > 20) {
            throw new ParamCheckException("department 必须包含1~20个班级id，请检查！！！");
        }
    }

    private void checkUserIdList(UserIdListRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getUserIdList())
                || request.getUserIdList().isEmpty() || request.getUserIdList().size() > 100) {
            throw new ParamCheckException("useridlist 必须包含1~100个账号，请检查！！！");
        }
        for (String userId : request.getUserIdList()) {
            checkUserId(userId, "useridlist 中的userid");
        }
    }

    private void checkStudentOperationRequest(BatchStudentOperationRequest request, boolean createMode) {
        if (Objects.isNull(request) || Objects.isNull(request.getStudents())
                || request.getStudents().isEmpty() || request.getStudents().size() > 100) {
            throw new ParamCheckException("students 必须包含1~100个学生，请检查！！！");
        }
        for (StudentItem student : request.getStudents()) {
            checkUserId(student.getStudentUserId(), "students.student_userid");
            if (createMode) {
                if (StringUtils.isEmpty(student.getName())) {
                    throw new ParamCheckException("students.name 不能为空，请检查！！！");
                }
                checkDepartmentList(student.getDepartment());
            }
        }
    }

    private void checkParentOperationRequest(BatchParentOperationRequest request, boolean createMode) {
        if (Objects.isNull(request) || Objects.isNull(request.getParents())
                || request.getParents().isEmpty() || request.getParents().size() > 100) {
            throw new ParamCheckException("parents 必须包含1~100个家长，请检查！！！");
        }
        for (ParentItem parent : request.getParents()) {
            checkUserId(parent.getParentUserId(), "parents.parent_userid");
            if (createMode) {
                if (StringUtils.isEmpty(parent.getMobile())) {
                    throw new ParamCheckException("parents.mobile 不能为空，请检查！！！");
                }
                checkParentChildren(parent.getChildren());
            }
        }
    }

    private void checkParentChildren(List<ParentChildItem> children) {
        if (Objects.isNull(children) || children.isEmpty() || children.size() > 10) {
            throw new ParamCheckException("children 必须包含1~10个孩子关系，请检查！！！");
        }
        for (ParentChildItem child : children) {
            if (StringUtils.isEmpty(child.getStudentUserId()) || StringUtils.isEmpty(child.getRelation())) {
                throw new ParamCheckException("孩子的student_userid和relation不能为空，请检查！！！");
            }
        }
    }

    private void checkDepartmentId(Integer departmentId) {
        if (Objects.isNull(departmentId) || departmentId <= 0) {
            throw new ParamCheckException("department_id 不能为空且必须大于0，请检查！！！");
        }
    }

    private void checkNotificationMode(Integer subscribeMode) {
        if (Objects.isNull(subscribeMode) || subscribeMode < 1 || subscribeMode > 2) {
            throw new ParamCheckException("subscribe_mode 只能为1或2，请检查！！！");
        }
    }
}
