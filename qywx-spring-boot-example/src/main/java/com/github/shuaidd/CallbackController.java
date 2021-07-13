package com.github.shuaidd;

import com.github.shuaidd.callback.AesException;
import com.github.shuaidd.callback.WXBizJsonMsgCrypt;
import com.github.shuaidd.callback.WXBizMsgXmlCrypt;
import com.github.shuaidd.event.BaseEventData;
import com.github.shuaidd.support.EventDataManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 企业微信回调
 * token 4pd5Q2GAVKAapyTD0IGLMD7Lngiyx
 * aeskey QJqvdlS4M4FTPUNqSQrMg3VwNRyBCqTqtNStREy3Ahd
 *
 * @author ddshuai
 * @date 2021-07-13 13:33
 **/
@RestController
@RequestMapping("/")
public class CallbackController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 通讯录回调
     *
     * @param msgSignature
     * @param nonce
     * @param echoStr
     * @param timestamp
     * @param xml
     * @return
     * @throws AesException
     */
    @RequestMapping("/addressBook/callback")
    public String callback(@RequestParam(value = "msg_signature", required = false) String msgSignature,
                           @RequestParam(value = "nonce", required = false) String nonce,
                           @RequestParam(value = "echostr", required = false) String echoStr,
                           @RequestParam(value = "timestamp", required = false) String timestamp,
                           @RequestBody(required = false) String xml
    ) throws AesException {
        logger.info("密文--{}--timestamp--{}--nonce---{},echostr--{}", msgSignature, timestamp, nonce, echoStr);
        WXBizMsgXmlCrypt crypt = new WXBizMsgXmlCrypt("4pd5Q2GAVKAapyTD0IGLMD7Lngiyx", "QJqvdlS4M4FTPUNqSQrMg3VwNRyBCqTqtNStREy3Ahd", "ww36e0a51aab349a7d");
        if (StringUtils.isNotEmpty(echoStr)) {
            String msg = crypt.VerifyURL(msgSignature, timestamp, nonce, echoStr);
            logger.info(msg);
            return msg;
        }

        if (StringUtils.isNotEmpty(xml)) {
            logger.info("接收到的postData--{}", xml);
            String data = crypt.DecryptMsg(msgSignature, timestamp, nonce, xml);
            logger.info("拿到的明文信息---{}", data);
            BaseEventData eventData = EventDataManager.getXmlData(data);
            logger.info("BaseEventData -- {}",eventData);
            return data;
        }

        return null;
    }

    @RequestMapping("/json/callback")
    public String callbackJson(@RequestParam(value = "msg_signature", required = false) String msgSignature,
                               @RequestParam(value = "nonce", required = false) String nonce,
                               @RequestParam(value = "echostr", required = false) String echoStr,
                               @RequestParam(value = "timestamp", required = false) String timestamp,
                               @RequestBody(required = false) String json
    ) throws AesException {
        logger.info("密文--{}--timestamp--{}--nonce---{},echostr--{}", msgSignature, timestamp, nonce, echoStr);
        WXBizJsonMsgCrypt crypt = new WXBizJsonMsgCrypt("4pd5Q2GAVKAapyTD0IGLMD7Lngiyx", "QJqvdlS4M4FTPUNqSQrMg3VwNRyBCqTqtNStREy3Ahd", "ww36e0a51aab349a7d");
        if (StringUtils.isNotEmpty(echoStr)) {
            String msg = crypt.VerifyURL(msgSignature, timestamp, nonce, echoStr);
            logger.info(msg);
            return msg;
        }

        if (StringUtils.isNotEmpty(json)) {
            logger.info("接收到的postData--{}", json);
            String data = crypt.DecryptMsg(msgSignature, timestamp, nonce, json);
            logger.info("拿到的明文信息---{}", data);
            return data;
        }

        return null;
    }
}
