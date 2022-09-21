package com.github.shuaidd.service;

import com.github.shuaidd.response.invoice.BatchInvoiceResponse;
import com.github.shuaidd.response.invoice.GetInvoiceResponse;
import com.github.shuaidd.resquest.invoice.BatchInvoiceRequest;
import com.github.shuaidd.resquest.invoice.BatchUpdateInvoiceRequest;
import com.github.shuaidd.resquest.invoice.GetInvoiceRequest;
import com.github.shuaidd.resquest.invoice.UpdateInvoiceRequest;
import org.springframework.stereotype.Service;

/**
 * 电子发票
 */
@Service
public class ElectronicInvoiceService extends AbstractBaseService {

    /**
     * 查询电子发票
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 发票信息
     */
    public GetInvoiceResponse getInvoice(GetInvoiceRequest request, String applicationName) {
        return electronicInvoiceClient.getInvoice(request, applicationName);
    }

    /**
     * 更新发票状态
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void updateInvoice(UpdateInvoiceRequest request, String applicationName) {
        electronicInvoiceClient.updateInvoice(request, applicationName);
    }

    /**
     * 批量更新发票状态
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void batchUpdateInvoice(BatchUpdateInvoiceRequest request, String applicationName) {
        electronicInvoiceClient.batchUpdateInvoice(request, applicationName);
    }

    /**
     * 批量查询电子发票
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 电子发票详情
     */
    public BatchInvoiceResponse getInvoices(BatchInvoiceRequest request, String applicationName) {
        return electronicInvoiceClient.getInvoices(request, applicationName);
    }
}
