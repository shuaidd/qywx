package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.CalendarViewProperty;
import com.github.shuaidd.dto.smartsheet.GanttViewProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddViewRequest {

    @JsonProperty("docid")
    private String docId;

    @JsonProperty("sheet_id")
    private String sheetId;

    @JsonProperty("view_title")
    private String viewTitle;

    @JsonProperty("view_type")
    private String viewType;

    @JsonProperty("property_gantt")
    private GanttViewProperty propertyGantt;

    @JsonProperty("property_calendar")
    private CalendarViewProperty propertyCalendar;
}
