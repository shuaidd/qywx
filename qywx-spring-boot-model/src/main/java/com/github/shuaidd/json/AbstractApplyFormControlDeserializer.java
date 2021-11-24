package com.github.shuaidd.json;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.shuaidd.dto.oa.formcontrol.*;
import com.github.shuaidd.dto.template.TemplateText;

import java.io.IOException;
import java.util.*;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 15:07
 **/
public abstract class AbstractApplyFormControlDeserializer<T> extends JsonDeserializer<T> {
    protected final static Map<String, Class<? extends ApplyFormControl>> APPLY_FORM_CONTROL_MAP = new HashMap<>();

    static {
        APPLY_FORM_CONTROL_MAP.put("Text", TextFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Textarea", TextFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Attendance", AttendanceFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Contact", ContactFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Date", DateFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("DateRange", DateRangeFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("File", FileFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Formula", FormulaFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Location", LocationFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Money", MoneyFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Number", NumberFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("RelatedApproval", RelatedApprovalFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Selector", SelectorFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Table", TableFormControl.class);
        APPLY_FORM_CONTROL_MAP.put("Vacation", VacationFormControl.class);
    }

    protected List<TemplateText> getTitle(JsonNode titleNode) {
        if (Objects.nonNull(titleNode)) {
            List<TemplateText> title = new ArrayList<>(2);
            ArrayNode arrayNode = (ArrayNode) titleNode;
            for (JsonNode node : arrayNode) {
                String text = node.get("text").textValue();
                String lang = node.get("lang").textValue();
                TemplateText templateText = new TemplateText(text, lang);
                title.add(templateText);
            }
            return title;
        }

        return null;
    }

    protected ApplyFormControl getApplyFormControl(String control, JsonNode valueNode, ObjectMapper mapper) throws IOException {
        if (Objects.nonNull(valueNode)) {
            Class<? extends ApplyFormControl> cls = APPLY_FORM_CONTROL_MAP.get(control);
            if (Objects.isNull(cls)) {
                return null;
            }
            return mapper.readValue(valueNode.toString(), cls);
        }

        return null;
    }
}
