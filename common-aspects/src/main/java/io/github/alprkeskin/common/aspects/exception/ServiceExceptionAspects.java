package io.github.alprkeskin.common.aspects.exception;

import io.github.alprkeskin.common.aspects.util.AspectUtil;
import io.github.alprkeskin.common.exception.CustomException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Order(Ordered.HIGHEST_PRECEDENCE + 3)
@Aspect
@Service
public class ServiceExceptionAspects {
    @AfterThrowing(pointcut = "io.github.alprkeskin.common.aspects.util.PointcutUtil.servicePointcut()", throwing = "t")
    public void handleError(JoinPoint joinPoint, Throwable t) {
        if(t instanceof CustomException)
            return;
        CustomException e = new CustomException(t, "Service is faced with an exception.", SERVICE_UNAVAILABLE);
        AspectUtil.handleException(joinPoint, e);
    }
}
