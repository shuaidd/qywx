package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.client.PaymentClient;
import com.github.shuaidd.client.PaymentUploadClient;
import com.github.shuaidd.dto.payment.ApplyMchAccountInfo;
import com.github.shuaidd.dto.payment.ApplyMchBusinessLicenseInfo;
import com.github.shuaidd.dto.payment.ApplyMchContactInfo;
import com.github.shuaidd.dto.payment.ApplyMchIdCardInfo;
import com.github.shuaidd.dto.payment.ApplyMchSalesSceneInfo;
import com.github.shuaidd.dto.payment.OrderAmount;
import com.github.shuaidd.dto.payment.OrderPayer;
import com.github.shuaidd.dto.payment.RefundAmount;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.file.QywxFile;
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
import com.github.shuaidd.response.payment.UploadPaymentImageResponse;
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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 企业支付与小程序对外收款服务。资金相关参数在调用前做基础一致性校验，路由、签名和证书由企业微信平台处理。
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class PaymentService extends AbstractBaseService {

    private static final long ONE_DAY_SECONDS = 60L * 60L * 24L;
    private static final long MAX_UPLOAD_SIZE = 2L * 1024L * 1024L;
    private static final Pattern BILL_DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    private final PaymentClient paymentClient;
    private final PaymentUploadClient paymentUploadClient;

    public PaymentService(QywxContext context, PaymentClient paymentClient, PaymentUploadClient paymentUploadClient) {
        super(context);
        this.paymentClient = paymentClient;
        this.paymentUploadClient = paymentUploadClient;
    }

    /**
     * 查询对外收款商户号详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 商户号详情
     */
    public GetMerchantResponse getMerchant(GetMerchantRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getMchId())) {
            throw new ParamCheckException("mch_id 不能为空，请检查！！！");
        }
        if (request.getMchId().length() > 32) {
            throw new ParamCheckException("mch_id 长度不能超过32字节，请检查！！！");
        }
        return paymentClient.getMerchant(request, app);
    }

    /**
     * 设置对外收款商户号使用范围
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    public BaseResponse setMerchantUseScope(SetMerchantUseScopeRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getMchId())) {
            throw new ParamCheckException("mch_id 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getAllowUseScope())) {
            throw new ParamCheckException("allow_use_scope 不能为空，请检查！！！");
        }
        boolean hasScope = CollectionUtils.isNotEmpty(request.getAllowUseScope().getUser())
                || CollectionUtils.isNotEmpty(request.getAllowUseScope().getPartyId())
                || CollectionUtils.isNotEmpty(request.getAllowUseScope().getTagId());
        if (!hasScope) {
            throw new ParamCheckException("使用范围至少配置成员、部门或标签之一，请检查！！！");
        }
        return paymentClient.setMerchantUseScope(request, app);
    }

    /**
     * 查询对外收款记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 收款记录分页结果
     */
    public ExternalBillListResponse getBillList(ExternalBillListRequest request, String app) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("查询对外收款记录请求不能为空");
        }
        checkRequiredTimeRange(request.getBeginTime(), request.getEndTime(), "收款记录");
        checkPageLimit(request.getLimit(), 1000, "收款记录");
        checkMonthRange(request.getBeginTime(), request.getEndTime(), "收款记录");
        return paymentClient.getBillList(request, app);
    }

    /**
     * 查询收款项目的商户单号
     *
     * @param request 请求
     * @param app     应用名
     * @return 商户单号列表
     */
    public PaymentInfoResponse getPaymentInfo(PaymentInfoRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getPaymentId())) {
            throw new ParamCheckException("payment_id 不能为空，请检查！！！");
        }
        return paymentClient.getPaymentInfo(request, app);
    }

    /**
     * 查询资金流水
     *
     * @param request 请求
     * @param app     应用名
     * @return 资金流水分页结果
     */
    public FundFlowResponse getFundFlow(FundFlowRequest request, String app) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("查询资金流水请求不能为空");
        }
        checkRequiredTimeRange(request.getBeginTime(), request.getEndTime(), "资金流水");
        checkPageLimit(request.getLimit(), 200, "资金流水");
        if (request.getEndTime() - request.getBeginTime() > 31 * ONE_DAY_SECONDS) {
            throw new ParamCheckException("资金流水起止时间间隔不能超过31天，请检查！！！");
        }
        return paymentClient.getFundFlow(request, app);
    }

    /**
     * 提交创建对外收款账户申请单
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单提交结果
     */
    public ApplyMchResponse applyMch(ApplyMchRequest request, String app) {
        checkApplyMch(request);
        return paymentClient.applyMch(request, app);
    }

    /**
     * 查询对外收款账户申请单状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 申请单状态
     */
    public GetApplymentStatusResponse getApplymentStatus(GetApplymentStatusRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getOutRequestNo())) {
            throw new ParamCheckException("out_request_no 不能为空，请检查！！！");
        }
        if (request.getOutRequestNo().length() > 32) {
            throw new ParamCheckException("out_request_no 长度不能超过32个字符，请检查！！！");
        }
        return paymentClient.getApplymentStatus(request, app);
    }

    /**
     * 上传创建对外收款账户申请单所需的图片
     *
     * @param image 图片文件
     * @param app   应用名
     * @return 微信支付图片 ID
     */
    public UploadPaymentImageResponse uploadImage(QywxFile image, String app) {
        if (Objects.isNull(image) || image.getContent().length == 0) {
            throw new ParamCheckException("上传图片不能为空，请检查！！！");
        }
        if (image.getContent().length < 5L || image.getContent().length > MAX_UPLOAD_SIZE) {
            throw new ParamCheckException("上传图片大小必须在5字节到2MB之间，请检查！！！");
        }
        String name = StringUtils.defaultString(image.getFilename()).toLowerCase(Locale.ROOT);
        boolean validType = name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".bmp");
        if (!validType) {
            throw new ParamCheckException("上传图片仅支持JPG/JPEG、PNG、BMP格式，请检查！！！");
        }
        return paymentUploadClient.uploadImage(image, app);
    }

    /**
     * 小程序对外收款下单
     *
     * @param request 请求
     * @param app     应用名
     * @return 预支付会话信息
     */
    public MiniOrderResponse createOrder(CreateOrderRequest request, String app) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("小程序下单请求不能为空");
        }
        if (StringUtils.isEmpty(request.getAppId())) {
            throw new ParamCheckException("appid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getMchId())) {
            throw new ParamCheckException("mchid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getOutTradeNo())) {
            throw new ParamCheckException("out_trade_no 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getDescription())) {
            throw new ParamCheckException("description 不能为空，请检查！！！");
        }
        OrderAmount amount = request.getAmount();
        if (Objects.isNull(amount) || Objects.isNull(amount.getTotal()) || amount.getTotal() <= 0) {
            throw new ParamCheckException("amount.total 必须为大于0的订单总金额，请检查！！！");
        }
        if (StringUtils.isEmpty(amount.getCurrency())) {
            throw new ParamCheckException("amount.currency 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getPayer()) || StringUtils.isEmpty(request.getPayer().getOpenId())) {
            throw new ParamCheckException("payer.openid 不能为空，请检查！！！");
        }
        return paymentClient.createOrder(request, app);
    }

    /**
     * 查询小程序对外收款订单
     *
     * @param request 请求
     * @param app     应用名
     * @return 订单详情
     */
    public MiniOrderResponse getOrder(GetOrderRequest request, String app) {
        checkGetOrderIdentity(request);
        return paymentClient.getOrder(request, app);
    }

    /**
     * 关闭小程序对外收款订单
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    public BaseResponse closeOrder(CloseOrderRequest request, String app) {
        checkCloseOrderIdentity(request);
        return paymentClient.closeOrder(request, app);
    }

    /**
     * 获取小程序支付签名
     *
     * @param request 请求
     * @param app     应用名
     * @return 支付签名
     */
    public MiniSignResponse getSign(GetSignRequest request, String app) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("获取支付签名请求不能为空");
        }
        if (StringUtils.isEmpty(request.getAppId())) {
            throw new ParamCheckException("appid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getPrepayId())) {
            throw new ParamCheckException("prepay_id 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getNonce())) {
            throw new ParamCheckException("nonce 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getTimestamp())) {
            throw new ParamCheckException("timestamp 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getSignType())) {
            request.setSignType("RSA");
        }
        if (!"RSA".equals(request.getSignType())) {
            throw new ParamCheckException("sign_type 当前仅支持RSA，请检查！！！");
        }
        return paymentClient.getSign(request, app);
    }

    /**
     * 申请小程序对外收款退款
     *
     * @param request 请求
     * @param app     应用名
     * @return 退款受理结果
     */
    public MiniRefundResponse refund(RefundRequest request, String app) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("退款请求不能为空");
        }
        List<String> required = Arrays.asList(request.getMchId(), request.getAppId(),
                request.getOutTradeNo(), request.getOutRefundNo());
        if (required.stream().anyMatch(StringUtils::isEmpty)) {
            throw new ParamCheckException("mchid、appid、out_trade_no 和 out_refund_no 不能为空，请检查！！！");
        }
        RefundAmount amount = request.getAmount();
        if (Objects.isNull(amount)) {
            throw new ParamCheckException("amount 不能为空，请检查！！！");
        }
        if (Objects.isNull(amount.getRefund()) || amount.getRefund() <= 0) {
            throw new ParamCheckException("amount.refund 必须为大于0的退款金额，请检查！！！");
        }
        if (Objects.isNull(amount.getTotal()) || amount.getTotal() <= 0) {
            throw new ParamCheckException("amount.total 必须为大于0的原订单金额，请检查！！！");
        }
        if (amount.getRefund() > amount.getTotal()) {
            throw new ParamCheckException("amount.refund 不能大于 amount.total，请检查！！！");
        }
        if (!"CNY".equals(amount.getCurrency())) {
            throw new ParamCheckException("amount.currency 当前仅支持CNY，请检查！！！");
        }
        return paymentClient.refund(request, app);
    }

    /**
     * 查询小程序对外收款退款详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 退款详情
     */
    public MiniRefundDetailResponse getRefundDetail(GetRefundDetailRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getMchId())
                || StringUtils.isEmpty(request.getOutRefundNo())) {
            throw new ParamCheckException("mchid 和 out_refund_no 不能为空，请检查！！！");
        }
        return paymentClient.getRefundDetail(request, app);
    }

    /**
     * 申请小程序对外收款交易账单
     *
     * @param request 请求
     * @param app     应用名
     * @return 账单下载信息
     */
    public MiniBillResponse getBill(GetBillRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getBillDate())
                || StringUtils.isEmpty(request.getMchId())) {
            throw new ParamCheckException("bill_date 和 mchid 不能为空，请检查！！！");
        }
        if (!BILL_DATE_PATTERN.matcher(request.getBillDate()).matches()) {
            throw new ParamCheckException("bill_date 格式必须为yyyy-MM-dd，请检查！！！");
        }
        if (request.getMchId().length() < 8 || request.getMchId().length() > 32) {
            throw new ParamCheckException("mchid 长度必须在8到32字节之间，请检查！！！");
        }
        if (StringUtils.isNotEmpty(request.getBillType())
                && !Arrays.asList("ALL", "SUCCESS", "REFUND").contains(request.getBillType())) {
            throw new ParamCheckException("bill_type 仅支持ALL、SUCCESS或REFUND，请检查！！！");
        }
        if (StringUtils.isNotEmpty(request.getTarType()) && !"GZIP".equals(request.getTarType())) {
            throw new ParamCheckException("tar_type 仅支持空值或GZIP，请检查！！！");
        }
        return paymentClient.getBill(request, app);
    }

    private void checkApplyMch(ApplyMchRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("创建收款账户申请请求不能为空");
        }
        List<String> requiredTexts = Arrays.asList(request.getOutRequestNo(), request.getMerchantShortName(),
                request.getUserId());
        if (requiredTexts.stream().anyMatch(StringUtils::isEmpty) || Objects.isNull(request.getOrganizationType())) {
            throw new ParamCheckException("out_request_no、organization_type、merchant_short_name 和 userid 必填，请检查！！！");
        }
        checkApplyBusinessLicense(request.getBusinessLicenseInfo());
        checkApplyIdCard(request.getIdCardInfo(), "id_card_info");
        checkApplyContact(request.getContactInfo());
        checkApplyAccount(request.getAccountInfo());
        checkApplySalesScene(request.getSalesSceneInfo());
        if (Objects.isNull(request.getBusinessId())) {
            throw new ParamCheckException("business_id 不能为空，请检查！！！");
        }
    }

    private void checkApplyBusinessLicense(ApplyMchBusinessLicenseInfo info) {
        if (Objects.isNull(info) || StringUtils.isEmpty(info.getBusinessLicenseCopyMediaId())
                || StringUtils.isEmpty(info.getBusinessLicenseNumber())
                || StringUtils.isEmpty(info.getMerchantName())
                || StringUtils.isEmpty(info.getLegalPerson())) {
            throw new ParamCheckException("business_license_info 核心字段不能为空，请检查！！！");
        }
    }

    private void checkApplyIdCard(ApplyMchIdCardInfo info, String fieldName) {
        if (Objects.isNull(info) || StringUtils.isEmpty(info.getIdCardCopyMediaId())
                || StringUtils.isEmpty(info.getIdCardNationalMediaId())
                || StringUtils.isEmpty(info.getIdCardName())
                || StringUtils.isEmpty(info.getIdCardNumber())) {
            throw new ParamCheckException(fieldName + " 核心证件字段不能为空，请检查！！！");
        }
    }

    private void checkApplyContact(ApplyMchContactInfo info) {
        if (Objects.isNull(info) || !Arrays.asList("65", "66").contains(info.getContactType())) {
            throw new ParamCheckException("contact_info.contact_type 必须为65或66，请检查！！！");
        }
        checkApplyIdCard(info.getContactInfo(), "contact_info.contact_info");
        if (StringUtils.isEmpty(info.getMobilePhone()) || StringUtils.isEmpty(info.getContactEmail())) {
            throw new ParamCheckException("超级管理员手机号和邮箱不能为空，请检查！！！");
        }
    }

    private void checkApplyAccount(ApplyMchAccountInfo info) {
        if (Objects.isNull(info) || StringUtils.isEmpty(info.getAccountBank())
                || StringUtils.isEmpty(info.getAccountName())
                || StringUtils.isEmpty(info.getBankAddressCode())
                || StringUtils.isEmpty(info.getAccountNumber())) {
            throw new ParamCheckException("account_info 核心字段不能为空，请检查！！！");
        }
    }

    private void checkApplySalesScene(ApplyMchSalesSceneInfo info) {
        if (Objects.isNull(info) || Objects.isNull(info.getType())) {
            throw new ParamCheckException("sales_scene_info.type 不能为空，请检查！！！");
        }
        if (Integer.valueOf(2).equals(info.getType())
                && StringUtils.isEmpty(info.getStoreUrl())
                && StringUtils.isEmpty(info.getStorePicMediaId())) {
            throw new ParamCheckException("线上店铺必须填写店铺地址或店铺二维码，请检查！！！");
        }
        if (Integer.valueOf(1).equals(info.getType())
                && (StringUtils.isEmpty(info.getAddressCode()) || StringUtils.isEmpty(info.getOfflineAddress()))) {
            throw new ParamCheckException("线下门店必须填写省市区编码和详细地址，请检查！！！");
        }
    }

    private void checkGetOrderIdentity(GetOrderRequest request) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getMchId()) || StringUtils.isEmpty(request.getOutTradeNo())) {
            throw new ParamCheckException("mchid 和 out_trade_no 不能为空，请检查！！！");
        }
    }

    private void checkCloseOrderIdentity(CloseOrderRequest request) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getMchId()) || StringUtils.isEmpty(request.getOutTradeNo())) {
            throw new ParamCheckException("mchid 和 out_trade_no 不能为空，请检查！！！");
        }
    }

    private void checkRequiredTimeRange(Long beginTime, Long endTime, String label) {
        if (Objects.isNull(beginTime) || Objects.isNull(endTime)) {
            throw new ParamCheckException(label + "的起止时间不能为空，请检查！！！");
        }
        if (endTime < beginTime) {
            throw new ParamCheckException(label + "的结束时间不能早于开始时间，请检查！！！");
        }
    }

    private void checkPageLimit(Integer limit, int maxValue, String label) {
        if (Objects.nonNull(limit) && (limit < 1 || limit > maxValue)) {
            throw new ParamCheckException(label + "的limit最小为1，最大为" + maxValue + "，请检查！！！");
        }
    }

    private void checkMonthRange(Long beginTime, Long endTime, String label) {
        long months = ChronoUnit.MONTHS.between(Instant.ofEpochSecond(beginTime), Instant.ofEpochSecond(endTime));
        if (months > 1) {
            throw new ParamCheckException(label + "的起止时间间隔不能超过1个月，请检查！！！");
        }
    }
}
