package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.ProductDTO;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

public class ProductAlbumListResponse extends AbstractBaseResponse {

    @JsonProperty("product_list")
    private List<ProductDTO> productAlbumList;

    public List<ProductDTO> getProductAlbumList() {
        return productAlbumList;
    }

    public void setProductAlbumList(List<ProductDTO> productAlbumList) {
        this.productAlbumList = productAlbumList;
    }
}
