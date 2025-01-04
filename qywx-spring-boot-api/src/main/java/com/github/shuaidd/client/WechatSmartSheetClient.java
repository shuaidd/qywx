package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.smartsheet.*;
import com.github.shuaidd.resquest.smartsheet.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 智能表格
 */
@FeignClient(name = "WechatSmartSheetClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface WechatSmartSheetClient extends CommonClient {

    /**
     * 添加子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/add_sheet", headers = HEAD)
    AddSheetResponse addSheet(AddSheetRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/delete_sheet", headers = HEAD)
    BaseResponse delSheet(DeleteSheetRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/delete_sheet", headers = HEAD)
    BaseResponse updateSheet(UpdateSheetRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/add_view", headers = HEAD)
    AddViewResponse addView(AddViewRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/delete_views", headers = HEAD)
    BaseResponse delView(DeleteViewRequest request, @RequestParam(HEAD_KEY) String app);


    /**
     * 更新视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/update_view", headers = HEAD)
    UpdateViewResponse updateView(UpdateViewRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/add_fields", headers = HEAD)
    AddFieldResponse addField(AddFieldRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/update_fields", headers = HEAD)
    UpdateFieldResponse updateField(UpdateFieldRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/delete_fields", headers = HEAD)
    BaseResponse delField(DeleteFieldRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/add_records", headers = HEAD)
    AddRecordResponse addRecords(AddRecordRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/update_records", headers = HEAD)
    UpdateRecordResponse updateRecords(UpdateRecordRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/delete_records", headers = HEAD)
    BaseResponse delRecords(DelRecordRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/get_sheet", headers = HEAD)
    GetSheetResponse getSheet(GetSheetRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/get_views", headers = HEAD)
    GetViewResponse getViews(GetViewRequest request, @RequestParam(HEAD_KEY) String app);


    /**
     * 查询字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/get_fields", headers = HEAD)
    GetFieldResponse getFields(GetFieldRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/get_records", headers = HEAD)
    GetRecordResponse getRecords(GetRecordRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询智能表格子表权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/content_priv/get_sheet_priv", headers = HEAD)
    GetSheetPrivResponse getSheetPriv(GetSheetPrivRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新智能表格子表权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/content_priv/update_sheet_priv", headers = HEAD)
    BaseResponse updateSheetPriv(UpdateSheetPrivRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 新增智能表格指定成员额外权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @PostMapping(value = "/wedoc/smartsheet/content_priv/create_rule", headers = HEAD)
    CreateRuleResponse createRule(CreateRuleRequest request, @RequestParam(HEAD_KEY) String app);


}
