package com.github.shuaidd.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.shuaidd.dto.oa.ApplyDataContent;
import com.github.shuaidd.dto.oa.formcontrol.*;
import com.github.shuaidd.dto.template.TemplateText;

import java.io.IOException;
import java.util.*;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-19 11:26
 **/
public class ApplyFormControlDeserializer extends AbstractApplyFormControlDeserializer<List<ApplyDataContent>> {



    @Override
    public List<ApplyDataContent> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        List<ApplyDataContent> applyDataContents = new ArrayList<>();
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ArrayNode root = mapper.readTree(jp);
        for (JsonNode jsonNode : root) {
            ApplyDataContent applyDataContent = new ApplyDataContent();

            String control = jsonNode.get("control").textValue();
            applyDataContent.setControl(control);

            String id = jsonNode.get("id").textValue();
            applyDataContent.setId(id);

            JsonNode titleNode = jsonNode.get("title");
            applyDataContent.setTitle(getTitle(titleNode));

            JsonNode valueNode = jsonNode.get("value");
            applyDataContent.setValue(getApplyFormControl(control,valueNode,mapper));


            applyDataContents.add(applyDataContent);
        }
        return applyDataContents;
    }
}
