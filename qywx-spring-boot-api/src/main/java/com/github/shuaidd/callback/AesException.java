package com.github.shuaidd.callback;

/**
 * @author shuaidongdong
 */
@SuppressWarnings("serial")
public class AesException extends Exception {

    public final static int OK = 0;
    public final static int VALIDATE_SIGNATURE_ERROR = -40001;
    public final static int PARSE_JSON_ERROR = -40002;
    public final static int COMPUTE_SIGNATURE_ERROR = -40003;
    public final static int ILLEGAL_AES_KEY = -40004;
    public final static int VALIDATE_CORPID_ERROR = -40005;
    public final static int ENCRYPTAES_ERROR = -40006;
    public final static int DECRYPTAES_ERROR = -40007;
    public final static int ILLEGAL_BUFFER = -40008;
    public final static int ENCODE_BASE64_ERROR = -40009;
    public final static int DECODE_BASE64_ERROR = -40010;
    public final static int GenReturnJsonError = -40011;
    public final static int ParseXmlError = -400021;

    private final int code;

    private static String getMessage(int code) {
        switch (code) {
            case VALIDATE_SIGNATURE_ERROR:
                return "签名验证错误";
            case PARSE_JSON_ERROR:
                return "json解析失败";
            case ParseXmlError:
                return "xml解析失败";
            case COMPUTE_SIGNATURE_ERROR:
                return "sha加密生成签名失败";
            case ILLEGAL_AES_KEY:
                return "SymmetricKey非法";
            case VALIDATE_CORPID_ERROR:
                return "corpid校验失败";
            case ENCRYPTAES_ERROR:
                return "aes加密失败";
            case DECRYPTAES_ERROR:
                return "aes解密失败";
            case ILLEGAL_BUFFER:
                return "解密后得到的buffer非法";
            case ENCODE_BASE64_ERROR:
                return "base64加密错误";
            case DECODE_BASE64_ERROR:
                return "base64解密错误";
            case GenReturnJsonError:
                return "josn生成失败";
            default:
                return null;
        }
    }

    public int getCode() {
        return code;
    }

    AesException(int code) {
        super(getMessage(code));
        this.code = code;
    }

}
