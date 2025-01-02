package com.github.shuaidd;

import com.github.shuaidd.response.wedoc.CreateDocResponse;
import com.github.shuaidd.response.wedoc.DocumentContentResponse;
import com.github.shuaidd.response.wedoc.GetSheetPropertiesResponse;
import com.github.shuaidd.resquest.wedoc.CreateDocRequest;
import com.github.shuaidd.service.DocumentService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DocumentServiceTest extends AbstractTest {

    private DocumentService documentService;

    public static final String DA = "doc-assistance";

    @BeforeEach
    public void init() {
        this.documentService = weChatManager.documentService();
    }

    /**
     * "url" : "https://doc.weixin.qq.com/sheet/e3_AMYAmhR_ANIhu12GmwGQbG6HHkj35_a?scode=AAkAFgfQAAcEeJVW1qAMYAmhR_ANI",
     * "docid" : "dcPDolyAlKuXCJu7mJiTSr8JCIAtiqwnjuJbTcDA9v-1ns4cRVxE1NXllr1bgiLIhEcM1QRTQVU-cKuIDlnsfMDQ"
     */
    @Test
    public void createDoc() {
        CreateDocRequest request = new CreateDocRequest();
        request.setDocName("测试11");
        request.setAdminUsers(Lists.newArrayList("20170410022717"));
        request.setDocType(4);
        CreateDocResponse res = documentService.createDocument(request,DA);
        logger.info("url {} ,docid {}",res.getUrl(),res.getDocId());
    }

    @Test
    public void getDocContents() {
        DocumentContentResponse res = documentService.getDocContent("dcPDolyAlKuXCJu7mJiTSr8JCIAtiqwnjuJbTcDA9v-1ns4cRVxE1NXllr1bgiLIhEcM1QRTQVU-cKuIDlnsfMDQ",DA);
        logger.info("{}",res.getDocument());

    }

    @Test
    public void getSheetProps() {
        GetSheetPropertiesResponse response = documentService.getSheetProps("dcd9CYW1rjLM626qqgGWVxhi5TiX4qRH1pZvA3Z70rY0m41v2fwfxgjV7gHNNYJhXVAOO34DGlp6o3FLZYIogVNg",DA);
        logger.info("{}",response.getProperties());
    }
}
