package com.github.shuaidd.aspect;

import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.support.WeChatManager;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Method;

/**
 * 描述 enjoy your life
 * 统一处理微信授权码无效问题
 * <p>
 * date 2018-11-19 下午4:39
 *
 * @author shuaidongdong
 */
@Aspect
@Component
public class WeChatClientAspect {

    private final WeChatManager weChatManager;

    private static final Logger log = LoggerFactory.getLogger(WeChatClientAspect.class);

    @Autowired
    public WeChatClientAspect(WeChatManager weChatManager) {
        this.weChatManager = weChatManager;
    }

    /**
     * 统一处理请求AccessToken失效问题
     *
     * @param joinPoint 切点信息
     * @return Object
     * @throws Throwable 异常
     */
    @Around("execution(* com.github.shuaidd.client.*Client.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        StopWatch watch = new StopWatch();
        try {
            watch.start("调用接口 " + joinPoint.getSignature().getName());
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            if (throwable instanceof WeChatException || throwable.getCause() instanceof WeChatException) {
                WeChatException w = throwable instanceof WeChatException ? (WeChatException) throwable : (WeChatException) throwable.getCause();
                if (w.getErrorCode() != null && needRetry(w.getErrorCode().getErrorCode())) {

                    if (log.isDebugEnabled()) {
                        log.debug("重试一次微信调用：{}", w.getErrorCode().getErrorCode());
                    }
                    String applicationName = getApplicationName(joinPoint);
                    if (StringUtils.isNotEmpty(applicationName)) {
                        //清除掉缓存的accessToken 重新获取新的access_token
                        weChatManager.tokenService().clearAccessToken(applicationName);
                    }

                    //如果还未成功，会有日志记录，查看日志再看具体原因
                    result = joinPoint.proceed();
                } else {
                    throw throwable;
                }
            } else {
                throw throwable;
            }
        } finally {
            watch.stop();
        }

        if (weChatManager.properties().getDebugMode()) {
            log.debug("调用企微接口耗时--{}秒",watch.getTotalTimeSeconds());
        }
        return result;
    }

    /**
     * 获取请求的应用
     *
     * @param joinPoint 切点信息
     * @return String
     */
    private String getApplicationName(ProceedingJoinPoint joinPoint) {
        String applicationName = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            String[] headers = postMapping.headers();
            if (headers.length > 0) {
                //获取应用
                Object[] objects = joinPoint.getArgs();
                if (objects.length > 0) {
                    /*约定好应用这个参数放在最后*/
                    applicationName = (String) objects[objects.length - 1];
                }
            }
        } else {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            if (getMapping != null) {
                String[] headers = getMapping.headers();
                if (headers.length > 0) {
                    //获取应用
                    Object[] objects = joinPoint.getArgs();
                    if (objects.length > 0) {
                        /*约定好应用这个参数放在最后*/
                        applicationName = (String) objects[objects.length - 1];
                    }
                }
            }
        }
        return applicationName;
    }

    /**
     * 如果微信服务调用返回码为
     * 40014[不合法的access_token]
     * 41001[缺少access_token参数]
     * 42001[access_token已过期]
     * 则重新获取授权码，重试一次
     *
     * @param errorCode 错误码
     * @return boolean 是否重试
     */
    private boolean needRetry(String errorCode) {
        boolean needRetry = false;

        if (ErrorCode.ERROR_CODE_40014.getErrorCode().equals(errorCode)) {
            needRetry = true;
        }

        if (ErrorCode.ERROR_CODE_41001.getErrorCode().equals(errorCode)) {
            needRetry = true;
        }

        if (ErrorCode.ERROR_CODE_42001.getErrorCode().equals(errorCode)) {
            needRetry = true;
        }

        return needRetry;
    }
}
