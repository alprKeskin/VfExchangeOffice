package io.github.alprkeskin.common.aspects.logging;

import io.github.alprkeskin.common.aspects.util.LoggingUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import static io.github.alprkeskin.common.aspects.util.LoggingUtil.handleErrorLog;

@Aspect
@Service
public class ErrorLoggingAspect {
    @AfterThrowing(pointcut = "io.github.alprkeskin.common.aspects.util.PointcutUtil.logErrorPointcut()",throwing = "t")
    public void logError(JoinPoint joinPoint, Throwable t) {
        LoggingUtil.handleErrorLog(joinPoint,t);
    }
}
