package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.tool.CreateLivingResponse;
import com.github.shuaidd.response.tool.CreateMeetingResponse;
import com.github.shuaidd.response.tool.DialRecordResponse;
import com.github.shuaidd.response.tool.GetMeetingForUserResponse;
import com.github.shuaidd.response.tool.GetUserLivingResponse;
import com.github.shuaidd.response.tool.LivingCodeResponse;
import com.github.shuaidd.response.tool.LivingInfoResponse;
import com.github.shuaidd.response.tool.LivingShareResponse;
import com.github.shuaidd.response.tool.MeetingDetailResponse;
import com.github.shuaidd.response.tool.WatchStatInfoResponse;
import com.github.shuaidd.response.wedrive.CapacityResponse;
import com.github.shuaidd.response.wedrive.CreateFileResponse;
import com.github.shuaidd.response.wedrive.CreateSpaceResponse;
import com.github.shuaidd.response.wedrive.DownloadFileResponse;
import com.github.shuaidd.response.wedrive.FileInfoResponse;
import com.github.shuaidd.response.wedrive.FileShareResponse;
import com.github.shuaidd.response.wedrive.FileUploadFinishResponse;
import com.github.shuaidd.response.wedrive.InitUploadFileResponse;
import com.github.shuaidd.response.wedrive.MoveFileResponse;
import com.github.shuaidd.response.wedrive.ProInfoResponse;
import com.github.shuaidd.response.wedrive.RenameFileResponse;
import com.github.shuaidd.response.wedrive.SpaceFileResponse;
import com.github.shuaidd.response.wedrive.SpaceInfoResponse;
import com.github.shuaidd.response.wedrive.SpaceShareResponse;
import com.github.shuaidd.response.vip.VipAccountListResponse;
import com.github.shuaidd.response.vip.VipBatchOperateResponse;
import com.github.shuaidd.resquest.tool.CancelLivingRequest;
import com.github.shuaidd.resquest.tool.CancelMeetingRequest;
import com.github.shuaidd.resquest.tool.DeleteLivingRequest;
import com.github.shuaidd.resquest.tool.DialRecordRequest;
import com.github.shuaidd.resquest.tool.GetMeetingDetailRequest;
import com.github.shuaidd.resquest.tool.GetMeetingForUserRequest;
import com.github.shuaidd.resquest.tool.GetUserLivingRequest;
import com.github.shuaidd.resquest.tool.GetWatchStatRequest;
import com.github.shuaidd.resquest.tool.LivingCodeRequest;
import com.github.shuaidd.resquest.tool.LivingRequest;
import com.github.shuaidd.resquest.tool.LivingShareRequest;
import com.github.shuaidd.resquest.tool.MeetingRequest;
import com.github.shuaidd.resquest.wedrive.CommonSpaceRequest;
import com.github.shuaidd.resquest.wedrive.CreateFileRequest;
import com.github.shuaidd.resquest.wedrive.CreateSpaceRequest;
import com.github.shuaidd.resquest.wedrive.DeleteFileRequest;
import com.github.shuaidd.resquest.wedrive.DismissSpaceRequest;
import com.github.shuaidd.resquest.wedrive.DownloadFileRequest;
import com.github.shuaidd.resquest.wedrive.FileAclRequest;
import com.github.shuaidd.resquest.wedrive.FileInfoRequest;
import com.github.shuaidd.resquest.wedrive.FileSettingRequest;
import com.github.shuaidd.resquest.wedrive.FileUploadFinishRequest;
import com.github.shuaidd.resquest.wedrive.FileUploadPartRequest;
import com.github.shuaidd.resquest.wedrive.InitUploadFileRequest;
import com.github.shuaidd.resquest.wedrive.MoveFileRequest;
import com.github.shuaidd.resquest.wedrive.ProInfoRequest;
import com.github.shuaidd.resquest.wedrive.QueryFileRequest;
import com.github.shuaidd.resquest.wedrive.RenameFileRequest;
import com.github.shuaidd.resquest.wedrive.RenameSpaceRequest;
import com.github.shuaidd.resquest.wedrive.SpaceAclRequest;
import com.github.shuaidd.resquest.wedrive.SpaceInfoRequest;
import com.github.shuaidd.resquest.wedrive.SpaceSettingRequest;
import com.github.shuaidd.resquest.wedrive.UploadFileRequest;
import com.github.shuaidd.resquest.vip.VipAccountListRequest;
import com.github.shuaidd.resquest.vip.VipUserBatchRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 效率工具
 *
 * @author ddshuai
 * date 2022-09-01 09:37
 **/
public interface EfficiencyToolClient extends CommonClient {

    /**
     * 创建预约直播
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateLivingResponse
     */
    @RequestLine("POST /living/create?app={app}")
    @Headers(HEAD)
    CreateLivingResponse createLiving(LivingRequest request, @Param("app") String app);

    /**
     * 修改预约直播
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /living/modify?app={app}")
    @Headers(HEAD)
    BaseResponse modifyLiving(LivingRequest request, @Param("app") String app);

    /**
     * 取消预约直播
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /living/cancel?app={app}")
    @Headers(HEAD)
    BaseResponse cancelLiving(CancelLivingRequest request, @Param("app") String app);

    /**
     * 删除直播回放
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /living/delete_replay_data?app={app}")
    @Headers(HEAD)
    BaseResponse delLiving(DeleteLivingRequest request, @Param("app") String app);

    /**
     * 获取微信观看直播凭证
     *
     * @param request 请求体
     * @param app     应用名
     * @return LivingCodeResponse
     */
    @RequestLine("POST /living/get_living_code?app={app}")
    @Headers(HEAD)
    LivingCodeResponse getLivingCode(LivingCodeRequest request, @Param("app") String app);

    /**
     * 获取成员直播ID列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetUserLivingResponse
     */
    @RequestLine("POST /living/get_user_all_livingid?app={app}")
    @Headers(HEAD)
    GetUserLivingResponse getUserAllLivingId(GetUserLivingRequest request, @Param("app") String app);

    /**
     * 获取直播详情
     *
     * @param livingId 直播编号
     * @param app      应用名
     * @return LivingInfoResponse
     */
    @RequestLine("GET /living/get_living_info?livingid={livingid}&app={app}")
    @Headers(HEAD)
    LivingInfoResponse getLivingInfo(@Param("livingid") String livingId, @Param("app") String app);

    /**
     * 获取直播观看明细
     *
     * @param request 请求体
     * @param app     应用名
     * @return WatchStatInfoResponse
     */
    @RequestLine("POST /living/get_watch_stat?app={app}")
    @Headers(HEAD)
    WatchStatInfoResponse getWatchStat(GetWatchStatRequest request, @Param("app") String app);

    /**
     * 获取跳转小程序商城的直播观众信息
     *
     * @param request 请求体
     * @param app     应用名
     * @return LivingShareResponse
     */
    @RequestLine("POST /living/get_living_share_info?app={app}")
    @Headers(HEAD)
    LivingShareResponse getLivingShareInfo(LivingShareRequest request, @Param("app") String app);

    /**
     * 创建预约会议
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateMeetingResponse
     */
    @RequestLine("POST /meeting/create?app={app}")
    @Headers(HEAD)
    CreateMeetingResponse createMeeting(MeetingRequest request, @Param("app") String app);

    /**
     * 修改预约会议
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse updateMeeting(MeetingRequest request, @Param("app") String app);
     */
    @RequestLine("POST /meeting/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateMeeting(MeetingRequest request, @Param("app") String app);

    /**
     * 取消预约会议
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /meeting/cancel?app={app}")
    @Headers(HEAD)
    BaseResponse cancelMeeting(CancelMeetingRequest request, @Param("app") String app);

    /**
     * 获取成员会议ID列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetMeetingForUserResponse
     */
    @RequestLine("POST /meeting/get_user_meetingid?app={app}")
    @Headers(HEAD)
    GetMeetingForUserResponse getUserMeetingId(GetMeetingForUserRequest request, @Param("app") String app);

    /**
     * 获取会议详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return MeetingDetailResponse
     */
    @RequestLine("POST /meeting/get_info?app={app}")
    @Headers(HEAD)
    MeetingDetailResponse getMeetingInfo(GetMeetingDetailRequest request, @Param("app") String app);

    /**
     * 获取公费电话拨打记录
     *
     * @param request 请求体
     * @param app     应用名
     * @return DialRecordResponse
     */
    @RequestLine("POST /dial/get_dial_record?app={app}")
    @Headers(HEAD)
    DialRecordResponse getDialRecord(DialRecordRequest request, @Param("app") String app);

    /* *************************************** 微盘 *************************************** */

    /**
     * 新建空间
     *
     * @param request 请求
     * @param app     应用名
     * @return 空间编号
     */
    @RequestLine("POST /wedrive/space_create?app={app}")
    @Headers(HEAD)
    CreateSpaceResponse createSpace(CreateSpaceRequest request, @Param("app") String app);

    /**
     * 该接口用于重命名已有空间，接收userid参数，以空间管理员身份来重命名
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/space_rename?app={app}")
    @Headers(HEAD)
    BaseResponse renameSpace(RenameSpaceRequest request, @Param("app") String app);

    /**
     * 该接口用于解散已有空间，需要以空间管理员身份来解散。
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/space_dismiss?app={app}")
    @Headers(HEAD)
    BaseResponse dismissSpace(DismissSpaceRequest request, @Param("app") String app);

    /**
     * 获取空间/相册信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 空间信息
     */
    @RequestLine("POST /wedrive/space_info?app={app}")
    @Headers(HEAD)
    SpaceInfoResponse spaceInfo(SpaceInfoRequest request, @Param("app") String app);

    /**
     * 添加成员/部门
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/space_acl_add?app={app}")
    @Headers(HEAD)
    BaseResponse addSpaceAcl(SpaceAclRequest request, @Param("app") String app);

    /**
     * 移除成员/部门
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/space_acl_del?app={app}")
    @Headers(HEAD)
    BaseResponse delSpaceAcl(SpaceAclRequest request, @Param("app") String app);

    /**
     * 安全设置
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/space_setting?app={app}")
    @Headers(HEAD)
    BaseResponse spaceSetting(SpaceSettingRequest request, @Param("app") String app);

    /**
     * 获取邀请链接
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/space_share?app={app}")
    @Headers(HEAD)
    SpaceShareResponse spaceShare(CommonSpaceRequest request, @Param("app") String app);

    /**
     * 获取文件列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 文件列表
     */
    @RequestLine("POST /wedrive/file_list?app={app}")
    @Headers(HEAD)
    SpaceFileResponse listFile(QueryFileRequest request, @Param("app") String app);

    /**
     * 获取文件列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 文件编号 地址
     */
    @RequestLine("POST /wedrive/file_create?app={app}")
    @Headers(HEAD)
    CreateFileResponse createFile(CreateFileRequest request, @Param("app") String app);

    /**
     * 上传文件
     *
     * @param request 请求
     * @param app     应用名
     * @return 文件编号 地址
     */
    @RequestLine("POST /wedrive/file_upload?app={app}")
    @Headers(HEAD)
    CreateFileResponse uploadFile(UploadFileRequest request, @Param("app") String app);

    /**
     * 下载文件
     *
     * @param request 请求
     * @param app     应用名
     * @return 文件地址
     */
    @RequestLine("POST /wedrive/file_download?app={app}")
    @Headers(HEAD)
    DownloadFileResponse downloadFile(DownloadFileRequest request, @Param("app") String app);

    /**
     * 重命名文件
     *
     * @param request 请求
     * @param app     应用名
     * @return 文件信息
     */
    @RequestLine("POST /wedrive/file_rename?app={app}")
    @Headers(HEAD)
    RenameFileResponse renameFile(RenameFileRequest request, @Param("app") String app);

    /**
     * 移动文件
     *
     * @param request 请求
     * @param app     应用名
     * @return 文件信息
     */
    @RequestLine("POST /wedrive/file_move?app={app}")
    @Headers(HEAD)
    MoveFileResponse moveFile(MoveFileRequest request, @Param("app") String app);

    /**
     * 删除文件
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/file_delete?app={app}")
    @Headers(HEAD)
    BaseResponse deleteFile(DeleteFileRequest request, @Param("app") String app);

    /**
     * 文件信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/file_info?app={app}")
    @Headers(HEAD)
    FileInfoResponse fileInfo(FileInfoRequest request, @Param("app") String app);

    /**
     * 文件权限 新增成员
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/file_acl_add?app={app}")
    @Headers(HEAD)
    BaseResponse addFileAcl(FileAclRequest request, @Param("app") String app);

    /**
     * 文件权限 删除成员
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/file_acl_del?app={app}")
    @Headers(HEAD)
    BaseResponse delFileAcl(FileAclRequest request, @Param("app") String app);

    /**
     * 文件权限 分享设置
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/file_setting?app={app}")
    @Headers(HEAD)
    BaseResponse fileSetting(FileSettingRequest request, @Param("app") String app);

    /**
     * 文件权限 获取分享链接
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/file_share?app={app}")
    @Headers(HEAD)
    FileShareResponse getFileShare(FileInfoRequest request, @Param("app") String app);

    /**
     * 获取盘专业版信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/mng_pro_info?app={app}")
    @Headers(HEAD)
    ProInfoResponse proInfo(ProInfoRequest request, @Param("app") String app);

    /**
     * 获取盘容量信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 容量信息
     */
    @RequestLine("POST /wedrive/mng_capacity?app={app}")
    @Headers(HEAD)
    CapacityResponse capacity(ProInfoRequest request, @Param("app") String app);

    /**
     * 分块上传初始化
     *
     * @param request 请求
     * @param app     应用名
     * @return 初始化信息
     */
    @RequestLine("POST /wedrive/file_upload_init?app={app}")
    @Headers(HEAD)
    InitUploadFileResponse initUploadFile(InitUploadFileRequest request, @Param("app") String app);

    /**
     * 分块上传文件
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/file_upload_part?app={app}")
    @Headers(HEAD)
    BaseResponse fileUploadPart(FileUploadPartRequest request, @Param("app") String app);

    /**
     * 分块上传完成
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作结果
     */
    @RequestLine("POST /wedrive/file_upload_finish?app={app}")
    @Headers(HEAD)
    FileUploadFinishResponse fileUploadFinish(FileUploadFinishRequest request, @Param("app") String app);

    /**
     * 批量分配高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 分配结果
     */
    @RequestLine("POST /wedrive/vip/batch_add?app={app}")
    @Headers(HEAD)
    VipBatchOperateResponse batchAddVipAccounts(VipUserBatchRequest request, @Param("app") String app);

    /**
     * 批量撤销高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 撤销分配结果
     */
    @RequestLine("POST /wedrive/vip/batch_del?app={app}")
    @Headers(HEAD)
    VipBatchOperateResponse batchDeleteVipAccounts(VipUserBatchRequest request, @Param("app") String app);

    /**
     * 获取高级功能账号列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 高级功能账号列表
     */
    @RequestLine("POST /wedrive/vip/list?app={app}")
    @Headers(HEAD)
    VipAccountListResponse listVipAccounts(VipAccountListRequest request, @Param("app") String app);
}
