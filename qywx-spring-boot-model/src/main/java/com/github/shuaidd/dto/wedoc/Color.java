package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 */
public class Color {

    /**
     * 红色，取值范围：[0,255]
     */
    @JsonProperty("red")
    private Integer red;
    /**
     * 绿色，取值范围：[0,255]
     */
    @JsonProperty("green")
    private Integer green;
    /**
     * 蓝色，取值范围：[0,255]
     */
    @JsonProperty("blue")
    private Integer blue;
    /**
     * alpha通道，取值范围：[0,255]，默认值为255完全不透明
     */
    @JsonProperty("alpha")
    private Integer alpha;

    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public Integer getGreen() {
        return green;
    }

    public void setGreen(Integer green) {
        this.green = green;
    }

    public Integer getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    public Integer getAlpha() {
        return alpha;
    }

    public void setAlpha(Integer alpha) {
        this.alpha = alpha;
    }
}
