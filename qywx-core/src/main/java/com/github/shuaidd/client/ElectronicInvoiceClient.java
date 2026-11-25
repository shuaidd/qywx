package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.invoice.BatchInvoiceResponse;
import com.github.shuaidd.response.invoice.GetInvoiceResponse;
import com.github.shuaidd.resquest.invoice.BatchInvoiceRequest;
import com.github.shuaidd.resquest.invoice.BatchUpdateInvoiceRequest;
import com.github.shuaidd.resquest.invoice.GetInvoiceRequest;
import com.github.shuaidd.resquest.invoice.UpdateInvoiceRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 电子发票
 */
public interface ElectronicInvoiceClient extends CommonClient {

    /**
     * 查询电子发票
     *
     * @param request 请求
     * @param app     应用
     * @return 收据详情
     */
    @RequestLine("POST /card/invoice/reimburse/getinvoiceinfo?app={app}")
    @Headers(HEAD)
    GetInvoiceResponse getInvoice(GetInvoiceRequest request, @Param("app") String app);

    /**
     * 更新发票状态
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /card/invoice/reimburse/updateinvoicestatus?app={app}")
    @Headers(HEAD)
    BaseResponse updateInvoice(UpdateInvoiceRequest request, @Param("app") String app);

    /**
     * 批量更新发票状态
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /card/invoice/reimburse/updatestatusbatch?app={app}")
    @Headers(HEAD)
    BaseResponse batchUpdateInvoice(BatchUpdateInvoiceRequest request, @Param("app") String app);

    /**
     * 批量查询电子发票
     *
     * @param request 请求
     * @param app     应用
     * @return 电子发票结果
     */
    @RequestLine("POST /card/invoice/reimburse/getinvoiceinfobatch?app={app}")
    @Headers(HEAD)
    BatchInvoiceResponse getInvoices(BatchInvoiceRequest request, @Param("app") String app);
}
