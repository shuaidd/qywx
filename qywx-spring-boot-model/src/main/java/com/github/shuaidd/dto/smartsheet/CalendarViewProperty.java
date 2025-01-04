package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CalendarViewProperty {
    @JsonProperty("start_date_field_id")
    private String startDateFieldId;

    @JsonProperty("end_date_field_id")
    private String endDateFieldId;
}
