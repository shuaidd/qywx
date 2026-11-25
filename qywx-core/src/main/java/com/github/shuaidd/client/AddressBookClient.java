package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.addressbook.ActiveStatResponse;
import com.github.shuaidd.response.addressbook.AsyncJobResponse;
import com.github.shuaidd.response.addressbook.AsyncJobResultResponse;
import com.github.shuaidd.response.addressbook.ContactRuleIdsResponse;
import com.github.shuaidd.response.addressbook.ContactRuleListResponse;
import com.github.shuaidd.response.addressbook.ConvertUserIdOpenIdResponse;
import com.github.shuaidd.response.addressbook.CreateDepartmentResponse;
import com.github.shuaidd.response.addressbook.CreateTagResponse;
import com.github.shuaidd.response.addressbook.DepartmentDetailResponse;
import com.github.shuaidd.response.addressbook.DepartmentListResponse;
import com.github.shuaidd.response.addressbook.DepartmentUserResponse;
import com.github.shuaidd.response.addressbook.JoinQrCodeResponse;
import com.github.shuaidd.response.addressbook.QueryTagUserResponse;
import com.github.shuaidd.response.addressbook.SimpleDepartmentListResponse;
import com.github.shuaidd.response.addressbook.SimpleDeptUserResponse;
import com.github.shuaidd.response.addressbook.TagListResponse;
import com.github.shuaidd.response.addressbook.TagUserResponse;
import com.github.shuaidd.response.addressbook.UserIdResponse;
import com.github.shuaidd.response.addressbook.WeChatUserResponse;
import com.github.shuaidd.response.auth.AuthenticationResponse;
import com.github.shuaidd.resquest.CursorPageRequest;
import com.github.shuaidd.resquest.addressbook.ActiveStatRequest;
import com.github.shuaidd.resquest.addressbook.AddressBookExportRequest;
import com.github.shuaidd.resquest.addressbook.AsyncJobRequest;
import com.github.shuaidd.resquest.addressbook.BatchDeleteUserRequest;
import com.github.shuaidd.resquest.addressbook.ContactRuleListRequest;
import com.github.shuaidd.resquest.addressbook.ConvertUserIdOpenIdRequest;
import com.github.shuaidd.resquest.addressbook.CreateUserRequest;
import com.github.shuaidd.resquest.addressbook.DeleteContactRuleRequest;
import com.github.shuaidd.resquest.addressbook.DepartmentRequest;
import com.github.shuaidd.resquest.addressbook.GetUserIdByEmailRequest;
import com.github.shuaidd.resquest.addressbook.GetUserIdByMobileRequest;
import com.github.shuaidd.resquest.addressbook.InviteUserRequest;
import com.github.shuaidd.resquest.addressbook.TagRequest;
import com.github.shuaidd.resquest.addressbook.TagUserRequest;
import com.github.shuaidd.resquest.addressbook.UpdateUserRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 通讯录接口
 *
 * @author ddshuai
 * date 2022-09-01 09:03
 **/
public interface AddressBookClient extends CommonClient {

    /**
     * 创建成员
     *
     * @param request 请求参数
     * @param app     应用
     * @return BaseResponse
     */
    @RequestLine("POST /user/create?app={app}")
    @Headers(HEAD)
    BaseResponse createUser(CreateUserRequest request, @Param("app") String app);

    /**
     * 读取成员信息
     *
     * @param app    应用名
     * @param userId 成员编号
     * @return WeChatUserResponse
     */
    @RequestLine("GET /user/get?userid={userid}&app={app}")
    @Headers(HEAD)
    WeChatUserResponse getUser(@Param("userid") String userId, @Param("app") String app);

    /**
     * 更新成员信息【根据userID更新，必传】
     *
     * @param app     应用名
     * @param request 更新请求参数
     * @return BaseResponse
     */
    @RequestLine("POST /user/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateUser(UpdateUserRequest request, @Param("app") String app);

    /**
     * 删除成员
     *
     * @param userId 成员编号
     * @param app    应用
     * @return BaseResponse
     */
    @RequestLine("GET /user/delete?userid={userid}&app={app}")
    @Headers(HEAD)
    BaseResponse deleteUser(@Param("userid") String userId, @Param("app") String app);

    /**
     * 批量删除成员
     *
     * @param request 请求体
     * @param app     应用
     * @return BaseResponse
     */
    @RequestLine("POST /user/batchdelete?app={app}")
    @Headers(HEAD)
    BaseResponse batchDelete(BatchDeleteUserRequest request, @Param("app") String app);

    /**
     * 获取部门成员[应用须拥有指定部门的查看权限。]
     *
     * @param app          应用名
     * @param departmentId 获取的部门id
     * @param fetchChild   1/0：是否递归获取子部门下面的成员
     * @return DepartmentUserResponse
     */
    @RequestLine("GET /user/simplelist?department_id={department_id}&fetch_child={fetch_child}&app={app}")
    @Headers(HEAD)
    DepartmentUserResponse getDepartmentUser(@Param("department_id") Integer departmentId, @Param("fetch_child") Integer fetchChild, @Param("app") String app);

    /**
     * 获取部门成员详情[应用须拥有指定部门的查看权限。]
     *
     * @param app          应用名
     * @param departmentId 获取的部门id
     * @param fetchChild   1/0：是否递归获取子部门下面的成员
     * @return DepartmentUserResponse
     */
    @RequestLine("GET /user/list?department_id={department_id}&fetch_child={fetch_child}&app={app}")
    @Headers(HEAD)
    DepartmentUserResponse getDepartmentUserDetail(@Param("department_id") Integer departmentId, @Param("fetch_child") Integer fetchChild, @Param("app") String app);

    /**
     * 获取成员ID列表
     *
     * @param request 请求
     * @param app     应用名
     * @return SimpleDeptUserResponse
     */
    @RequestLine("POST /user/list_id?app={app}")
    @Headers(HEAD)
    SimpleDeptUserResponse getUserIds(CursorPageRequest request, @Param("app") String app);

    /**
     * userid转openid
     *
     * @param request 请求体
     * @param app     应用名
     * @return ConvertUserIdOpenIdResponse
     */
    @RequestLine("POST /user/convert_to_openid?app={app}")
    @Headers(HEAD)
    ConvertUserIdOpenIdResponse convertToOpenId(ConvertUserIdOpenIdRequest request, @Param("app") String app);

    /**
     * openid转userid
     *
     * @param request 请求体
     * @param app     应用名
     * @return ConvertUserIdOpenIdResponse
     */
    @RequestLine("POST /user/convert_to_userid?app={app}")
    @Headers(HEAD)
    ConvertUserIdOpenIdResponse convertToUserId(ConvertUserIdOpenIdRequest request, @Param("app") String app);

    /**
     * 二次验证
     *
     * @param userId 用户编号
     * @param app    应用名
     * @return BaseResponse
     */
    @RequestLine("GET /user/authsucc?userid={userid}&app={app}")
    @Headers(HEAD)
    BaseResponse authSucc(@Param("userid") String userId, @Param("app") String app);

    /**
     * 邀请成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /batch/invite?app={app}")
    @Headers(HEAD)
    BaseResponse invite(InviteUserRequest request, @Param("app") String app);

    /**
     * 创建部门
     *
     * @param app     应用
     * @param request 请求体
     * @return CreateDepartmentResponse
     */
    @RequestLine("POST /department/create?app={app}")
    @Headers(HEAD)
    CreateDepartmentResponse createDepartment(DepartmentRequest request, @Param("app") String app);

    /**
     * 更新部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /department/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateDepartment(DepartmentRequest request, @Param("app") String app);

    /**
     * 删除部门【注：不能删除根部门；不能删除含有子部门、成员的部门】
     *
     * @param id  企业微信部门id
     * @param app 应用名
     * @return BaseResponse
     */
    @RequestLine("GET /department/delete?id={id}&app={app}")
    @Headers(HEAD)
    BaseResponse deleteDepartment(@Param("id") Integer id, @Param("app") String app);

    /**
     * 权限说明：
     * 只能拉取token对应的应用的权限范围内的部门列表
     * <p>
     * 获取部门列表【获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构】
     *
     * @param id  部门id
     * @param app 应用名
     * @return DepartmentListResponse
     */
    @RequestLine("GET /department/list?id={id}&app={app}")
    @Headers(HEAD)
    DepartmentListResponse departmentList(@Param("id") Integer id, @Param("app") String app);

    /**
     * 获取子部门ID列表
     *
     * @param id  部门id
     * @param app 应用名
     * @return SimpleDepartmentListResponse
     */
    @RequestLine("GET /department/simplelist?id={id}&app={app}")
    @Headers(HEAD)
    SimpleDepartmentListResponse departmentSimpleList(@Param("id") Integer id, @Param("app") String app);

    /**
     * 创建标签【创建的标签属于该应用，只有该应用才可以增删成员】
     * 注意，标签总数不能超过3000个。
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateTagResponse
     */
    @RequestLine("POST /tag/create?app={app}")
    @Headers(HEAD)
    CreateTagResponse createTag(TagRequest request, @Param("app") String app);

    /**
     * 权限说明：
     * 调用者必须是指定标签的创建者
     * <p>
     * 更新标签名字【长度限制为32个字（汉字或英文字母），标签不可与其他标签重名】
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /tag/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateTag(TagRequest request, @Param("app") String app);

    /**
     * 删除标签
     *
     * @param id  标签ID
     * @param app 应用名
     * @return BaseResponse
     */
    @RequestLine("GET /tag/delete?tagid={tagid}&app={app}")
    @Headers(HEAD)
    BaseResponse deleteTag(@Param("tagid") Integer id, @Param("app") String app);

    /**
     * 获取标签成员
     *
     * @param id  标签id
     * @param app 应用名
     * @return QueryTagUserResponse
     */
    @RequestLine("GET /tag/get?tagid={tagid}&app={app}")
    @Headers(HEAD)
    QueryTagUserResponse getTagUser(@Param("tagid") Integer id, @Param("app") String app);

    /**
     * 增加标签成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return TagUserResponse
     */
    @RequestLine("POST /tag/addtagusers?app={app}")
    @Headers(HEAD)
    TagUserResponse addTagUsers(TagUserRequest request, @Param("app") String app);

    /**
     * 删除标签成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return TagUserResponse
     */
    @RequestLine("POST /tag/deltagusers?app={app}")
    @Headers(HEAD)
    TagUserResponse deleteTagUsers(TagUserRequest request, @Param("app") String app);

    /**
     * 获取标签列表
     *
     * @param app 应用名
     * @return TagListResponse
     */
    @RequestLine("GET /tag/list?app={app}")
    @Headers(HEAD)
    TagListResponse getTagList(@Param("app") String app);

    /**
     * 注意事项：
     * <p>
     * 模板中的部门需填写部门ID，多个部门用分号分隔，部门ID必须为数字，根部门的部门id默认为1
     * 文件中存在、通讯录中也存在的成员，更新成员在文件中指定的字段值
     * 文件中存在、通讯录中不存在的成员，执行添加操作
     * 通讯录中存在、文件中不存在的成员，保持不变
     * 成员字段更新规则：可自行添加扩展字段。文件中有指定的字段，以指定的字段值为准；文件中没指定的字段，不更新
     * <p>
     * 增量更新成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return AsyncJobResponse
     */
    @RequestLine("POST /batch/syncuser?app={app}")
    @Headers(HEAD)
    AsyncJobResponse asyncBatchUpdateUser(AsyncJobRequest request, @Param("app") String app);

    /**
     * 注意事项：
     * <p>
     * 模板中的部门需填写部门ID，多个部门用分号分隔，部门ID必须为数字，根部门的部门id默认为1
     * 文件中存在、通讯录中也存在的成员，完全以文件为准
     * 文件中存在、通讯录中不存在的成员，执行添加操作
     * 通讯录中存在、文件中不存在的成员，执行删除操作。出于安全考虑，下面两种情形系统将中止导入并返回相应的错误码。
     * 需要删除的成员多于50人，且多于现有人数的20%以上
     * 需要删除的成员少于50人，且多于现有人数的80%以上
     * 成员字段更新规则：可自行添加扩展字段。文件中有指定的字段，以指定的字段值为准；文件中没指定的字段，不更新
     * <p>
     * 全量覆盖成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return AsyncJobResponse
     */
    @RequestLine("POST /batch/replaceuser?app={app}")
    @Headers(HEAD)
    AsyncJobResponse fullCoverUser(AsyncJobRequest request, @Param("app") String app);

    /**
     * 注意事项：
     * <p>
     * 文件中存在、通讯录中也存在的部门，执行修改操作
     * 文件中存在、通讯录中不存在的部门，执行添加操作
     * 文件中不存在、通讯录中存在的部门，当部门下没有任何成员或子部门时，执行删除操作
     * 文件中不存在、通讯录中存在的部门，当部门下仍有成员或子部门时，暂时不会删除，当下次导入成员把人从部门移出后自动删除
     * CSV文件中，部门名称、部门ID、父部门ID为必填字段，部门ID必须为数字，根部门的部门id默认为1；排序为可选字段，置空或填0不修改排序, order值大的排序靠前。
     * <p>
     * 全量覆盖部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return AsyncJobResponse
     */
    @RequestLine("POST /batch/replaceparty?app={app}")
    @Headers(HEAD)
    AsyncJobResponse fullCoverDepartment(AsyncJobRequest request, @Param("app") String app);

    /**
     * 获取异步任务结果
     *
     * @param jobId 任务id
     * @param app   应用名
     * @return AsyncJobResultResponse
     */
    @RequestLine("GET /batch/getresult?jobid={jobid}&app={app}")
    @Headers(HEAD)
    AsyncJobResultResponse jobResult(@Param("jobid") String jobId, @Param("app") String app);

    /**
     * 获取访问用户身份
     *
     * @param code 授权码
     * @param app  应用名
     * @return AuthenticationResponse
     */
    @RequestLine("GET /user/getuserinfo?code={code}&app={app}")
    @Headers(HEAD)
    AuthenticationResponse getAuthentication(@Param("code") String code, @Param("app") String app);

    /**
     * 获取企业活跃成员数
     *
     * @param request 请求体
     * @param app     应用名
     * @return 企业活跃成员数
     */
    @RequestLine("POST /user/get_active_stat?app={app}")
    @Headers(HEAD)
    ActiveStatResponse getActiveStat(ActiveStatRequest request, @Param("app") String app);

    /**
     * 导出成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return 异步任务编号
     */
    @RequestLine("POST /export/simple_user?app={app}")
    @Headers(HEAD)
    AsyncJobResponse exportUser(AddressBookExportRequest request, @Param("app") String app);

    /**
     * 导出成员详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return 异步任务编号
     */
    @RequestLine("POST /export/user?app={app}")
    @Headers(HEAD)
    AsyncJobResponse exportUserDetail(AddressBookExportRequest request, @Param("app") String app);

    /**
     * 导出部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return 异步任务编号
     */
    @RequestLine("POST /export/department?app={app}")
    @Headers(HEAD)
    AsyncJobResponse exportDepartment(AddressBookExportRequest request, @Param("app") String app);

    /**
     * 导出标签成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return 异步任务编号
     */
    @RequestLine("POST /export/taguser?app={app}")
    @Headers(HEAD)
    AsyncJobResponse exportTagUser(AddressBookExportRequest request, @Param("app") String app);

    /**
     * 获取加入企业二维码
     *
     * @param sizeType qrcode尺寸类型
     * @param app      应用名
     * @return 二维码地址
     */
    @RequestLine("GET /corp/get_join_qrcode?size_type={size_type}&app={app}")
    @Headers(HEAD)
    JoinQrCodeResponse getJoinQrCode(@Param("size_type") Integer sizeType, @Param("app") String app);

    /**
     * 根据手机号获取 userid
     *
     * @param request 请求体
     * @param app     应用名
     * @return UserIdResponse
     */
    @RequestLine("POST /user/getuserid?app={app}")
    @Headers(HEAD)
    UserIdResponse getUserIdByMobile(GetUserIdByMobileRequest request, @Param("app") String app);

    /**
     * 根据邮箱获取 userid
     *
     * @param request 请求体
     * @param app     应用名
     * @return UserIdResponse
     */
    @RequestLine("POST /user/get_userid_by_email?app={app}")
    @Headers(HEAD)
    UserIdResponse getUserIdByEmail(GetUserIdByEmailRequest request, @Param("app") String app);

    /**
     * 获取单个部门详情
     *
     * @param id  部门id
     * @param app 应用名
     * @return DepartmentDetailResponse
     */
    @RequestLine("GET /department/get?id={id}&app={app}")
    @Headers(HEAD)
    DepartmentDetailResponse getDepartmentDetail(@Param("id") Integer id, @Param("app") String app);
    /**
     * 创建通讯录隐藏规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return ContactRuleIdsResponse
     */
    @RequestLine("POST /contactrule/create?app={app}")
    @Headers(HEAD)
    ContactRuleIdsResponse createContactRule(ContactRuleListRequest request, @Param("app") String app);

    /**
     * 读取通讯录隐藏规则列表
     *
     * @param app 应用名
     * @return ContactRuleListResponse
     */
    @RequestLine("POST /contactrule/list?app={app}")
    @Headers(HEAD)
    ContactRuleListResponse getContactRuleList(@Param("app") String app);

    /**
     * 修改通讯录隐藏规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /contactrule/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateContactRule(ContactRuleListRequest request, @Param("app") String app);

    /**
     * 删除通讯录隐藏规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /contactrule/delete?app={app}")
    @Headers(HEAD)
    BaseResponse deleteContactRule(DeleteContactRuleRequest request, @Param("app") String app);

}
