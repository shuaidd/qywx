package com.github.shuaidd.resquest.government;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取网格列表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GridListRequest {

    /**
     * 查询起点网格id；不填表示返回根节点及其子节点
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
