package com.github.shuaidd.response.government;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用户关联的网格摘要
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UserGrid {

    /**
     * 网格id
     */
    @JsonProperty("grid_id")
    private String gridId;

    /**
     * 网格名称
     */
    @JsonProperty("grid_name")
    private String gridName;

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }
}
