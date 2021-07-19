package com.github.shuaidd.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.shuaidd.dto.oa.ApplyDataContent;
import com.github.shuaidd.dto.oa.formcontrol.TableChildrenDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-19 11:26
 **/
public class TableFormControlDeserializer extends AbstractApplyFormControlDeserializer<List<TableChildrenDetail>> {



    @Override
    public List<TableChildrenDetail> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        List<TableChildrenDetail> tableChildrenDetails = new ArrayList<>();
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ArrayNode root = mapper.readTree(jp);
        for (JsonNode jsonNode : root) {
            TableChildrenDetail tableChildrenDetail = new TableChildrenDetail();

            String control = jsonNode.get("control").textValue();
            tableChildrenDetail.setControl(control);

            String id = jsonNode.get("id").textValue();
            tableChildrenDetail.setId(id);

            JsonNode titleNode = jsonNode.get("title");
            tableChildrenDetail.setTitle(getTitle(titleNode));

            JsonNode valueNode = jsonNode.get("value");
            tableChildrenDetail.setValue(getApplyFormControl(control,valueNode,mapper));


            tableChildrenDetails.add(tableChildrenDetail);
        }
        return tableChildrenDetails;
    }
}
