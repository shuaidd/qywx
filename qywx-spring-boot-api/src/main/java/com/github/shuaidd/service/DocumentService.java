package com.github.shuaidd.service;

import com.github.shuaidd.client.WechatDocClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.wedoc.CreateDocResponse;
import com.github.shuaidd.response.wedoc.DocumentContentResponse;
import com.github.shuaidd.response.wedoc.GetSheetPropertiesResponse;
import com.github.shuaidd.resquest.wedoc.CreateDocRequest;
import com.github.shuaidd.resquest.wedoc.DocIdRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 文档管理
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
     * @param docId document id
     * @param application application
     * @return res
     */
    public DocumentContentResponse getDocContent(String docId,String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        if (StringUtils.isEmpty(docId)) {
            throw new ParamCheckException("docId 不能为空，请检查！！！");
        }
        return wechatDocClient.getDocumentContent(request,application);
    }

    /**
     * 新建文档
     * @param request request
     * @param application application
     * @return res
     */
    public CreateDocResponse createDocument(CreateDocRequest request, String application) {
       return wechatDocClient.createDoc(request,application);
    }

    /**
     * 获取表格行列信息
     * @param docId docId
     * @param application application
     * @return props
     */
    public GetSheetPropertiesResponse getSheetProps(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        if (StringUtils.isEmpty(docId)) {
            throw new ParamCheckException("docId 不能为空，请检查！！！");
        }
        return wechatDocClient.getSheetProps(request,application);
    }
}
