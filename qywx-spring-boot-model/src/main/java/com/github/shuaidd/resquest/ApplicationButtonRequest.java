package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.ApplicationButton;


import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 16:17
 **/
public class ApplicationButtonRequest {

    @JsonProperty("button")
    private List<ApplicationButton> buttonList;

    public List<ApplicationButton> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<ApplicationButton> buttonList) {
        this.buttonList = buttonList;
    }
}
