package com.github.shuaidd;

import com.github.shuaidd.dto.wedoc.*;
import com.github.shuaidd.response.wedoc.*;
import com.github.shuaidd.resquest.wedoc.*;
import com.github.shuaidd.service.DocumentService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DocumentServiceTest extends AbstractTest {

    private DocumentService documentService;

    public static final String DA = "doc-assistance";
    public static final String DOC_ID = "dcd9CYW1rjLM626qqgGWVxhi5TiX4qRH1pZvA3Z70rY0m41v2fwfxgjV7gHNNYJhXVAOO34DGlp6o3FLZYIogVNg";

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
        CreateDocResponse res = documentService.createDocument(request, DA);
        logger.info("url {} ,docid {}", res.getUrl(), res.getDocId());
    }

    @Test
    public void getDocContents() {
        DocumentContentResponse res = documentService.getDocContent("dcPDolyAlKuXCJu7mJiTSr8JCIAtiqwnjuJbTcDA9v-1ns4cRVxE1NXllr1bgiLIhEcM1QRTQVU-cKuIDlnsfMDQ", DA);
        logger.info("{}", res.getDocument());
    }

    @Test
    public void getSheetProps() {
        GetSheetPropertiesResponse response = documentService.getSheetProps(DOC_ID, DA);
        logger.info("{}", response.getProperties());
    }

    @Test
    public void batchUpdateSheet() {
        BatchUpdateSheetRequest batch = new BatchUpdateSheetRequest();
        UpdateSheetRequest request = new UpdateSheetRequest();
        UpdateSheetRange range = new UpdateSheetRange();
        range.setSheetId("d60fWw");
        GridData gridData = new GridData();
        gridData.setStartColumn(1);
        gridData.setStartRow(1);
        gridData.setRows(rowData());
        range.setGridData(gridData);
        request.setUpdateRangeRequest(range);

        batch.setDocId(DOC_ID);
        batch.setRequests(Lists.newArrayList(request));
        UpdateSheetResponse res = documentService.batchUpdateSheet(batch, DA);
        logger.info("Updated batch {}", res);
    }

    private List<RowData> rowData() {
        List<RowData> rows = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            RowData rowData = new RowData();
            List<CellData> cells = new ArrayList<>();
            for (int i1 = 0; i1 < 10; i1++) {
                CellData cellData = new CellData().setCellValue(new CellValue().setLink(new Link().setUrl("https://boss.shinyway.com/index.html").setText(RandomStringUtils.random(10, true, true))));
                cellData.setCellFormat(new CellFormat().setTextFormat(new TextFormat().setBold(true)));
                cells.add(cellData);
            }
            rowData.setValues(cells);
            rows.add(rowData);
        }


        return rows;
    }

    @Test
    public void getDocBaseInfo() {
        GetDocBaseResponse res = documentService.getDocBaseInfo(DOC_ID, DA);
        logger.info("{}", res);
    }

    @Test
    public void shareDoc() {
        DocIdFormIdRequest request = new DocIdFormIdRequest();
        request.setDocId(DOC_ID);
        ShareDocResponse res = documentService.shareDoc(request, DA);
        logger.info("Sharing doc {}", res.getShareUrl());
    }

    @Test
    public void getSheetData() {
        SheetDataRequest request = new SheetDataRequest();
        request.setDocId(DOC_ID);
        request.setSheetId("d60fWw");
        request.setRange("A1:B2");
        SheetDataResponse res = documentService.getSheetData(request, DA);
        logger.info("Sharing sheet {}", res.getData());
    }
}
