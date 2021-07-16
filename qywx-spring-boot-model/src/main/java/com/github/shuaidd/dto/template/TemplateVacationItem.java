package com.github.shuaidd.dto.template;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 18:24
 **/
public class TemplateVacationItem {
    private String id;
    private List<TemplateText> name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TemplateText> getName() {
        return name;
    }

    public void setName(List<TemplateText> name) {
        this.name = name;
    }
}
