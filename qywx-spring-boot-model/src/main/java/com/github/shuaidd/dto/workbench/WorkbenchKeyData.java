package com.github.shuaidd.dto.workbench;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工作台关键数据型展示数据
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class WorkbenchKeyData {

    /**
     * 关键数据列表，不超过4个
     */
    private List<WorkbenchKeyDataItem> items;
}
