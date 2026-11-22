package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 设置家校通讯录同步模式的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SetArchSyncModeRequest {

    /**
     * 同步模式：1-禁止标签同步家校通讯录；2-禁止家校通讯录同步标签；3-禁止相互同步
     */
    @JsonProperty("arch_sync_mode")
    private Integer archSyncMode;

    public Integer getArchSyncMode() {
        return archSyncMode;
    }

    public void setArchSyncMode(Integer archSyncMode) {
        this.archSyncMode = archSyncMode;
    }
}
