package com.github.shuaidd.resquest.safety;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FileOperatorRecordRequest {

    @JsonProperty("start_time")
    private Integer startTime;
    @JsonProperty("end_time")
    private Integer endTime;
    @JsonProperty("userid_list")
    private List<String> useridList;
    @JsonProperty("operation")
    private OperationDTO operation;
    @JsonProperty("cursor")
    private String cursor;
    @JsonProperty("limit")
    private Integer limit;

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public List<String> getUseridList() {
        return useridList;
    }

    public void setUseridList(List<String> useridList) {
        this.useridList = useridList;
    }

    public OperationDTO getOperation() {
        return operation;
    }

    public void setOperation(OperationDTO operation) {
        this.operation = operation;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public static class OperationDTO {
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("source")
        private Integer source;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getSource() {
            return source;
        }

        public void setSource(Integer source) {
            this.source = source;
        }
    }
}
