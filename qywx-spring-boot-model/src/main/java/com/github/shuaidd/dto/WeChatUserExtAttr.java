package com.github.shuaidd.dto;

import java.util.List;

/**
 * 企业微信用户扩展信息
 *
 * @author ddshuai
 * date 2019-04-04 15:29
 **/
public class WeChatUserExtAttr {

    private List<ExtAttr> attrs;

    public List<ExtAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<ExtAttr> attrs) {
        this.attrs = attrs;
    }
}
