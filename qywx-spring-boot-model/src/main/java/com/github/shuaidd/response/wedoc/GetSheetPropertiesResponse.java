package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.SheetProperties;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * @author ddshuai
 */
public class GetSheetPropertiesResponse extends AbstractBaseResponse {

    /**
     * 工作表属性
     */
    @JsonProperty("properties")
    private List<SheetProperties> properties;

    public List<SheetProperties> getProperties() {
        return properties;
    }

    public void setProperties(List<SheetProperties> properties) {
        this.properties = properties;
    }
}
