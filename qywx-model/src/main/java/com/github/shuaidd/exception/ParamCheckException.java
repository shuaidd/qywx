package com.github.shuaidd.exception;


/**
 * 参数校验异常
 *
 * @author ddshuai
 * date 2019-04-04 16:32
 **/
public class ParamCheckException extends RuntimeException {

    public ParamCheckException(String message) {
        super(message);
    }
}
