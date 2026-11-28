package com.github.shuaidd.resquest.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BatchInvoiceRequest {

    @JsonProperty("item_list")
    private List<ItemListDTO> itemList;

    public List<ItemListDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListDTO> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListDTO {
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
