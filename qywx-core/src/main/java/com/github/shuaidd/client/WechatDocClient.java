package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.vip.VipAccountListResponse;
import com.github.shuaidd.response.vip.VipBatchOperateResponse;
import com.github.shuaidd.response.wedoc.CreateDocResponse;
import com.github.shuaidd.response.wedoc.CreateFormResponse;
import com.github.shuaidd.response.wedoc.DocAuthResponse;
import com.github.shuaidd.response.wedoc.DocumentContentResponse;
import com.github.shuaidd.response.wedoc.ExportSmartDocResponse;
import com.github.shuaidd.response.wedoc.FormAnswerResponse;
import com.github.shuaidd.response.wedoc.FormStatisticResponse;
import com.github.shuaidd.response.wedoc.GetDocBaseResponse;
import com.github.shuaidd.response.wedoc.GetFormInfoResponse;
import com.github.shuaidd.response.wedoc.GetSheetPropertiesResponse;
import com.github.shuaidd.response.wedoc.GetSmartDocBlockListResponse;
import com.github.shuaidd.response.wedoc.GetSmartDocExportResultResponse;
import com.github.shuaidd.response.wedoc.GetSmartDocPageHierarchyResponse;
import com.github.shuaidd.response.wedoc.GetSmartDocSheetInfoResponse;
import com.github.shuaidd.response.wedoc.ShareDocResponse;
import com.github.shuaidd.response.wedoc.SheetDataResponse;
import com.github.shuaidd.response.wedoc.SmartDocBlockResponse;
import com.github.shuaidd.response.wedoc.SmartDocPageResponse;
import com.github.shuaidd.response.wedoc.SmartDocPublishResponse;
import com.github.shuaidd.response.wedoc.SmartDocSheetResponse;
import com.github.shuaidd.response.wedoc.UpdateSheetResponse;
import com.github.shuaidd.resquest.vip.VipAccountListRequest;
import com.github.shuaidd.resquest.vip.VipUserBatchRequest;
import com.github.shuaidd.resquest.wedoc.BatchUpdateDocRequest;
import com.github.shuaidd.resquest.wedoc.BatchUpdateSheetRequest;
import com.github.shuaidd.resquest.wedoc.CreateDocRequest;
import com.github.shuaidd.resquest.wedoc.CreateFormRequest;
import com.github.shuaidd.resquest.wedoc.DeleteSmartDocBlockRequest;
import com.github.shuaidd.resquest.wedoc.DeleteSmartDocPageRequest;
import com.github.shuaidd.resquest.wedoc.DeleteSmartDocSheetRequest;
import com.github.shuaidd.resquest.wedoc.DocIdFormIdRequest;
import com.github.shuaidd.resquest.wedoc.DocIdRequest;
import com.github.shuaidd.resquest.wedoc.ExportSmartDocRequest;
import com.github.shuaidd.resquest.wedoc.FormAnswerRequest;
import com.github.shuaidd.resquest.wedoc.FormIdRequest;
import com.github.shuaidd.resquest.wedoc.FormStatisticRequest;
import com.github.shuaidd.resquest.wedoc.GetSmartDocBlockListRequest;
import com.github.shuaidd.resquest.wedoc.GetSmartDocExportResultRequest;
import com.github.shuaidd.resquest.wedoc.ModDocMemberRequest;
import com.github.shuaidd.resquest.wedoc.ModDocRuleRequest;
import com.github.shuaidd.resquest.wedoc.ModDocSafetySettingRequest;
import com.github.shuaidd.resquest.wedoc.ModifyFormRequest;
import com.github.shuaidd.resquest.wedoc.RenameDocRequest;
import com.github.shuaidd.resquest.wedoc.SheetDataRequest;
import com.github.shuaidd.resquest.wedoc.SmartDocBlockRequest;
import com.github.shuaidd.resquest.wedoc.SmartDocPageRequest;
import com.github.shuaidd.resquest.wedoc.SmartDocPublishRequest;
import com.github.shuaidd.resquest.wedoc.SmartDocSheetRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 文档管理
 *
 * @author ddshuai
 */
public interface WechatDocClient extends CommonClient {

    /**
     * 新建文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/create_doc?app={app}")
    @Headers(HEAD)
    CreateDocResponse createDoc(CreateDocRequest request, @Param("app") String app);

    /**
     * 重命名文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/rename_doc?app={app}")
    @Headers(HEAD)
    BaseResponse renameDoc(RenameDocRequest request, @Param("app") String app);

    /**
     * 删除文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/del_doc?app={app}")
    @Headers(HEAD)
    BaseResponse delDoc(DocIdFormIdRequest request, @Param("app") String app);

    /**
     * 获取文档基础信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/get_doc_base_info?app={app}")
    @Headers(HEAD)
    GetDocBaseResponse getDocBaseInfo(DocIdRequest request, @Param("app") String app);

    /**
     * 分享文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/doc_share?app={app}")
    @Headers(HEAD)
    ShareDocResponse shareDoc(DocIdFormIdRequest request, @Param("app") String app);

    /**
     * 获取文档数据
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/document/get?app={app}")
    @Headers(HEAD)
    DocumentContentResponse getDocumentContent(DocIdRequest request, @Param("app") String app);

    /**
     * 获取表格行列信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/spreadsheet/get_sheet_properties?app={app}")
    @Headers(HEAD)
    GetSheetPropertiesResponse getSheetProps(DocIdRequest request, @Param("app") String app);

    /**
     * 获取表格数据
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/spreadsheet/get_sheet_range_data?app={app}")
    @Headers(HEAD)
    SheetDataResponse getSheetData(SheetDataRequest request, @Param("app") String app);

    /**
     * 编辑文档内容
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/document/batch_update?app={app}")
    @Headers(HEAD)
    BaseResponse batchUpdate(BatchUpdateDocRequest request, @Param("app") String app);

    /**
     * 编辑表格内容
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/spreadsheet/batch_update?app={app}")
    @Headers(HEAD)
    UpdateSheetResponse batchUpdateSheet(BatchUpdateSheetRequest request, @Param("app") String app);

    /**
     * 获取文档权限信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/doc_get_auth?app={app}")
    @Headers(HEAD)
    DocAuthResponse getDocAuth(DocIdRequest request, @Param("app") String app);

    /**
     * 修改文档查看规则
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/mod_doc_join_rule?app={app}")
    @Headers(HEAD)
    BaseResponse modDocAuth(ModDocRuleRequest request, @Param("app") String app);

    /**
     * 修改文档通知范围及权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/mod_doc_member?app={app}")
    @Headers(HEAD)
    BaseResponse modDocMember(ModDocMemberRequest request, @Param("app") String app);

    /**
     * 修改文档安全设置
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/mod_doc_safty_setting?app={app}")
    @Headers(HEAD)
    BaseResponse modDocSafetySetting(ModDocSafetySettingRequest request, @Param("app") String app);

    /**
     * 创建收集表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/create_form?app={app}")
    @Headers(HEAD)
    CreateFormResponse createForm(CreateFormRequest request, @Param("app") String app);

    /**
     * 编辑收集表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/modify_form?app={app}")
    @Headers(HEAD)
    BaseResponse modifyForm(ModifyFormRequest request, @Param("app") String app);

    /**
     * 获取收集表信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/get_form_info?app={app}")
    @Headers(HEAD)
    GetFormInfoResponse getFormInfo(FormIdRequest request, @Param("app") String app);

    /**
     * 查询收集表统计信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/get_form_statistic?app={app}")
    @Headers(HEAD)
    FormStatisticResponse getFormStatistic(FormStatisticRequest request, @Param("app") String app);

    /**
     * 读取收集表答案
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/get_form_answer?app={app}")
    @Headers(HEAD)
    FormAnswerResponse getFormAnswer(FormAnswerRequest request, @Param("app") String app);

    /**
     * 获取智能文档页面结构
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/get_page_hierarchy?app={app}")
    @Headers(HEAD)
    GetSmartDocPageHierarchyResponse getSmartDocPageHierarchy(DocIdRequest request, @Param("app") String app);

    /**
     * 添加智能文档页面
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/add_page?app={app}")
    @Headers(HEAD)
    SmartDocPageResponse addSmartDocPage(SmartDocPageRequest request, @Param("app") String app);

    /**
     * 更新智能文档页面
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/update_page?app={app}")
    @Headers(HEAD)
    SmartDocPageResponse updateSmartDocPage(SmartDocPageRequest request, @Param("app") String app);

    /**
     * 删除智能文档页面
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/delete_page?app={app}")
    @Headers(HEAD)
    BaseResponse delSmartDocPage(DeleteSmartDocPageRequest request, @Param("app") String app);

    /**
     * 添加智能文档内容块
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/add_blocks?app={app}")
    @Headers(HEAD)
    SmartDocBlockResponse addSmartDocBlocks(SmartDocBlockRequest request, @Param("app") String app);

    /**
     * 更新智能文档内容块
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/update_blocks?app={app}")
    @Headers(HEAD)
    SmartDocBlockResponse updateSmartDocBlocks(SmartDocBlockRequest request, @Param("app") String app);

    /**
     * 删除智能文档内容块
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/delete_blocks?app={app}")
    @Headers(HEAD)
    BaseResponse delSmartDocBlocks(DeleteSmartDocBlockRequest request, @Param("app") String app);

    /**
     * 获取智能文档内容块列表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/get_block_list?app={app}")
    @Headers(HEAD)
    GetSmartDocBlockListResponse getSmartDocBlockList(GetSmartDocBlockListRequest request, @Param("app") String app);

    /**
     * 提交智能文档导出任务
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/export_task?app={app}")
    @Headers(HEAD)
    ExportSmartDocResponse exportSmartDoc(ExportSmartDocRequest request, @Param("app") String app);

    /**
     * 查询智能文档导出任务结果
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/get_export_result?app={app}")
    @Headers(HEAD)
    GetSmartDocExportResultResponse getSmartDocExportResult(GetSmartDocExportResultRequest request, @Param("app") String app);

    /**
     * 获取智能文档数据源
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/get_smartsheet_info?app={app}")
    @Headers(HEAD)
    GetSmartDocSheetInfoResponse getSmartDocSheetInfo(DocIdRequest request, @Param("app") String app);

    /**
     * 添加智能文档数据表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/add_smartsheet?app={app}")
    @Headers(HEAD)
    SmartDocSheetResponse addSmartDocSheet(SmartDocSheetRequest request, @Param("app") String app);

    /**
     * 更新智能文档数据表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/update_smartsheet?app={app}")
    @Headers(HEAD)
    SmartDocSheetResponse updateSmartDocSheet(SmartDocSheetRequest request, @Param("app") String app);

    /**
     * 删除智能文档数据表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/delete_smartsheet?app={app}")
    @Headers(HEAD)
    BaseResponse delSmartDocSheet(DeleteSmartDocSheetRequest request, @Param("app") String app);

    /**
     * 发布智能文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/publish?app={app}")
    @Headers(HEAD)
    SmartDocPublishResponse publishSmartDoc(SmartDocPublishRequest request, @Param("app") String app);

    /**
     * 取消发布智能文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/cancel_publish?app={app}")
    @Headers(HEAD)
    BaseResponse cancelSmartDocPublish(DocIdRequest request, @Param("app") String app);

    /**
     * 修改智能文档发布页可查看范围
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartdoc/publish_setting?app={app}")
    @Headers(HEAD)
    BaseResponse setSmartDocPublishRange(SmartDocPublishRequest request, @Param("app") String app);

    /**
     * 批量分配高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 分配结果
     */
    @RequestLine("POST /wedoc/vip/batch_add?app={app}")
    @Headers(HEAD)
    VipBatchOperateResponse batchAddVipAccounts(VipUserBatchRequest request, @Param("app") String app);

    /**
     * 批量撤销高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 撤销分配结果
     */
    @RequestLine("POST /wedoc/vip/batch_del?app={app}")
    @Headers(HEAD)
    VipBatchOperateResponse batchDeleteVipAccounts(VipUserBatchRequest request, @Param("app") String app);

    /**
     * 获取高级功能账号列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 高级功能账号列表
     */
    @RequestLine("POST /wedoc/vip/list?app={app}")
    @Headers(HEAD)
    VipAccountListResponse listVipAccounts(VipAccountListRequest request, @Param("app") String app);
}
