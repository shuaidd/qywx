package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.enums.ButtonType;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-08 16:15
 **/
public class ApplicationButton {

    private ButtonType type;
    private String name;
    private String key;
    private String url;
    @JsonProperty("sub_button")
    private List<ApplicationButton> subButton;

    public ButtonType getType() {
        return type;
    }

    public void setType(ButtonType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ApplicationButton> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<ApplicationButton> subButton) {
        this.subButton = subButton;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApplicationButton.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("name='" + name + "'")
                .add("key='" + key + "'")
                .add("url='" + url + "'")
                .add("subButton=" + subButton)
                .toString();
    }
}
