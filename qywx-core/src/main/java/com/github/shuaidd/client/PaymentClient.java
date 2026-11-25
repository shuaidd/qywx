package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.payment.ApplyMchResponse;
import com.github.shuaidd.response.payment.ExternalBillListResponse;
import com.github.shuaidd.response.payment.FundFlowResponse;
import com.github.shuaidd.response.payment.GetApplymentStatusResponse;
import com.github.shuaidd.response.payment.GetMerchantResponse;
import com.github.shuaidd.response.payment.MiniBillResponse;
import com.github.shuaidd.response.payment.MiniOrderResponse;
import com.github.shuaidd.response.payment.MiniRefundDetailResponse;
import com.github.shuaidd.response.payment.MiniRefundResponse;
import com.github.shuaidd.response.payment.MiniSignResponse;
import com.github.shuaidd.response.payment.PaymentInfoResponse;
import com.github.shuaidd.resquest.payment.ApplyMchRequest;
import com.github.shuaidd.resquest.payment.CloseOrderRequest;
import com.github.shuaidd.resquest.payment.CreateOrderRequest;
import com.github.shuaidd.resquest.payment.ExternalBillListRequest;
import com.github.shuaidd.resquest.payment.FundFlowRequest;
import com.github.shuaidd.resquest.payment.GetApplymentStatusRequest;
import com.github.shuaidd.resquest.payment.GetBillRequest;
import com.github.shuaidd.resquest.payment.GetMerchantRequest;
import com.github.shuaidd.resquest.payment.GetOrderRequest;
import com.github.shuaidd.resquest.payment.GetRefundDetailRequest;
import com.github.shuaidd.resquest.payment.GetSignRequest;
import com.github.shuaidd.resquest.payment.PaymentInfoRequest;
import com.github.shuaidd.resquest.payment.RefundRequest;
import com.github.shuaidd.resquest.payment.SetMerchantUseScopeRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 企业支付与小程序对外收款
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public interface PaymentClient extends CommonClient {

    /**
     * 查询已绑定的对外收款商户号信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 商户号详情
     */
    @RequestLine("POST /externalpay/getmerchant?app={app}")
    @Headers(HEAD)
    GetMerchantResponse getMerchant(GetMerchantRequest request, @Param("app") String app);

    /**
     * 设置已绑定对外收款商户号的使用范围
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /externalpay/set_mch_use_scope?app={app}")
    @Headers(HEAD)
    BaseResponse setMerchantUseScope(SetMerchantUseScopeRequest request, @Param("app") String app);

    /**
     * 查询企业对外收款记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 收款记录分页结果
     */
    @RequestLine("POST /externalpay/get_bill_list?app={app}")
    @Headers(HEAD)
    ExternalBillListResponse getBillList(ExternalBillListRequest request, @Param("app") String app);

    /**
     * 查询收款项目关联的商户单号
     *
     * @param request 请求
     * @param app     应用名
     * @return 商户单号列表
     */
    @RequestLine("POST /externalpay/get_payment_info?app={app}")
    @Headers(HEAD)
    PaymentInfoResponse getPaymentInfo(PaymentInfoRequest request, @Param("app") String app);

    /**
     * 查询企业支付资金流水
     *
     * @param request 请求
     * @param app     应用名
     * @return 资金流水分页结果
     */
    @RequestLine("POST /externalpay/get_fund_flow?app={app}")
    @Headers(HEAD)
    FundFlowResponse getFundFlow(FundFlowRequest request, @Param("app") String app);

    /**
     * 提交创建对外收款账户申请单
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单提交结果
     */
    @RequestLine("POST /miniapppay/apply_mch?app={app}")
    @Headers(HEAD)
    ApplyMchResponse applyMch(ApplyMchRequest request, @Param("app") String app);

    /**
     * 查询对外收款账户申请单状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单状态
     */
    @RequestLine("POST /miniapppay/get_applyment_status?app={app}")
    @Headers(HEAD)
    GetApplymentStatusResponse getApplymentStatus(GetApplymentStatusRequest request, @Param("app") String app);

    /**
     * 小程序对外收款下单
     *
     * @param request 请求
     * @param app     应用名
     * @return 预支付会话信息
     */
    @RequestLine("POST /miniapppay/create_order?app={app}")
    @Headers(HEAD)
    MiniOrderResponse createOrder(CreateOrderRequest request, @Param("app") String app);

    /**
     * 查询小程序对外收款订单
     *
     * @param request 请求
     * @param app     应用名
     * @return 订单详情
     */
    @RequestLine("POST /miniapppay/get_order?app={app}")
    @Headers(HEAD)
    MiniOrderResponse getOrder(GetOrderRequest request, @Param("app") String app);

    /**
     * 关闭小程序对外收款订单
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /miniapppay/close_order?app={app}")
    @Headers(HEAD)
    BaseResponse closeOrder(CloseOrderRequest request, @Param("app") String app);

    /**
     * 获取小程序支付签名
     *
     * @param request 请求
     * @param app     应用名
     * @return 支付签名
     */
    @RequestLine("POST /miniapppay/get_sign?app={app}")
    @Headers(HEAD)
    MiniSignResponse getSign(GetSignRequest request, @Param("app") String app);

    /**
     * 申请小程序对外收款退款
     *
     * @param request 请求
     * @param app     应用名
     * @return 退款受理结果
     */
    @RequestLine("POST /miniapppay/refund?app={app}")
    @Headers(HEAD)
    MiniRefundResponse refund(RefundRequest request, @Param("app") String app);

    /**
     * 查询小程序对外收款退款详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 退款详情
     */
    @RequestLine("POST /miniapppay/get_refund_detail?app={app}")
    @Headers(HEAD)
    MiniRefundDetailResponse getRefundDetail(GetRefundDetailRequest request, @Param("app") String app);

    /**
     * 申请小程序对外收款交易账单
     *
     * @param request 请求
     * @param app     应用名
     * @return 账单下载信息
     */
    @RequestLine("POST /miniapppay/get_bill?app={app}")
    @Headers(HEAD)
    MiniBillResponse getBill(GetBillRequest request, @Param("app") String app);
}
