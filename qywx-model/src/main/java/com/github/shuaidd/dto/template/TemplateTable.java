package com.github.shuaidd.dto.template;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 18:33
 **/
public class TemplateTable {
    private List<TemplateTableChildren> children;

    public List<TemplateTableChildren> getChildren() {
        return children;
    }

    public void setChildren(List<TemplateTableChildren> children) {
        this.children = children;
    }
}
