package com.github.shuaidd.service;

import com.github.shuaidd.client.WeChatClient;
import com.github.shuaidd.client.WeChatMediaClient;
import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.client.config.WeChatConfigurationProperties;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.AbstractBaseResponse;
import com.github.shuaidd.response.BaseResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-04 14:24
 **/
public abstract class AbstractBaseService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected WeChatClient weChatClient;

    @Autowired
    protected WeChatMediaClient weChatMediaClient;

    @Autowired
    protected WeChatConfigurationProperties properties;

    /**
     * 是否检查结果
     */
    private boolean check = true;

    final void setCheck(boolean check){
        this.check = check;
    }

    final boolean isSuccess(AbstractBaseResponse baseResponse) {
        if (Objects.nonNull(baseResponse)) {
            if (ErrorCode.ERROR_CODE_0.getErrorCode().equals(baseResponse.getErrCode().toString())) {
                return true;
            } else {
                ErrorCode errorCode = ErrorCode.errorCode(baseResponse.getErrCode());
                logger.error("企业微信调用异常：errorCode[{}],msg:[{}],response:{}", errorCode.getErrorCode(), errorCode.getErrorDesc(), baseResponse.getErrMsg());
                throw new WeChatException(errorCode.getErrorDesc(), errorCode);
            }
        } else {
            return false;
        }

    }

    /**
     * 获取企业微信结果
     * @param response
     * @param <T>
     * @return
     */
    final <T extends AbstractBaseResponse> T getResponse(T response){
        if (check){
            if (isSuccess(response)){
                return response;
            }
        }
        return response;
    }

    /**
     * 获取应用密匙
     *
     * @param applicationName
     * @return
     */
    final String getApplicationSecret(String applicationName) {
        String secret = "";
        List<ApplicationProperties> list = properties.getApplicationList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ApplicationProperties applicationProperties : list) {
                if (Objects.equals(applicationName, applicationProperties.getApplicationName())) {
                    secret = applicationProperties.getSecret();
                }
            }
        }
        if (StringUtils.isEmpty(secret)) {
            //不存在的应用 则抛出异常
            throw new WeChatException(applicationName + "应用不存在密匙");
        }

        return secret;
    }

    final void checkApplication(String applicationName){
        Objects.requireNonNull(applicationName,"应用名称不能为空");
        boolean exsit = false;
        for (ApplicationProperties applicationProperties : properties.getApplicationList()) {
            if (applicationName.equals(applicationProperties.getApplicationName())){
                exsit = true;
                break;
            }
        }
        if (!exsit){
            throw new WeChatException("应用"+applicationName+"无效，不在配置列表之内");
        }
    }
}
