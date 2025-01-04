package com.github.shuaidd.exception;


import com.github.shuaidd.enums.ErrorCode;
import lombok.Getter;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-04 16:32
 **/
@Getter
public class WeChatException extends RuntimeException {
    private ErrorCode errorCode;
    private String code;
    private String msg;

    public WeChatException(String message) {
        super(message);
    }


    public WeChatException(ErrorCode errorCode) {
        super(errorCode.getErrorDesc());
        this.errorCode = errorCode;
        this.code = errorCode.getErrorCode();
        this.msg = errorCode.getErrorDesc();
    }

    public WeChatException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.code = errorCode.getErrorCode();
        this.msg = errorCode.getErrorDesc();
    }

    public WeChatException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.code = errorCode.getErrorCode();
        this.msg = errorCode.getErrorDesc();
    }

    public WeChatException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.code = errorCode.getErrorCode();
        this.msg = errorCode.getErrorDesc();
    }

    public WeChatException(String code,String msg) {
        super(msg);
        this.errorCode = ErrorCode.UNKNOWN;
        this.code = code;
        this.msg = msg;
    }

    public WeChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.code = errorCode.getErrorCode();
        this.msg = errorCode.getErrorDesc();
    }

    public static WeChatException unknown(String code,String msg) {
        return new WeChatException(code,msg);
    }
}
