package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-02 17:31
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadFileRequest extends CommonSpaceRequest {

    @JsonProperty("selected_ticket")
    private String selectedTicket;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_base64_content")
    private String fileBase64Content;

    public String getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(String selectedTicket) {
        this.selectedTicket = selectedTicket;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileBase64Content() {
        return fileBase64Content;
    }

    public void setFileBase64Content(String fileBase64Content) {
        this.fileBase64Content = fileBase64Content;
    }
}
