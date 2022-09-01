package com.github.shuaidd.aspect;

import com.github.shuaidd.support.WeChatManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ddshuai
 * date 2022-09-01 10:43
 **/
@Aspect
@Component
public class WeChatServiceAspect {

    private static final Logger log = LoggerFactory.getLogger(WeChatServiceAspect.class);
    private final WeChatManager weChatManager;
    public static final String APPLICATION_NAME = "applicationName";

    public WeChatServiceAspect(WeChatManager weChatManager) {
        this.weChatManager = weChatManager;
    }

    @Around("execution(* com.github.shuaidd.service.*.*(..))")
    public Object checkApplicationName(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        Object[] args = pjp.getArgs();
        for (int loopCount = 0; loopCount < paramNames.length; loopCount++) {
            String parameterName = paramNames[loopCount];
            if (APPLICATION_NAME.equals(parameterName)) {
                //有应用名参数的都需要检验
                weChatManager.commonService().checkApplication((String) args[loopCount]);
            }
        }

        return pjp.proceed();
    }
}
