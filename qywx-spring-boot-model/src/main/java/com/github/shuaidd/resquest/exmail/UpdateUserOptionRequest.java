package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 更改用户邮件客户端功能属性请求。
 */
public class UpdateUserOptionRequest {

    /**
     * 用户 userid
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 待更改的功能属性列表
     */
    @JsonProperty("option")
    private OptionDTO option;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

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
         * 功能属性类型：1强制安全登录，2 IMAP/SMTP，3 POP/SMTP，4安全登录
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
