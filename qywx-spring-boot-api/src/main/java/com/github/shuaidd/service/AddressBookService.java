package com.github.shuaidd.service;

import com.github.shuaidd.dto.addressbook.Department;
import com.github.shuaidd.dto.addressbook.DeptUser;
import com.github.shuaidd.dto.addressbook.Tag;
import com.github.shuaidd.dto.addressbook.WeChatUser;
import com.github.shuaidd.dto.tool.CallbackData;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.addressbook.*;
import com.github.shuaidd.response.linkedcorp.*;
import com.github.shuaidd.resquest.CursorPageRequest;
import com.github.shuaidd.resquest.addressbook.*;
import com.github.shuaidd.resquest.linkedcorp.LinkedCorpDeptRequest;
import com.github.shuaidd.resquest.linkedcorp.LinkedCropUserRequest;
import com.github.shuaidd.support.CommonsMultipartFile;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 企业微信通讯录管理
 *
 * @author ddshuai
 * date 2019-04-04 14:22
 **/
@Service
public class AddressBookService extends AbstractBaseService {

    /**
     * 增量更新用户时上传的文件名称
     */
    private static final String ASYNC_BATCH_UPDATE_USER_FILE = "asyncBatchUpdateUser-%s.csv";

    /**
     * 全量覆盖用户时
     */
    private static final String FULL_COVER_USER_FILE = "fullCoverUser-%s.csv";

    /**
     * 批量更新用户
     */
    private static final String ASYNC_BATCH_UPDATE_USER = "asyncBatchUpdateUser";

    /**
     * 全量覆盖用户
     */
    private static final String FULL_COVER_USER = "fullCoverUser";

    /**
     * 更新用户时上传文件的title
     */
    private static final String ASYNC_BATCH_UPDATE_USER_FILE_TITLE = "姓名,帐号,手机号,邮箱,所在部门,职位,性别,是否部门内领导,排序,别名,地址,座机,禁用,禁用项说明：(0-启用;1-禁用)\n";


    private static final String FULL_COVER_DEPARTMENT_FILE_TITLE = "部门名称,部门ID,父部门ID,排序\n";

    /**
     * 全量覆盖部门
     */
    private static final String FULL_COVER_DEPARTMENT_FILE = "fullCoverUser-%s.csv";

    private MediaService mediaService;

    @Autowired
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * 创建用户
     *
     * @param applicationName 应用名
     * @param request         请求
     */
    public void createUser(CreateUserRequest request, String applicationName) {
        if (Objects.nonNull(request)) {
            addressBookClient.createUser(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("用户创建成功:{}", request);
            }
        }
    }

    /**
     * 读取成员信息
     *
     * @param userId          用户编号
     * @param applicationName 应用
     * @return WeChatUserResponse
     */
    public WeChatUserResponse getUser(String userId, String applicationName) {
        WeChatUserResponse weChatUserResponse = null;
        if (StringUtils.isNotEmpty(userId)) {
            weChatUserResponse = addressBookClient.getUser(userId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("读取到成员信息：{}", weChatUserResponse);
            }
        }

        return weChatUserResponse;
    }

    /**
     * 更新成员信息
     *
     * @param request         更新信息
     * @param applicationName 应用
     */
    public void updateUser(UpdateUserRequest request, String applicationName) {
        if (Objects.nonNull(request) && StringUtils.isNotEmpty(request.getUserId())) {
            addressBookClient.updateUser(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("更新成员信息成功：userId-{},applicationName-{}", request.getUserId(), applicationName);
            }
        }
    }

    /**
     * 删除成员
     *
     * @param userId          用户编号
     * @param applicationName 应用名称
     */
    public void deleteUser(String userId, String applicationName) {
        if (StringUtils.isNotEmpty(userId)) {
            addressBookClient.deleteUser(userId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("删除成员成功：userId-{},applicationName-{}", userId, applicationName);
            }
        }
    }

    /**
     * 批量删除成员
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void batchDeleteUser(BatchDeleteUserRequest request, String applicationName) {
        if (Objects.nonNull(request) && CollectionUtils.isNotEmpty(request.getUserIdList())) {
            addressBookClient.batchDelete(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("批量删除成员成功：{},applicationName-{}", request, applicationName);
            }
        }
    }

    /**
     * 获取部门成员
     *
     * @param departmentId    企业微信端部门编号
     * @param fetchChild      是否获取子部门人员
     * @param applicationName 应用
     * @return WeChatUser
     */
    public List<WeChatUser> getDepartmentUser(Integer departmentId, boolean fetchChild, String applicationName) {

        List<WeChatUser> weChatUsers = new ArrayList<>(1);
        if (Objects.nonNull(departmentId)) {
            Integer getChild = fetchChild ? 1 : 0;
            DepartmentUserResponse departmentUserResponse = addressBookClient.getDepartmentUser(departmentId, getChild, applicationName);
            weChatUsers = departmentUserResponse.getWeChatUserList();

            if (logger.isInfoEnabled()) {
                logger.info("获取部门成员成功：applicationName-{}--weChatUsers--{}", applicationName, weChatUsers);
            }
        }

        return weChatUsers;
    }

    /**
     * 获取部门成员详情
     *
     * @param departmentId    企业微信端部门编号
     * @param fetchChild      是否获取子部门人员
     * @param applicationName 应用
     * @return WeChatUser
     */
    public List<WeChatUser> getDepartmentUserDetail(Integer departmentId, boolean fetchChild, String applicationName) {

        List<WeChatUser> weChatUsers = new ArrayList<>(1);
        if (Objects.nonNull(departmentId)) {
            Integer getChild = fetchChild ? 1 : 0;
            DepartmentUserResponse departmentUserResponse = addressBookClient.getDepartmentUserDetail(departmentId, getChild, applicationName);
            weChatUsers = departmentUserResponse.getWeChatUserList();

            if (logger.isInfoEnabled()) {
                logger.info("获取部门成员详情成功：applicationName-{}--weChatUsers--{}", applicationName, weChatUsers);
            }
        }

        return weChatUsers;
    }

    /**
     * userid转openid
     *
     * @param userId          用户编号
     * @param applicationName 应用名称
     * @return String
     */
    public String convertToOpenId(String userId, String applicationName) {

        String openId = null;
        if (StringUtils.isNotEmpty(userId)) {
            ConvertUserIdOpenIdResponse response = addressBookClient.convertToOpenId(new ConvertUserIdOpenIdRequest(userId, null), applicationName);
            openId = response.getOpenId();
            if (logger.isInfoEnabled()) {
                logger.info("获取openId成功：openid-{}----applicationName-{}", openId, applicationName);
            }
        }

        return openId;
    }

    /**
     * openid转userid
     *
     * @param openId          用户编号
     * @param applicationName 应用名称
     * @return String
     */
    public String convertToUserId(String openId, String applicationName) {

        String userId = null;
        if (StringUtils.isNotEmpty(openId)) {
            ConvertUserIdOpenIdResponse response = addressBookClient.convertToUserId(new ConvertUserIdOpenIdRequest(null, openId), applicationName);
            userId = response.getUserId();
            if (logger.isInfoEnabled()) {
                logger.info("获取用户ID成功：userId-{}, applicationName-{}", userId, applicationName);
            }
        }

        return userId;
    }

    /**
     * 二次验证
     *
     * @param userId          用户编号
     * @param applicationName 应用名称
     */
    public void authSuccess(String userId, String applicationName) {

        if (StringUtils.isNotEmpty(userId)) {
            BaseResponse response = addressBookClient.authSucc(userId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("二次验证成功：userId-{},applicationName-{}", userId, applicationName);
            }
        }
    }

    /**
     * 邀请成员
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void invite(InviteUserRequest request, String applicationName) {

        if (Objects.nonNull(request)) {
            if (CollectionUtils.isEmpty(request.getParty()) && CollectionUtils.isEmpty(request.getTag()) && CollectionUtils.isEmpty(request.getUser())) {
                return;
            }
            BaseResponse baseResponse = addressBookClient.invite(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("邀请成功：applicationName-{}", applicationName);
            }
        }
    }

    /**
     * 创建部门
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CreateDepartmentResponse
     */
    public CreateDepartmentResponse createDepartment(DepartmentRequest request, String applicationName) {
        CreateDepartmentResponse response = null;
        if (Objects.nonNull(request) && StringUtils.isNotEmpty(request.getName())) {
            response = addressBookClient.createDepartment(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("创建部门成功：部门编号-{},applicationName-{}", response.getId(), applicationName);
            }
        }

        return response;
    }

    /**
     * 更新部门信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateDepartment(DepartmentRequest request, String applicationName) {
        if (Objects.nonNull(request) && Objects.nonNull(request.getId())) {
            addressBookClient.updateDepartment(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("更新部门成功：更新信息-{},applicationName-{}", request, applicationName);
            }
        }
    }

    /**
     * 删除部门
     *
     * @param id              部门id
     * @param applicationName 应用名称
     */
    public void deleteDepartment(Integer id, String applicationName) {

        if (Objects.nonNull(id)) {
            addressBookClient.deleteDepartment(id, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("删除部门成功：部门编号-{},applicationName-{}", id, applicationName);
            }
        }
    }

    /**
     * 拉取部门列表
     *
     * @param id              部门id
     * @param applicationName 应用名称
     * @return Department
     */
    public List<Department> departmentList(Integer id, String applicationName) {
        DepartmentListResponse response = addressBookClient.departmentList(id, applicationName);
        List<Department> departments = response.getDepartments();
        if (logger.isInfoEnabled()) {
            logger.info("拉取部门列表成功：部门数量-{},applicationName-{}，departments--{}", departments.size(), applicationName, departments);
        }

        return departments;
    }

    /**
     * 拉取部门列表
     *
     * @param id              部门id
     * @param applicationName 应用名称
     * @return Department
     */
    public List<Department> simpleDepartmentList(Integer id, String applicationName) {
        SimpleDepartmentListResponse response = addressBookClient.departmentSimpleList(id, applicationName);
        List<Department> departments = response.getDepartments();
        if (logger.isInfoEnabled()) {
            logger.info("拉取部门列表成功：部门数量-{},applicationName-{}，departments--{}", departments.size(), applicationName, departments);
        }

        return departments;
    }

    /**
     * 创建标签
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return Integer
     */
    public Integer createTag(TagRequest request, String applicationName) {

        Integer tagId = null;
        if (Objects.nonNull(request) && StringUtils.isNotEmpty(request.getTagName())) {
            CreateTagResponse createTagResponse = addressBookClient.createTag(request, applicationName);
            tagId = createTagResponse.getTagId();
            if (logger.isInfoEnabled()) {
                logger.info("创建标签成功：标签编号-{},applicationName-{}", tagId, applicationName);
            }
        }

        return tagId;
    }

    /**
     * 更新标签名字
     *
     * @param tagId           标签ID
     * @param tagName         标签名称
     * @param applicationName 应用名称
     */
    public void updateTagName(Integer tagId, String tagName, String applicationName) {
        if (Objects.nonNull(tagId) && StringUtils.isNotEmpty(tagName)) {
            TagRequest tagRequest = new TagRequest();
            tagRequest.setTagId(tagId);
            tagRequest.setTagName(tagName);
            addressBookClient.updateTag(tagRequest, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("更新标签名字成功：标签编号[{}],标签名称[{}],applicationName-{}", tagId, tagName, applicationName);
            }
        }
    }

    /**
     * 删除标签
     *
     * @param tagId           标签ID
     * @param applicationName 应用名称
     */
    public void deleteTag(Integer tagId, String applicationName) {
        if (Objects.nonNull(tagId)) {
            addressBookClient.deleteTag(tagId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("删除标签成功：标签编号[{}],applicationName-{}", tagId, applicationName);
            }
        }
    }

    /**
     * 获取标签成员
     *
     * @param tagId           标签ID
     * @param applicationName 应用名称
     * @return QueryTagUserResponse
     */
    public QueryTagUserResponse getTagUser(Integer tagId, String applicationName) {
        QueryTagUserResponse response = null;
        if (Objects.nonNull(tagId)) {
            response = addressBookClient.getTagUser(tagId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("获取标签成员成功：标签编号[{}],applicationName-{}--成员--{}", tagId, applicationName, response.getUserList());
            }
        }

        return response;
    }

    /**
     * 增加标签成员
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return TagUserResponse
     */
    public TagUserResponse addTagUsers(TagUserRequest request, String applicationName) {
        TagUserResponse response = null;
        if (Objects.nonNull(request) && Objects.nonNull(request.getTagId())) {
            response = addressBookClient.addTagUsers(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("增加标签成员成功：标签编号[{}],applicationName-{}", request.getTagId(), applicationName);
            }
        }
        return response;
    }

    /**
     * 删除标签成员
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return TagUserResponse
     */
    public TagUserResponse deleteTagUsers(TagUserRequest request, String applicationName) {
        TagUserResponse response = null;
        if (Objects.nonNull(request) && Objects.nonNull(request.getTagId())) {
            response = addressBookClient.deleteTagUsers(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("删除标签成员成功：标签编号[{}],applicationName-{}", request.getTagId(), applicationName);
            }
        }
        return response;
    }

    /**
     * 获取标签列表
     *
     * @param applicationName 应用名称
     * @return Tag
     */
    public List<Tag> getTagList(String applicationName) {
        TagListResponse response = addressBookClient.getTagList(applicationName);
        List<Tag> tags = response.getTagList();
        if (logger.isInfoEnabled()) {
            logger.info("获取标签列表成功：applicationName-{}--tags--{}", applicationName, tags);
        }

        return tags;
    }

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
     * @param toInvite        是否邀请新建的成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
     * @param weChatUsers     用户
     * @param applicationName 应用名称
     * @param callbackData    回调信息。如填写该项则任务完成后，通过callback推送事件给企业。具体请参考应用回调模式中的相应选项
     * @return 任务编号
     */
    public String asyncBatchUpdateUser(List<WeChatUser> weChatUsers, Boolean toInvite, CallbackData callbackData, String applicationName) {
        return asyncHandleUser(weChatUsers, toInvite, callbackData, applicationName, ASYNC_BATCH_UPDATE_USER);
    }

    /**
     * 增量更新成员
     *
     * @param weChatUsers     用户
     * @param toInvite        是否邀请新建的成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
     * @param applicationName 应用名称
     * @return 任务编号
     */
    public String asyncBatchUpdateUser(List<WeChatUser> weChatUsers, Boolean toInvite, String applicationName) {
        return asyncBatchUpdateUser(weChatUsers, toInvite, null, applicationName);
    }

    /**
     * 全量覆盖成员
     *
     * @param weChatUsers     用户
     * @param toInvite        是否邀请新建的成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
     * @param callbackData    回调信息
     * @param applicationName 应用名称
     * @return 任务编号
     */
    public String fullCoverUser(List<WeChatUser> weChatUsers, Boolean toInvite, CallbackData callbackData, String applicationName) {
        return asyncHandleUser(weChatUsers, toInvite, callbackData, applicationName, FULL_COVER_USER);
    }

    /**
     * 全量覆盖成员
     *
     * @param weChatUsers     用户
     * @param toInvite        是否邀请新建的成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
     * @param applicationName 应用名称
     * @return 任务编号
     */
    public String fullCoverUser(List<WeChatUser> weChatUsers, Boolean toInvite, String applicationName) {
        return asyncHandleUser(weChatUsers, toInvite, null, applicationName, FULL_COVER_USER);
    }

    private String asyncHandleUser(List<WeChatUser> weChatUsers, Boolean toInvite, CallbackData callbackData, String applicationName, String type) {

        //上传待增量更新的文件
        String fileName = "";
        if (ASYNC_BATCH_UPDATE_USER.equals(type)) {
            fileName = ASYNC_BATCH_UPDATE_USER_FILE;
        } else if (FULL_COVER_USER.equals(type)) {
            fileName = FULL_COVER_USER_FILE;
        }
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.TEXT_PLAIN.getType(), true, String.format(fileName, DateFormatUtils.format(new Date(), "yyyyMMddhhmmss")));

        try (OutputStream os = fileItem.getOutputStream()) {
            if (CollectionUtils.isNotEmpty(weChatUsers)) {
                IOUtils.write(ASYNC_BATCH_UPDATE_USER_FILE_TITLE, os);
                /*
                 * csv文件格式
                 * 姓名,帐号,手机号,邮箱,所在部门,职位,性别,是否部门内领导,排序,别名,地址,座机,禁用,禁用项说明：(0-启用;1-禁用)
                 * */
                for (WeChatUser weChatUser : weChatUsers) {
                    String line = buildUserLine(weChatUser);
                    IOUtils.write(line, os);
                }
            }

            //上传
            MultipartFile multi = new CommonsMultipartFile(fileItem);
            String mediaId = mediaService.uploadMaterial(multi, MediaService.FILE, applicationName);
            AsyncJobRequest request = buildAsyncJobRequest(mediaId, toInvite, callbackData);
            AsyncJobResponse response = null;
            if (ASYNC_BATCH_UPDATE_USER.equals(type)) {
                response = addressBookClient.asyncBatchUpdateUser(request, applicationName);
            } else if (FULL_COVER_USER.equals(type)) {
                response = addressBookClient.fullCoverUser(request, applicationName);
            }
            return Objects.nonNull(response) ? response.getJobId() : "";
        } catch (Exception e) {
            logger.error("异步更新用户信息异常:{}，type: {}", applicationName, type, e);
        }

        return null;
    }

    private String buildUserLine(WeChatUser weChatUser) {
        if (Objects.nonNull(weChatUser)) {
            return StringUtils.defaultString(weChatUser.getName(), "") + "," +
                    StringUtils.defaultString(weChatUser.getUserId(), "") + "," +
                    StringUtils.defaultString(weChatUser.getMobile(), "") + "," +
                    StringUtils.defaultString(weChatUser.getEmail(), "") + "," +
                    getStringFromList(weChatUser.getDepartment()) + "," +
                    StringUtils.defaultString(weChatUser.getPosition(), "") + "," +
                    getStringFromList(weChatUser.getIsLeaderInDept()) + "," +
                    getStringFromList(weChatUser.getOrder()) + "," +
                    StringUtils.defaultString(weChatUser.getAlias(), "") + "," +
                    StringUtils.defaultString(weChatUser.getAddress(), "") + "," +
                    StringUtils.defaultString(weChatUser.getTelephone(), "") + "," +
                    (weChatUser.getEnable() == null ? "" : String.valueOf(weChatUser.getEnable())) + ",\n";
        }

        return null;
    }

    private AsyncJobRequest buildAsyncJobRequest(String mediaId, Boolean toInvite, CallbackData callbackData) {
        AsyncJobRequest request = new AsyncJobRequest();
        request.setMediaId(mediaId);
        request.setToInvite(toInvite);
        request.setCallback(callbackData);
        return request;
    }

    private String getStringFromList(List<Integer> list) {
        StringBuilder result = new StringBuilder();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer data : list) {
                result.append(data).append(";");
            }
            if (result.toString().endsWith(";")) {
                result.deleteCharAt(result.length() - 1);
            }
        }
        return result.toString();
    }

    /**
     * 全量覆盖部门
     *
     * @param departments     departments
     * @param callbackData    callbackData
     * @param applicationName 应用名称
     * @return String
     */
    public String fullCoverDepartment(List<Department> departments, CallbackData callbackData, String applicationName) {

        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.TEXT_PLAIN.getType(), true, String.format(FULL_COVER_DEPARTMENT_FILE, DateFormatUtils.format(new Date(), "yyyyMMddhhmmss")));

        try (OutputStream os = fileItem.getOutputStream()) {
            if (CollectionUtils.isNotEmpty(departments)) {
                IOUtils.write(FULL_COVER_DEPARTMENT_FILE_TITLE, os,"utf-8");
                /*
                 * csv文件格式
                 * 部门名称,部门ID,父部门ID,排序
                 * */
                for (Department department : departments) {
                    String line = StringUtils.defaultString(department.getName(), "") + "," +
                            (department.getId() == null ? "" : String.valueOf(department.getId())) + "," +
                            (department.getParentId() == null ? "" : String.valueOf(department.getParentId())) + "," +
                            (department.getOrder() == null ? "" : String.valueOf(department.getOrder())) + "\n";

                    IOUtils.write(line, os,"utf-8");
                }
            }

            //上传
            MultipartFile multi = new CommonsMultipartFile(fileItem);
            String mediaId = mediaService.uploadMaterial(multi, MediaService.FILE, applicationName);
            AsyncJobRequest request = buildAsyncJobRequest(mediaId, null, callbackData);
            AsyncJobResponse response = addressBookClient.fullCoverDepartment(request, applicationName);
            return Objects.nonNull(response) ? response.getJobId() : "";
        } catch (Exception e) {
            logger.error("全量覆盖部门异常:{}", applicationName, e);
        }

        return null;
    }

    /**
     * 全量覆盖部门
     *
     * @param departments     部门信息
     * @param applicationName 应用名称
     * @return 任务编号
     */
    public String fullCoverDepartment(List<Department> departments, String applicationName) {
        return fullCoverDepartment(departments, null, applicationName);
    }


    /**
     * 获取job执行结果
     *
     * @param jobId           jobId
     * @param applicationName 应用名称
     * @return AsyncJobResultResponse
     */
    public AsyncJobResultResponse jobResult(String jobId, String applicationName) {
        AsyncJobResultResponse resultResponse = null;
        if (StringUtils.isNotEmpty(jobId)) {
            resultResponse = addressBookClient.jobResult(jobId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("获取任务执行结果成功：{}", resultResponse);
            }
        }

        return resultResponse;
    }

    /**
     * 获取加入企业二维码
     *
     * @param sizeType        qrcode尺寸类型，1: 171 x 171; 2: 399 x 399; 3: 741 x 741; 4: 2052 x 2052
     * @param applicationName 应用名称
     * @return 二维码URL
     */
    public String getCorpQrCode(Integer sizeType, String applicationName) {
        JoinQrCodeResponse response = addressBookClient.getJoinQrCode(sizeType, applicationName);
        return response.getJoinQrCode();
    }

    /**
     * 获取企业活跃成员数
     *
     * @param date            日期
     * @param applicationName 应用名称
     * @return 活跃成员数
     */
    public Integer getActiveStat(Date date, String applicationName) {
        if (date == null) {
            return 0;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = format.format(date);
        ActiveStatRequest request = new ActiveStatRequest();
        request.setDate(formatDate);

        ActiveStatResponse response = addressBookClient.getActiveStat(request, applicationName);
        return response.getActiveCnt();
    }

    /**
     * 导出成员
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return 异步任务编号
     */
    public String exportUser(AddressBookExportRequest request, String applicationName) {
        AsyncJobResponse response = addressBookClient.exportUser(request, applicationName);
        return response.getJobId();
    }

    /**
     * 导出成员详情
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return 异步任务编号
     */
    public String exportUserDetail(AddressBookExportRequest request, String applicationName) {
        AsyncJobResponse response = addressBookClient.exportUserDetail(request, applicationName);
        return response.getJobId();
    }

    /**
     * 导出部门
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return 异步任务编号
     */
    public String exportDepartment(AddressBookExportRequest request, String applicationName) {
        AsyncJobResponse response = addressBookClient.exportDepartment(request, applicationName);
        return response.getJobId();
    }

    /**
     * 导出标签成员
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return 异步任务编号
     */
    public String exportTagUser(AddressBookExportRequest request, String applicationName) {
        AsyncJobResponse response = addressBookClient.exportTagUser(request, applicationName);
        return response.getJobId();
    }

    public ExportResultResponse getExportResult(String jobId, String applicationName) {
        return weChatClient.getExportResult(jobId, applicationName);
    }

    /**
     * 获取导出结果的解密数据
     *
     * @param aesKey 导出时传递的加密密钥
     * @param url    结果下载地址
     * @return 解密后的json串
     */
    public String getDecryptExportData(String aesKey, String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);
        if (responseEntity.getBody() == null) {
            return null;
        }

        byte[] key = Base64.decodeBase64(aesKey + "=");
        byte[] original = null;
        try {
            // 设置解密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(key, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            original = cipher.doFinal(responseEntity.getBody());
        } catch (Exception e) {
            logger.error("", e);
        }

        assert original != null;
        logger.info("数据解密--{}", new String(original));
        return new String(original);
    }

    /**
     * 获取成员ID列表
     *
     * @param request         分页请求
     * @param applicationName 应用名称
     * @return SimpleDeptUserResponse
     */
    public List<DeptUser> getUserIds(CursorPageRequest request, String applicationName) {
        SimpleDeptUserResponse response = addressBookClient.getUserIds(request, applicationName);
        return response.getDeptUsers();
    }

    /**
     * 获取应用的可见范围
     *
     * @param applicationName 应用名称
     * @return 可见范围
     */
    public PermListResponse getPermList(String applicationName) {
        return linkedCorpClient.getPermList(applicationName);
    }

    /**
     * 获取互联企业成员详细信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 详情
     */
    public LinkedCorpUserResponse getUserDetail(LinkedCropUserRequest request, String applicationName) {
        return linkedCorpClient.getUserDetail(request, applicationName);
    }

    /**
     * 获取互联企业部门成员
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 详情
     */
    public LinkedCorpDeptUserResponse getDeptUser(LinkedCorpDeptRequest request, String applicationName) {
        return linkedCorpClient.getDeptUser(request, applicationName);
    }

    /**
     * 获取互联企业部门成员详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 详情
     */
    public LinkedCorpDeptUserListResponse getDeptUserList(LinkedCorpDeptRequest request, String applicationName) {
        return linkedCorpClient.getDeptUserList(request, applicationName);
    }

    /**
     * 获取互联企业部门列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 详情
     */
    public LinkedCorpDeptResponse getDeptList(LinkedCorpDeptRequest request, String applicationName) {
        return linkedCorpClient.getDeptList(request, applicationName);
    }
}
