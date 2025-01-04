package com.github.shuaidd.support;

import com.github.shuaidd.client.config.WeChatConfigurationProperties;
import com.github.shuaidd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 企业微信服务操作统一端口
 *
 * @author ddshuai
 * date 2019-04-03 16:10
 **/
@Component
public class WeChatManager {

    private final AddressBookService addressBookService;
    private final TokenService tokenService;
    private final WeChatConfigurationProperties properties;
    private final AuthenticationService authenticationService;
    private final ApplicationService applicationService;
    private final MediaService mediaService;
    private final MessageService messageService;
    private final OAService oaService;
    private final CustomContactService customContactService;
    private final EfficiencyToolService efficiencyToolService;
    private final WeChatCallCenterService callCenterService;
    private final CommonService commonService;
    private final ServiceProviderService serviceProviderService;
    private final EnterpriseEmailService enterpriseEmailService;
    private final ElectronicInvoiceService electronicInvoiceService;
    private final EnterpriseInterConnectionService enterpriseInterConnectionService;
    private final MeetingService meetingService;
    private final FrontEndToolService frontEndToolService;
    private final DocumentService documentService;
    private final SmartSheetService smartSheetService;

    @Autowired
    public WeChatManager(AddressBookService addressBookService,
                         TokenService tokenService,
                         WeChatConfigurationProperties properties,
                         AuthenticationService authenticationService,
                         ApplicationService applicationService,
                         MediaService mediaService, MessageService messageService,
                         OAService oaService,
                         CustomContactService customContactService,
                         EfficiencyToolService efficiencyToolService,
                         WeChatCallCenterService callCenterService,
                         CommonService commonService,
                         ServiceProviderService serviceProviderService,
                         EnterpriseEmailService enterpriseEmailService,
                         ElectronicInvoiceService electronicInvoiceService,
                         EnterpriseInterConnectionService enterpriseInterConnectionService,
                         MeetingService meetingService,
                         FrontEndToolService frontEndToolService, DocumentService documentService, SmartSheetService smartSheetService) {
        this.addressBookService = addressBookService;
        this.tokenService = tokenService;
        this.properties = properties;
        this.authenticationService = authenticationService;
        this.applicationService = applicationService;
        this.mediaService = mediaService;
        this.messageService = messageService;
        this.oaService = oaService;
        this.customContactService = customContactService;
        this.efficiencyToolService = efficiencyToolService;
        this.callCenterService = callCenterService;
        this.commonService = commonService;
        this.serviceProviderService = serviceProviderService;
        this.enterpriseEmailService = enterpriseEmailService;
        this.electronicInvoiceService = electronicInvoiceService;
        this.enterpriseInterConnectionService = enterpriseInterConnectionService;
        this.meetingService = meetingService;
        this.frontEndToolService = frontEndToolService;
        this.documentService = documentService;
        this.smartSheetService = smartSheetService;
    }

    /**
     * 通讯录管理服务
     *
     * @return AddressBookService
     */
    public AddressBookService addressBookService() {
        return addressBookService;
    }

    /**
     * 令牌管理服务
     *
     * @return TokenService
     */
    public TokenService tokenService() {
        return tokenService;
    }

    /**
     * 企业微信配置信息
     *
     * @return WeChatConfigurationProperties
     */
    public WeChatConfigurationProperties properties() {
        return properties;
    }

    /**
     * 身份验证
     *
     * @return AuthenticationService
     */
    public AuthenticationService authenticationService() {
        return authenticationService;
    }

    /**
     * 素材管理
     *
     * @return MediaService
     */
    public MediaService mediaService() {
        return mediaService;
    }

    /**
     * 应用管理
     *
     * @return ApplicationService
     */
    public ApplicationService applicationService() {
        return applicationService;
    }

    /**
     * 消息管理
     *
     * @return MessageService
     */
    public MessageService messageService() {
        return messageService;
    }

    /**
     * OA数据管理
     *
     * @return OAService
     */
    public OAService oaService() {
        return oaService;
    }

    /**
     * 客户联系
     *
     * @return CustomContactService
     */
    public CustomContactService customContactService() {
        return customContactService;
    }

    /**
     * 效率工具
     *
     * @return EfficiencyToolService
     */
    public EfficiencyToolService efficiencyTool() {
        return efficiencyToolService;
    }

    /**
     * 微信客服
     *
     * @return WeChatCallCenterService
     */
    public WeChatCallCenterService callCenterService() {
        return callCenterService;
    }

    /**
     * 服务商
     *
     * @return ServiceProviderService
     */
    public ServiceProviderService serviceProviderService() {
        return serviceProviderService;
    }

    /**
     * 邮箱，群组管理
     *
     * @return enterpriseEmailService
     */
    public EnterpriseEmailService enterpriseEmailService() {
        return enterpriseEmailService;
    }

    /**
     * 电子发票
     *
     * @return ElectronicInvoiceService
     */
    public ElectronicInvoiceService electronicInvoiceService() {
        return electronicInvoiceService;
    }

    /**
     * 企业互联
     *
     * @return EnterpriseInterConnectionService
     */
    public EnterpriseInterConnectionService enterpriseInterConnectionService() {
        return enterpriseInterConnectionService;
    }

    /**
     * 公共服务
     *
     * @return CommonService
     */
    public CommonService commonService() {
        return commonService;
    }

    /**
     * 预约会议管理
     *
     * @return MeetingService
     */
    public MeetingService meetingService() {
        return meetingService;
    }

    /**
     * 前端SDK管理
     *
     * @return MeetingService
     */
    public FrontEndToolService frontEndToolService() {
        return frontEndToolService;
    }

    /**
     * 文档管理
     * @return DocumentService
     */
    public DocumentService documentService() {
        return documentService;
    }

    /**
     * 智能表格
     * @return SmartSheetService
     */
    public SmartSheetService smartSheetService() {
        return smartSheetService;
    }
}
