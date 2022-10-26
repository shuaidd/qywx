package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;

import java.util.Date;
import java.util.List;

public class ProductDTO {
        /**
         * productId
         */
        @JsonProperty("product_id")
        private String productId;
        /**
         * description
         */
        @JsonProperty("description")
        private String description;
        /**
         * price
         */
        @JsonProperty("price")
        private Integer price;
        /**
         * createTime
         */
        @JsonProperty("create_time")
        @JsonDeserialize(using = Long2DateDeserializer.class)
        private Date createTime;
        /**
         * productSn
         */
        @JsonProperty("product_sn")
        private String productSn;
        /**
         * attachments
         */
        @JsonProperty("attachments")
        private List<AttachmentsDTO> attachments;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public String getProductSn() {
            return productSn;
        }

        public void setProductSn(String productSn) {
            this.productSn = productSn;
        }

        public List<AttachmentsDTO> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentsDTO> attachments) {
            this.attachments = attachments;
        }

        public static class AttachmentsDTO {
            /**
             * type
             */
            @JsonProperty("type")
            private String type;
            /**
             * image
             */
            @JsonProperty("image")
            private ImageDTO image;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public ImageDTO getImage() {
                return image;
            }

            public void setImage(ImageDTO image) {
                this.image = image;
            }

            public static class ImageDTO {
                /**
                 * mediaId
                 */
                @JsonProperty("media_id")
                private String mediaId;

                public String getMediaId() {
                    return mediaId;
                }

                public void setMediaId(String mediaId) {
                    this.mediaId = mediaId;
                }
            }
        }
    }