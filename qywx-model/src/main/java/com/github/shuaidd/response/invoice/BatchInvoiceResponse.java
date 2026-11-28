package com.github.shuaidd.response.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

public class BatchInvoiceResponse extends AbstractBaseResponse {

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

        @JsonProperty("openid")
        private String openId;

        @JsonProperty("type")
        private String type;

        @JsonProperty("payee")
        private String payee;

        @JsonProperty("detail")
        private String detail;

        @JsonProperty("user_info")
        private UserInfoDTO userInfo;

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPayee() {
            return payee;
        }

        public void setPayee(String payee) {
            this.payee = payee;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public UserInfoDTO getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoDTO userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoDTO {

            @JsonProperty("fee")
            private Integer fee;

            @JsonProperty("title")
            private String title;

            @JsonProperty("billing_time")
            private Integer billingTime;

            @JsonProperty("billing_no")
            private String billingNo;

            @JsonProperty("billing_code")
            private String billingCode;

            @JsonProperty("info")
            private List<InfoDTO> info;

            @JsonProperty("fee_without_tax")
            private Integer feeWithoutTax;

            @JsonProperty("tax")
            private Integer tax;

            @JsonProperty("detail")
            private String detail;

            @JsonProperty("pdf_url")
            private String pdfUrl;

            @JsonProperty("reimburse_status")
            private String reimburseStatus;

            @JsonProperty("order_id")
            private String orderId;

            @JsonProperty("check_code")
            private String checkCode;

            @JsonProperty("buyer_number")
            private String buyerNumber;

            public Integer getFee() {
                return fee;
            }

            public void setFee(Integer fee) {
                this.fee = fee;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Integer getBillingTime() {
                return billingTime;
            }

            public void setBillingTime(Integer billingTime) {
                this.billingTime = billingTime;
            }

            public String getBillingNo() {
                return billingNo;
            }

            public void setBillingNo(String billingNo) {
                this.billingNo = billingNo;
            }

            public String getBillingCode() {
                return billingCode;
            }

            public void setBillingCode(String billingCode) {
                this.billingCode = billingCode;
            }

            public List<InfoDTO> getInfo() {
                return info;
            }

            public void setInfo(List<InfoDTO> info) {
                this.info = info;
            }

            public Integer getFeeWithoutTax() {
                return feeWithoutTax;
            }

            public void setFeeWithoutTax(Integer feeWithoutTax) {
                this.feeWithoutTax = feeWithoutTax;
            }

            public Integer getTax() {
                return tax;
            }

            public void setTax(Integer tax) {
                this.tax = tax;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getPdfUrl() {
                return pdfUrl;
            }

            public void setPdfUrl(String pdfUrl) {
                this.pdfUrl = pdfUrl;
            }

            public String getReimburseStatus() {
                return reimburseStatus;
            }

            public void setReimburseStatus(String reimburseStatus) {
                this.reimburseStatus = reimburseStatus;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getCheckCode() {
                return checkCode;
            }

            public void setCheckCode(String checkCode) {
                this.checkCode = checkCode;
            }

            public String getBuyerNumber() {
                return buyerNumber;
            }

            public void setBuyerNumber(String buyerNumber) {
                this.buyerNumber = buyerNumber;
            }

            public static class InfoDTO {
                @JsonProperty("name")
                private String name;
                @JsonProperty("num")
                private Integer num;
                @JsonProperty("unit")
                private String unit;
                @JsonProperty("fee")
                private Integer fee;
                @JsonProperty("price")
                private Integer price;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Integer getNum() {
                    return num;
                }

                public void setNum(Integer num) {
                    this.num = num;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public Integer getFee() {
                    return fee;
                }

                public void setFee(Integer fee) {
                    this.fee = fee;
                }

                public Integer getPrice() {
                    return price;
                }

                public void setPrice(Integer price) {
                    this.price = price;
                }
            }
        }
    }
}
