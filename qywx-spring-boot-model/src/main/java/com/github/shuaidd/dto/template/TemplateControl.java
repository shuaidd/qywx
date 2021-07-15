package com.github.shuaidd.dto.template;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 15:40
 **/
public class TemplateControl {
    private TemplateProperty property;
    private TemplateConfig config;

    public TemplateProperty getProperty() {
        return property;
    }

    public void setProperty(TemplateProperty property) {
        this.property = property;
    }

    public TemplateConfig getConfig() {
        return config;
    }

    public void setConfig(TemplateConfig config) {
        this.config = config;
    }
}
