package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 */
public class TextFormat {

    /**
     * 字体名称，表格支持的字体类型: 字体列表
     */
    @JsonProperty("font")
    private String font;
    /**
     * 字体大小，最大72
     */
    @JsonProperty("font_size")
    private Integer fontSize;
    /**
     * 字体加粗
     */
    @JsonProperty("bold")
    private Boolean bold;
    /**
     * 斜体
     */
    @JsonProperty("italic")
    private Boolean italic;
    /**
     * 字体删除线
     */
    @JsonProperty("strikethrough")
    private Boolean strikethrough;
    /**
     * 下划线
     */
    @JsonProperty("underline")
    private Boolean underline;

    /**
     * 字体颜色
     */
    @JsonProperty("color")
    private Color color;

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean getItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public Boolean getStrikethrough() {
        return strikethrough;
    }

    public void setStrikethrough(Boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    public Boolean getUnderline() {
        return underline;
    }

    public void setUnderline(Boolean underline) {
        this.underline = underline;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
