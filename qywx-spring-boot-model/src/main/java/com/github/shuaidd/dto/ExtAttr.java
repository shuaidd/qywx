package com.github.shuaidd.dto;

import java.util.Map;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-04 15:31
 **/
public class ExtAttr {
    private Integer type;
    private String name;
    private Map<String,Map<String,String>> attrs;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Map<String, String>> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Map<String, String>> attrs) {
        this.attrs = attrs;
    }
}
