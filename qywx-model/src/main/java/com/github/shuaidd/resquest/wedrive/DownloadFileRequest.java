package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-02 17:49
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class DownloadFileRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("fileid")
    private String fileId;

    @JsonProperty("selected_ticket")
    private String selectedTicket;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(String selectedTicket) {
        this.selectedTicket = selectedTicket;
    }
}
