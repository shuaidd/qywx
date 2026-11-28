package com.github.shuaidd.response.government;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 用户负责及参与的网格列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UserGridInfoResponse extends AbstractBaseResponse {

    /**
     * 作为负责人管理的网格列表
     */
    @JsonProperty("manage_grids")
    private List<UserGrid> manageGrids;

    /**
     * 作为成员参与的网格列表
     */
    @JsonProperty("joined_grids")
    private List<UserGrid> joinedGrids;

    public List<UserGrid> getManageGrids() {
        return manageGrids;
    }

    public void setManageGrids(List<UserGrid> manageGrids) {
        this.manageGrids = manageGrids;
    }

    public List<UserGrid> getJoinedGrids() {
        return joinedGrids;
    }

    public void setJoinedGrids(List<UserGrid> joinedGrids) {
        this.joinedGrids = joinedGrids;
    }
}
