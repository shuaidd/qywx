package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.payment.*;
import com.github.shuaidd.resquest.payment.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 企业支付与小程序对外收款
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "PaymentClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface PaymentClient extends CommonClient {

    /**
     * 查询已绑定的对外收款商户号信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 商户号详情
     */
    @PostMapping(value = "/externalpay/getmerchant", headers = HEAD)
    GetMerchantResponse getMerchant(GetMerchantRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置已绑定对外收款商户号的使用范围
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "/externalpay/set_mch_use_scope", headers = HEAD)
    BaseResponse setMerchantUseScope(SetMerchantUseScopeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询企业对外收款记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 收款记录分页结果
     */
    @PostMapping(value = "/externalpay/get_bill_list", headers = HEAD)
    ExternalBillListResponse getBillList(ExternalBillListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询收款项目关联的商户单号
     *
     * @param request 请求
     * @param app     应用名
     * @return 商户单号列表
     */
    @PostMapping(value = "/externalpay/get_payment_info", headers = HEAD)
    PaymentInfoResponse getPaymentInfo(PaymentInfoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询企业支付资金流水
     *
     * @param request 请求
     * @param app     应用名
     * @return 资金流水分页结果
     */
    @PostMapping(value = "/externalpay/get_fund_flow", headers = HEAD)
    FundFlowResponse getFundFlow(FundFlowRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 提交创建对外收款账户申请单
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单提交结果
     */
    @PostMapping(value = "/miniapppay/apply_mch", headers = HEAD)
    ApplyMchResponse applyMch(ApplyMchRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询对外收款账户申请单状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单状态
     */
    @PostMapping(value = "/miniapppay/get_applyment_status", headers = HEAD)
    GetApplymentStatusResponse getApplymentStatus(GetApplymentStatusRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 小程序对外收款下单
     *
     * @param request 请求
     * @param app     应用名
     * @return 预支付会话信息
     */
    @PostMapping(value = "/miniapppay/create_order", headers = HEAD)
    MiniOrderResponse createOrder(CreateOrderRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询小程序对外收款订单
     *
     * @param request 请求
     * @param app     应用名
     * @return 订单详情
     */
    @PostMapping(value = "/miniapppay/get_order", headers = HEAD)
    MiniOrderResponse getOrder(GetOrderRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 关闭小程序对外收款订单
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "/miniapppay/close_order", headers = HEAD)
    BaseResponse closeOrder(CloseOrderRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取小程序支付签名
     *
     * @param request 请求
     * @param app     应用名
     * @return 支付签名
     */
    @PostMapping(value = "/miniapppay/get_sign", headers = HEAD)
    MiniSignResponse getSign(GetSignRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 申请小程序对外收款退款
     *
     * @param request 请求
     * @param app     应用名
     * @return 退款受理结果
     */
    @PostMapping(value = "/miniapppay/refund", headers = HEAD)
    MiniRefundResponse refund(RefundRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询小程序对外收款退款详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 退款详情
     */
    @PostMapping(value = "/miniapppay/get_refund_detail", headers = HEAD)
    MiniRefundDetailResponse getRefundDetail(GetRefundDetailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 申请小程序对外收款交易账单
     *
     * @param request 请求
     * @param app     应用名
     * @return 账单下载信息
     */
    @PostMapping(value = "/miniapppay/get_bill", headers = HEAD)
    MiniBillResponse getBill(GetBillRequest request, @RequestParam(HEAD_KEY) String app);
}
