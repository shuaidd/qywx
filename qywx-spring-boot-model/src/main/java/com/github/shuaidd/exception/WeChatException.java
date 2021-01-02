package com.github.shuaidd.exception;


import com.github.shuaidd.enums.ErrorCode;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-04 16:32
 **/
public class WeChatException extends RuntimeException {
    private ErrorCode errorCode;

    public WeChatException(String message) {
        super(message);
    }


    public WeChatException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }


    public WeChatException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


    public WeChatException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public WeChatException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public WeChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
