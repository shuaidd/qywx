package com.github.shuaidd.service;

import com.github.shuaidd.client.WechatDocClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.wedoc.*;
import com.github.shuaidd.resquest.wedoc.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 文档管理
 *
 * @author ddshuai
 */
@Service
public class DocumentService extends AbstractBaseService {

    private final WechatDocClient wechatDocClient;

    public DocumentService(WechatDocClient wechatDocClient) {
        this.wechatDocClient = wechatDocClient;
    }

    /**
     * 获取文档数据
     *
     * @param docId       document id
     * @param application application
     * @return res
     */
    public DocumentContentResponse getDocContent(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        if (StringUtils.isEmpty(docId)) {
            throw new ParamCheckException("docId 不能为空，请检查！！！");
        }
        return wechatDocClient.getDocumentContent(request, application);
    }

    /**
     * 新建文档
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public CreateDocResponse createDocument(CreateDocRequest request, String application) {
        return wechatDocClient.createDoc(request, application);
    }

    /**
     * 重命名文档
     *
     * @param request     request
     * @param application application
     */
    public void renameDoc(RenameDocRequest request, String application) {
        wechatDocClient.renameDoc(request, application);
    }

    /**
     * 删除文档
     *
     * @param request     request
     * @param application application
     */
    public void delDoc(DocIdFormIdRequest request, String application) {
        wechatDocClient.delDoc(request, application);
    }

    /**
     * 获取文档基础信息
     *
     * @param request     request
     * @param application application
     */
    public GetDocBaseResponse getDocBaseInfo(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        return wechatDocClient.getDocBaseInfo(request, application);
    }

    /**
     * 分享文档
     *
     * @param request     request
     * @param application application
     */
    public ShareDocResponse shareDoc(DocIdFormIdRequest request, String application) {
        return wechatDocClient.shareDoc(request, application);
    }

    /**
     * 获取表格行列信息
     *
     * @param docId       docId
     * @param application application
     * @return props
     */
    public GetSheetPropertiesResponse getSheetProps(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        if (StringUtils.isEmpty(docId)) {
            throw new ParamCheckException("docId 不能为空，请检查！！！");
        }
        return wechatDocClient.getSheetProps(request, application);
    }

    /**
     * 获取表格数据
     *
     * @param request     request
     * @param application application
     * @return props
     */
    public SheetDataResponse getSheetData(SheetDataRequest request, String application) {
        return wechatDocClient.getSheetData(request, application);
    }

    /**
     * 编辑文档内容
     *
     * @param request     request
     * @param application application
     * @return props
     */
    public BaseResponse batchUpdate(BatchUpdateDocRequest request, String application) {
        return wechatDocClient.batchUpdate(request, application);
    }

    /**
     * 编辑表格内容
     *
     * @param request     request
     * @param application application
     * @return props
     */
    public UpdateSheetResponse batchUpdateSheet(BatchUpdateSheetRequest request, String application) {
        return wechatDocClient.batchUpdateSheet(request, application);
    }

    /**
     * 获取文档权限信息
     *
     * @param docId       docId
     * @param application application
     * @return props
     */
    public DocAuthResponse getDocAuth(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        return wechatDocClient.getDocAuth(request, application);
    }

    /**
     * 修改文档查看规则
     *
     * @param request     request
     * @param application application
     */
    public void modDocAuth(ModDocRuleRequest request, String application) {
        wechatDocClient.modDocAuth(request, application);
    }

    /**
     * 修改文档通知范围及权限
     *
     * @param request     request
     * @param application application
     */
    public void modDocMember(ModDocMemberRequest request, String application) {
        wechatDocClient.modDocMember(request, application);
    }

    /**
     * 修改文档安全设置
     *
     * @param request     request
     * @param application application
     */
    public void modDocSafetySetting(ModDocSafetySettingRequest request, String application) {
        wechatDocClient.modDocSafetySetting(request, application);
    }
}
