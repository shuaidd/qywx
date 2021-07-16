package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.ExtAttr;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 16:37
 **/
public class ExternalProfile {
    @JsonProperty("external_attr")
    private List<ExtAttr> externalAttr;

    public List<ExtAttr> getExternalAttr() {
        return externalAttr;
    }

    public void setExternalAttr(List<ExtAttr> externalAttr) {
        this.externalAttr = externalAttr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("externalAttr", externalAttr)
                .toString();
    }
}
