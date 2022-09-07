package io.github.alprkeskin.common.aspects.logging;

import io.github.alprkeskin.common.aspects.util.LoggingUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;

public class InfoLoggingAspect {
    @Before("util.io.github.alprkeskin.common.aspects.PointcutUtil.logInfoPointcut()")
    public void logBeforeInfo(JoinPoint joinPoint) {
        LoggingUtil.handleBeforeInfoLog(joinPoint, true);
    }

    @AfterReturning(pointcut = "util.io.github.alprkeskin.common.aspects.PointcutUtil.logInfoPointcut()", returning = "returnValue")
    public void logAfterInfo(JoinPoint joinPoint, Object returnValue) {
        LoggingUtil.handleAfterInfoLog(joinPoint, returnValue);
    }
}
