package com.github.shuaidd.resquest.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BatchUpdateInvoiceRequest {

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("reimburse_status")
    private String reimburseStatus;

    @JsonProperty("invoice_list")
    private List<InvoiceListDTO> invoiceList;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getReimburseStatus() {
        return reimburseStatus;
    }

    public void setReimburseStatus(String reimburseStatus) {
        this.reimburseStatus = reimburseStatus;
    }

    public List<InvoiceListDTO> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceListDTO> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public static class InvoiceListDTO {
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
}
