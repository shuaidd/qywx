package com.github.shuaidd.resquest.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetInvoiceRequest {

    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("encrypt_code")
    private String encryptCode;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getEncryptCode() {
        return encryptCode;
    }

    public void setEncryptCode(String encryptCode) {
        this.encryptCode = encryptCode;
    }
}
