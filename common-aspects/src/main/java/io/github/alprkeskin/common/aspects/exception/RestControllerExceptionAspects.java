package io.github.alprkeskin.common.aspects.exception;

import io.github.alprkeskin.common.aspects.util.AspectUtil;
import io.github.alprkeskin.common.exception.CustomException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@Aspect
@Service
public class RestControllerExceptionAspects {
    @AfterThrowing(pointcut = "io.github.alprkeskin.common.aspects.util.PointcutUtil.restControllerPointcut()", throwing = "t")
    public void handleError(JoinPoint joinPoint, Throwable t) {
        if(t instanceof CustomException)
            return;
        CustomException e = new CustomException(t, "RESTFul service has an error.", BAD_REQUEST);
        AspectUtil.handleException(joinPoint, e);
    }
}
