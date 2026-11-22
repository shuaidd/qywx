package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
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
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 政民沟通服务端接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "GovernmentCommunicationClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface GovernmentCommunicationClient extends CommonClient {

    /**
     * 添加网格
     *
     * @param request 请求体
     * @param app     应用名
     * @return 创建结果
     */
    @PostMapping(value = "report/grid/add", headers = HEAD)
    CreateGridResponse addGrid(CreateGridRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑网格
     *
     * @param request 请求体
     * @param app     应用名
     * @return 编辑结果
     */
    @PostMapping(value = "report/grid/update", headers = HEAD)
    UpdateGridResponse updateGrid(UpdateGridRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除网格
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "report/grid/delete", headers = HEAD)
    BaseResponse deleteGrid(DeleteGridRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取网格列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return 网格列表
     */
    @PostMapping(value = "report/grid/list", headers = HEAD)
    GridListResponse listGrid(GridListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取用户负责及参与的网格列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return 用户网格关系
     */
    @PostMapping(value = "report/grid/get_user_grid_info", headers = HEAD)
    UserGridInfoResponse getUserGridInfo(UserGridInfoRequest request, @RequestParam(HEAD_KEY) String app);
}
