package com.github.shuaidd.resquest.government;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 删除网格请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class DeleteGridRequest {

    /**
     * 需要删除的网格id；根节点可传空
     */
    @JsonProperty("grid_id")
    private String gridId;

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }
}
