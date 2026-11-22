package com.github.shuaidd.response.government;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 网格列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GridListResponse extends AbstractBaseResponse {

    /**
     * 网格信息列表
     */
    @JsonProperty("grid_list")
    private List<GridInfo> gridList;

    public List<GridInfo> getGridList() {
        return gridList;
    }

    public void setGridList(List<GridInfo> gridList) {
        this.gridList = gridList;
    }
}
