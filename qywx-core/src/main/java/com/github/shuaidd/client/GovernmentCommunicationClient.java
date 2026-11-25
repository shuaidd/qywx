package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.government.CreateGridResponse;
import com.github.shuaidd.response.government.GridListResponse;
import com.github.shuaidd.response.government.UpdateGridResponse;
import com.github.shuaidd.response.government.UserGridInfoResponse;
import com.github.shuaidd.resquest.government.CreateGridRequest;
import com.github.shuaidd.resquest.government.DeleteGridRequest;
import com.github.shuaidd.resquest.government.GridListRequest;
import com.github.shuaidd.resquest.government.UpdateGridRequest;
import com.github.shuaidd.resquest.government.UserGridInfoRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 政民沟通服务端接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public interface GovernmentCommunicationClient extends CommonClient {

    /**
     * 添加网格
     *
     * @param request 请求体
     * @param app     应用名
     * @return 创建结果
     */
    @RequestLine("POST /report/grid/add?app={app}")
    @Headers(HEAD)
    CreateGridResponse addGrid(CreateGridRequest request, @Param("app") String app);

    /**
     * 编辑网格
     *
     * @param request 请求体
     * @param app     应用名
     * @return 编辑结果
     */
    @RequestLine("POST /report/grid/update?app={app}")
    @Headers(HEAD)
    UpdateGridResponse updateGrid(UpdateGridRequest request, @Param("app") String app);

    /**
     * 删除网格
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /report/grid/delete?app={app}")
    @Headers(HEAD)
    BaseResponse deleteGrid(DeleteGridRequest request, @Param("app") String app);

    /**
     * 获取网格列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return 网格列表
     */
    @RequestLine("POST /report/grid/list?app={app}")
    @Headers(HEAD)
    GridListResponse listGrid(GridListRequest request, @Param("app") String app);

    /**
     * 获取用户负责及参与的网格列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return 用户网格关系
     */
    @RequestLine("POST /report/grid/get_user_grid_info?app={app}")
    @Headers(HEAD)
    UserGridInfoResponse getUserGridInfo(UserGridInfoRequest request, @Param("app") String app);
}
