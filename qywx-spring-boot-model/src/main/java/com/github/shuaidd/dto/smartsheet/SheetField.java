package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SheetField {

    @JsonProperty("field_id")
    private String fieldId;
    @JsonProperty("field_title")
    private String fieldTitle;
    @JsonProperty("field_type")
    private String fieldType;
    @JsonProperty("property_number")
    private NumberFieldProperty propertyNumber;
    @JsonProperty("property_checkbox")
    private CheckboxFieldProperty propertyCheckbox;
    @JsonProperty("property_date_time")
    private DateTimeFieldProperty propertyDateTime;
    @JsonProperty("property_attachment")
    private AttachmentFieldProperty propertyAttachment;
    @JsonProperty("property_user")
    private UserFieldProperty propertyUser;
    @JsonProperty("property_url")
    private UrlFieldProperty propertyUrl;
    @JsonProperty("property_select")
    private SelectFieldProperty propertySelect;
    @JsonProperty("property_created_time")
    private CreatedTimeFieldProperty propertyCreatedTime;
    @JsonProperty("property_modified_time")
    private ModifiedTimeFieldProperty propertyModifiedTime;
    @JsonProperty("property_progress")
    private ProgressFieldProperty propertyProgress;
    @JsonProperty("property_single_select")
    private SingleSelectFieldProperty propertySingleSelect;
    @JsonProperty("property_reference")
    private ReferenceFieldProperty propertyReference;
    @JsonProperty("property_location")
    private LocationFieldProperty propertyLocation;
    @JsonProperty("property_auto_number")
    private AutoNumberFieldProperty propertyAutoNumber;
    @JsonProperty("property_currency")
    private CurrencyFieldProperty propertyCurrency;
    @JsonProperty("property_ww_group")
    private WwGroupFieldProperty propertyWwGroup;
}
