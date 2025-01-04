package com.github.shuaidd;

import com.github.shuaidd.dto.smartsheet.CellTextValue;
import com.github.shuaidd.dto.smartsheet.Properties;
import com.github.shuaidd.dto.smartsheet.SheetField;
import com.github.shuaidd.dto.smartsheet.SheetRecord;
import com.github.shuaidd.response.smartsheet.*;
import com.github.shuaidd.response.wedoc.CreateDocResponse;
import com.github.shuaidd.resquest.smartsheet.*;
import com.github.shuaidd.resquest.wedoc.CreateDocRequest;
import com.github.shuaidd.service.SmartSheetService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartSheetServiceTest extends AbstractTest {

    private SmartSheetService smartSheetService;
    public static final String DA = "doc-assistance";
    public static final String DOC_ID = "dcvV4dWTXK826wFi_V28xAiBkXtWgsM-UpXuAkpYTjmgVDYTJ4O0LYGE4qeZ59JS7WEpv9Ca6CEJPlE0ZVt7cQQg";

    @BeforeEach
    public void init() {
        this.smartSheetService = weChatManager.smartSheetService();
    }

    /**
     * {
     * "errcode" : "0",
     * "errmsg" : "ok",
     * "next_cursor" : null,
     * "has_more" : null,
     * "url" : "https://doc.weixin.qq.com/smartsheet/s3_AMYAmhR_ANIg102trFmThSdb8vk4I_a?scode=AAkAFgfQAAcOq9GpxfAMYAmhR_ANI",
     * "docid" : "dcvV4dWTXK826wFi_V28xAiBkXtWgsM-UpXuAkpYTjmgVDYTJ4O0LYGE4qeZ59JS7WEpv9Ca6CEJPlE0ZVt7cQQg"
     * }
     */
    @Test
    public void create() {
        CreateDocRequest request = new CreateDocRequest();
        request.setAdminUsers(Lists.newArrayList("20170410022717"));
        request.setDocName("测试智能表格");
        CreateDocResponse res = smartSheetService.createSmartSheet(request, DA);
        logger.info("{},{}",res.getDocId(),res.getUrl());
    }

    /**
     * {
     * "errcode" : "0",
     * "errmsg" : "ok",
     * "next_cursor" : null,
     * "has_more" : null,
     * "properties" : {
     * "title" : "first",
     * "index" : 1,
     * "sheet_id" : "8ygigN"
     * }
     * }
     */
    @Test
    public void addSheet() {
        AddSheetRequest request = new AddSheetRequest();
        request.setDocid(DOC_ID);
        request.setProperties(new Properties().setIndex(1).setTitle("first"));
        AddSheetResponse res = smartSheetService.addSheet(request, DA);
        logger.info("{}",res.getProperties());
    }

    @Test
    public void updateSheet() {
        UpdateSheetRequest request = new UpdateSheetRequest();
        request.setDocId(DOC_ID);
        request.setProperties(new Properties().setSheetId("8ygigN").setIndex(2).setTitle("first"));
        smartSheetService.updateSheet(request,DA);
    }

    @Test
    public void getSheet() {
        GetSheetRequest request = new GetSheetRequest().setDocId(DOC_ID).setSheetId("8ygigN");
        GetSheetResponse res = smartSheetService.getSheet(request, DA);
        logger.info("{}", res.getSheetList());
    }

    @Test
    public void delSheet() {
        DeleteSheetRequest request = new DeleteSheetRequest();
        request.setSheetId("8ygigN1");
        request.setDocId(DOC_ID);
        smartSheetService.delSheet(request,DA);
    }

    @Test
    public void addView() {
        AddViewRequest request = new AddViewRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN").setViewType("VIEW_TYPE_GRID").setViewTitle("个人数据1");
        AddViewResponse res = smartSheetService.addView(request,DA);
        logger.info("{}",res.getView());
    }

    @Test
    public void updateView() {
        UpdateViewRequest request = new UpdateViewRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN").setViewId("vU9BNv").setViewTitle("个人数据2");
        UpdateViewResponse res = smartSheetService.updateView(request,DA);
        logger.info("{}",res.getView());
    }

    @Test
    public void deleteView() {
        DeleteViewRequest request = new DeleteViewRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN").setViewIds(Lists.newArrayList("22"));
        smartSheetService.delView(request,DA);
    }

    @Test
    public void addField() {
        AddFieldRequest request = new AddFieldRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN");
        request.setFields(fields());
        AddFieldResponse res = smartSheetService.addField(request, DA);
        logger.info("{}", res.getFields());
    }

    private List<SheetField> fields() {
        return Lists.newArrayList("用戶姓名1", "账号", "手机号", "职位", "状态").stream().map(item -> new SheetField().setFieldType("FIELD_TYPE_TEXT").setFieldTitle(item)).toList();
    }

    private List<SheetField> updateFields() {
        return Lists.newArrayList(new SheetField().setFieldId("fajTdf").setFieldType("FIELD_TYPE_TEXT").setFieldTitle("用戶姓名33"));
    }

    @Test
    public void updateField() {
        UpdateFieldRequest request = new UpdateFieldRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN");
        request.setFields(updateFields());
        UpdateFieldResponse res = smartSheetService.updateField(request, DA);
        logger.info("{}", res.getFields());
    }

    @Test
    public void delField() {
        DeleteFieldRequest request = new DeleteFieldRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN").setFieldIds(Lists.newArrayList("fajTdf"));
        smartSheetService.delField(request,DA);
    }

    @Test
    public void getField() {
        GetFieldRequest request = new GetFieldRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN");
        GetFieldResponse res = smartSheetService.getFields(request,DA);
        logger.info("{}",res.getFields());
    }

    @Test
    public void addRecords() {
        AddRecordRequest request = new AddRecordRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN");
        request.setRecords(records());
        AddRecordResponse res = smartSheetService.addRecords(request, DA);
        logger.info("{}", res.getRecords());
    }

    @Test
    public void updateRecords() {
        UpdateRecordRequest request = new UpdateRecordRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN");
        request.setRecords(updateRds());
        UpdateRecordResponse res = smartSheetService.updateRecords(request, DA);
        logger.info("{}", res.getRecords());
    }

    private List<SheetRecord> records() {
        SheetRecord record = new SheetRecord();
        Map<String, Object> values = new HashMap<>();
        Lists.newArrayList("用戶姓名1:帅冬冬", "账号:ddshuai", "手机号:15268781020", "职位:开发", "状态:在职").forEach(item -> {
            String[] a = item.split(":");
            values.put(a[0], Lists.newArrayList(new CellTextValue().setType("text").setText(a[1])));
        });
        record.setValues(values);
        return Lists.newArrayList(record);
    }

    private List<SheetRecord> updateRds() {
        SheetRecord record = new SheetRecord();
        Map<String, Object> values = new HashMap<>();
        Lists.newArrayList("用戶姓名:帅冬冬", "账号:ddshuai", "手机号:15268781021", "职位:开发攻城狮1", "状态:离职").forEach(item -> {
            String[] a = item.split(":");
            values.put(a[0], Lists.newArrayList(new CellTextValue().setType("text").setText(a[1])));
        });
        record.setValues(values);
        record.setRecordId("r6bmtX");
        return Lists.newArrayList(record);
    }

    @Test
    public void getRecords() {
        GetRecordRequest request = new GetRecordRequest();
        request.setDocId(DOC_ID).setSheetId("8ygigN");
        GetRecordResponse res = smartSheetService.getRecords(request,DA);
        logger.info("{}",res.getRecords());
    }
}
