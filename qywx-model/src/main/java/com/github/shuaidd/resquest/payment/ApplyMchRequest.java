package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;
import lombok.experimental.Accessors;
import com.github.shuaidd.dto.payment.*;

/**
 * 创建对外收款账户申请单请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplyMchRequest {

    /**
     * 业务申请编号，长度 1 到 32
     **/
    @JsonProperty("out_request_no")
    private String outRequestNo;

    /**
     * 主体类型：0 企业、1 个体、2 社会团体组织、3 事业单位
     **/
    @JsonProperty("organization_type")
    private Integer organizationType;

    /**
     * 营业执照或登记证书信息
     **/
    @JsonProperty("business_license_info")
    private ApplyMchBusinessLicenseInfo businessLicenseInfo;

    /**
     * 金融机构许可证信息，金融机构主体必填
     **/
    @JsonProperty("finance_institution_info")
    private ApplyMchFinanceInstitutionInfo financeInstitutionInfo;

    /**
     * 商户简称
     **/
    @JsonProperty("merchant_short_name")
    private String merchantShortName;

    /**
     * 经营者或法人证件信息
     **/
    @JsonProperty("id_card_info")
    private ApplyMchIdCardInfo idCardInfo;

    /**
     * 经营者或法人是否为最终受益人
     **/
    @JsonProperty("owner")
    private Boolean owner;

    /**
     * 最终受益人证件信息
     **/
    @JsonProperty("ubo_info")
    private ApplyMchIdCardInfo uboInfo;

    /**
     * 超级管理员信息
     **/
    @JsonProperty("contact_info")
    private ApplyMchContactInfo contactInfo;

    /**
     * 结算账户信息
     **/
    @JsonProperty("account_info")
    private ApplyMchAccountInfo accountInfo;

    /**
     * 经营场景证明
     **/
    @JsonProperty("sales_scene_info")
    private ApplyMchSalesSceneInfo salesSceneInfo;

    /**
     * 经营范围编号
     **/
    @JsonProperty("business_id")
    private Integer businessId;

    /**
     * 特殊资质照片 ID 映射
     **/
    @JsonProperty("qualifications")
    private Map<String, List<String>> qualifications;

    /**
     * 补充材料照片 ID 映射
     **/
    @JsonProperty("business_addition_pics")
    private Map<String, List<String>> businessAdditionPics;

    /**
     * 实名认证的提现人员 userid
     **/
    @JsonProperty("userid")
    private String userId;
}
