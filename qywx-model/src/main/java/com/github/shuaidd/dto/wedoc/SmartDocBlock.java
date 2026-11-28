package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

/**
 * 智能文档内容块信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocBlock {

    /**
     * 内容块 ID；添加时可为空，更新时必填
     */
    @JsonProperty("id")
    private String id;

    /**
     * 内容块类型，取值见官方 BlockType 枚举
     */
    @JsonProperty("type")
    private String type;

    /**
     * 内容块文本内容
     */
    @JsonProperty("title")
    private String title;

    /**
     * 父内容块 ID
     */
    @JsonProperty("parent_id")
    private String parentId;

    /**
     * 排序位置，表示排在哪个内容块之后，为空表示插入到最前
     */
    @JsonProperty("after_id")
    private String afterId;

    /**
     * 子内容块 ID 列表
     */
    @JsonProperty("children")
    private List<String> children;

    /**
     * 内容块属性，结构随 BlockType 变化
     */
    @JsonProperty("props")
    private Map<String, Object> props;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAfterId() {
        return afterId;
    }

    public void setAfterId(String afterId) {
        this.afterId = afterId;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("type", type)
                .append("title", title)
                .append("parentId", parentId)
                .append("afterId", afterId)
                .append("children", children)
                .append("props", props)
                .toString();
    }
}