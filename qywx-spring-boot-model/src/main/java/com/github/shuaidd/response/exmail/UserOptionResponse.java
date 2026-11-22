package com.github.shuaidd.response.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 用户邮件客户端功能属性响应。
 */
public class UserOptionResponse extends AbstractBaseResponse {

    /**
     * 功能属性容器
     */
    @JsonProperty("option")
    private OptionDTO option;

    public OptionDTO getOption() {
        return option;
    }

    public void setOption(OptionDTO option) {
        this.option = option;
    }

    /**
     * 用户功能属性容器
     */
    public static class OptionDTO {

        /**
         * 功能属性列表
         */
        @JsonProperty("list")
        private List<OptionItemDTO> list;

        public List<OptionItemDTO> getList() {
            return list;
        }

        public void setList(List<OptionItemDTO> list) {
            this.list = list;
        }
    }

    /**
     * 单个用户功能属性
     */
    public static class OptionItemDTO {

        /**
         * 功能属性类型：1强制启用安全登录，2 IMAP/SMTP，3 POP/SMTP，4安全登录
         */
        @JsonProperty("type")
        private Integer type;

        /**
         * 属性值：1启用，0关闭
         */
        @JsonProperty("value")
        private String value;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
