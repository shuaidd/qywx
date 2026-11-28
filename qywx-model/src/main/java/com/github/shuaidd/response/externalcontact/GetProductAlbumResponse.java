package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.ProductDTO;
import com.github.shuaidd.response.AbstractBaseResponse;

public class GetProductAlbumResponse extends AbstractBaseResponse {

    /**
     * product
     */
    @JsonProperty("product")
    private ProductDTO product;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

}
