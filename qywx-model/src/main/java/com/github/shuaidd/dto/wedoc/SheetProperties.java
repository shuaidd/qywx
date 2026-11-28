package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SheetProperties {
    /**
     * sheetId
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * title
     */
    @JsonProperty("title")
    private String title;
    /**
     * rowCount
     */
    @JsonProperty("row_count")
    private Integer rowCount;
    /**
     * columnCount
     */
    @JsonProperty("column_count")
    private Integer columnCount;

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }
}
