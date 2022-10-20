package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddProductAlbumRequest {

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("product_sn")
    private String productSn;

    @JsonProperty("attachments")
    private List<AttachmentsDTO> attachments;

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
        @JsonProperty("type")
        private String type;
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
