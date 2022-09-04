package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-03 07:27
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class InitUploadFileRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("spaceid")
    private String spaceId;

    @JsonProperty("fatherid")
    private String fatherId;

    @JsonProperty("selected_ticket")
    private String selectedTicket;

    @JsonProperty("file_name")
    private String fileName;

    private Long size;

    @JsonProperty("skip_push_card")
    private Boolean skipPushCard;

    @JsonProperty("block_sha")
    private List<String> blockSha;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Boolean isSkipPushCard() {
        return skipPushCard;
    }

    public void setSkipPushCard(Boolean skipPushCard) {
        this.skipPushCard = skipPushCard;
    }

    public List<String> getBlockSha() {
        return blockSha;
    }

    public void setBlockSha(List<String> blockSha) {
        this.blockSha = blockSha;
    }
}
