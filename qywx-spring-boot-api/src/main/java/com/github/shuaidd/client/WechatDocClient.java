package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.wedoc.*;
import com.github.shuaidd.resquest.wedoc.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文档管理
 *
 * @author ddshuai
 */
@FeignClient(name = "WechatDocClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface WechatDocClient extends CommonClient {

    /**
     * 新建文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/create_doc", headers = HEAD)
    CreateDocResponse createDoc(CreateDocRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 重命名文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/rename_doc", headers = HEAD)
    BaseResponse renameDoc(RenameDocRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/del_doc", headers = HEAD)
    BaseResponse delDoc(DocIdFormIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取文档基础信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/get_doc_base_info", headers = HEAD)
    GetDocBaseResponse getDocBaseInfo(DocIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 分享文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/doc_share", headers = HEAD)
    ShareDocResponse shareDoc(DocIdFormIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取文档数据
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/document/get", headers = HEAD)
    DocumentContentResponse getDocumentContent(DocIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取表格行列信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/spreadsheet/get_sheet_properties", headers = HEAD)
    GetSheetPropertiesResponse getSheetProps(DocIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取表格数据
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/spreadsheet/get_sheet_range_data", headers = HEAD)
    SheetDataResponse getSheetData(SheetDataRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑文档内容
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/document/batch_update", headers = HEAD)
    BaseResponse batchUpdate(BatchUpdateDocRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑表格内容
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/spreadsheet/batch_update", headers = HEAD)
    UpdateSheetResponse batchUpdateSheet(BatchUpdateSheetRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取文档权限信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/doc_get_auth", headers = HEAD)
    DocAuthResponse getDocAuth(DocIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改文档查看规则
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/mod_doc_join_rule", headers = HEAD)
    BaseResponse modDocAuth(ModDocRuleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改文档通知范围及权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/mod_doc_member", headers = HEAD)
    BaseResponse modDocMember(ModDocMemberRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改文档安全设置
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/mod_doc_safty_setting", headers = HEAD)
    BaseResponse modDocSafetySetting(ModDocSafetySettingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建收集表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/create_form", headers = HEAD)
    CreateFormResponse createForm(CreateFormRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑收集表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/modify_form", headers = HEAD)
    BaseResponse modifyForm(ModifyFormRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取收集表信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/get_form_info", headers = HEAD)
    GetFormInfoResponse getFormInfo(FormIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询收集表统计信息
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/get_form_statistic", headers = HEAD)
    FormStatisticResponse getFormStatistic(FormStatisticRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 读取收集表答案
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/get_form_answer", headers = HEAD)
    FormAnswerResponse getFormAnswer(FormAnswerRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取智能文档页面结构
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/get_page_hierarchy", headers = HEAD)
    GetSmartDocPageHierarchyResponse getSmartDocPageHierarchy(DocIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加智能文档页面
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/add_page", headers = HEAD)
    SmartDocPageResponse addSmartDocPage(SmartDocPageRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新智能文档页面
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/update_page", headers = HEAD)
    SmartDocPageResponse updateSmartDocPage(SmartDocPageRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除智能文档页面
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/delete_page", headers = HEAD)
    BaseResponse delSmartDocPage(DeleteSmartDocPageRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加智能文档内容块
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/add_blocks", headers = HEAD)
    SmartDocBlockResponse addSmartDocBlocks(SmartDocBlockRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新智能文档内容块
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/update_blocks", headers = HEAD)
    SmartDocBlockResponse updateSmartDocBlocks(SmartDocBlockRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除智能文档内容块
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/delete_blocks", headers = HEAD)
    BaseResponse delSmartDocBlocks(DeleteSmartDocBlockRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取智能文档内容块列表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/get_block_list", headers = HEAD)
    GetSmartDocBlockListResponse getSmartDocBlockList(GetSmartDocBlockListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 提交智能文档导出任务
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/export_task", headers = HEAD)
    ExportSmartDocResponse exportSmartDoc(ExportSmartDocRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询智能文档导出任务结果
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/get_export_result", headers = HEAD)
    GetSmartDocExportResultResponse getSmartDocExportResult(GetSmartDocExportResultRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取智能文档数据源
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/get_smartsheet_info", headers = HEAD)
    GetSmartDocSheetInfoResponse getSmartDocSheetInfo(DocIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加智能文档数据表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/add_smartsheet", headers = HEAD)
    SmartDocSheetResponse addSmartDocSheet(SmartDocSheetRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新智能文档数据表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/update_smartsheet", headers = HEAD)
    SmartDocSheetResponse updateSmartDocSheet(SmartDocSheetRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除智能文档数据表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/delete_smartsheet", headers = HEAD)
    BaseResponse delSmartDocSheet(DeleteSmartDocSheetRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 发布智能文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/publish", headers = HEAD)
    SmartDocPublishResponse publishSmartDoc(SmartDocPublishRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 取消发布智能文档
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/cancel_publish", headers = HEAD)
    BaseResponse cancelSmartDocPublish(DocIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改智能文档发布页可查看范围
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartdoc/publish_setting", headers = HEAD)
    BaseResponse setSmartDocPublishRange(SmartDocPublishRequest request, @RequestParam(HEAD_KEY) String app);
}
