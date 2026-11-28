package com.github.shuaidd.dto.workbench;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工作台列表型展示数据
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class WorkbenchList {

    /**
     * 列表数据，不超过3个
     */
    private List<WorkbenchListItem> items;
}
