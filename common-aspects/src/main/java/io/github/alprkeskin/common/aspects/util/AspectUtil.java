package io.github.alprkeskin.common.aspects.util;

import io.github.alprkeskin.common.exception.CustomException;
import org.aspectj.lang.JoinPoint;

import static io.github.alprkeskin.common.aspects.util.LoggingUtil.handleErrorLog;

public final class AspectUtil {
    private AspectUtil() {

    }

    public static void handleException(JoinPoint joinPoint, CustomException e) {
        String methodName = joinPoint.getSignature().getName();
        Class<?> throwerClass = getTargetClass(joinPoint, methodName);
        LoggingUtil.handleErrorLog(throwerClass, methodName, joinPoint, e.getCause());

        throw e;
    }

    public static Class<?> getTargetClass(JoinPoint joinPoint, String methodName) {
        try {
            return Class.forName(getTargetClassStr(joinPoint, methodName));
        } catch (Throwable t) {
            return AspectUtil.class;
        }
    }

    private static String getTargetClassStr(JoinPoint joinPoint, String methodName) {
        String packageName = joinPoint.getSignature().getDeclaringTypeName();
        String longName = joinPoint.getSignature().toLongString();

        int start = longName.indexOf(packageName);
        int end = longName.lastIndexOf(methodName + "(") - 1;

        return longName.substring(start, end);
    }

}
