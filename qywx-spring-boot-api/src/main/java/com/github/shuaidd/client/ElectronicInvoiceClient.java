package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.invoice.GetInvoiceResponse;
import com.github.shuaidd.resquest.invoice.GetInvoiceRequest;
import com.github.shuaidd.resquest.invoice.UpdateInvoiceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 电子发票
 */
@FeignClient(name = "ElectronicInvoiceClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface ElectronicInvoiceClient extends CommonClient {

    /**
     * 查询电子发票
     *
     * @param request 请求
     * @param app     应用
     * @return 收据详情
     */
    @PostMapping(value = "/card/invoice/reimburse/getinvoiceinfo", headers = HEAD)
    GetInvoiceResponse getInvoice(GetInvoiceRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新发票状态
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "/card/invoice/reimburse/updateinvoicestatus", headers = HEAD)
    BaseResponse updateInvoice(UpdateInvoiceRequest request, @RequestParam(HEAD_KEY) String app);

}
