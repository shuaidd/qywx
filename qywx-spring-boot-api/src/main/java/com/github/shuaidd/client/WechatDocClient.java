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
}
