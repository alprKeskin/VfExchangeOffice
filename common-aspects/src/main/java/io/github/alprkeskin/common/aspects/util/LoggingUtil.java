package io.github.alprkeskin.common.aspects.util;

import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.alprkeskin.common.aspects.util.AspectUtil.getTargetClass;

public final class LoggingUtil {

	private LoggingUtil() {

	}

	public static void handleErrorLog(JoinPoint joinPoint, Throwable t) {
		String methodName = joinPoint.getSignature().getName();
		Class<?> throwerClass = getTargetClass(joinPoint, methodName);
		handleErrorLog(throwerClass, methodName, joinPoint, t);
	}

	public static void handleErrorLog(Class<?> throwerClass, String methodName, JoinPoint joinPoint, Throwable t) {
		StringBuilder message = new StringBuilder(methodName);

		message.append(" is completed with exception. ");

		printErrorLog(throwerClass, message, true, t, joinPoint.getArgs());
	}

	public static void handleBeforeInfoLog(JoinPoint joinPoint, boolean printArguments) {
		String methodName = joinPoint.getSignature().getName();
		Class<?> declaringClass = getTargetClass(joinPoint, methodName);
		StringBuilder message = new StringBuilder(methodName);

		message.append(" is started. ");

		printInfoLog(declaringClass, message, printArguments, joinPoint.getArgs());
	}

	public static void handleAfterInfoLog(JoinPoint joinPoint, Object returnValue) {
		String methodName = joinPoint.getSignature().getName();
		Class<?> declaringClass = getTargetClass(joinPoint, methodName);
		StringBuilder message = new StringBuilder(methodName);

		message.append(" is completed.");
		if(returnValue != null) {
			message.append(" :: return value={}");
			LoggerFactory.getLogger(declaringClass).info(message.toString(), returnValue);
			return;
		}

		LoggerFactory.getLogger(declaringClass).info(message.toString());
	}

	public static void printInfoLog(Class<?> declaringClass, StringBuilder message, boolean printArguments, Object... args) {
		Logger logger = LoggerFactory.getLogger(declaringClass);
		if(args == null || !printArguments){
			logger.info(message.toString());
		} else {
			addArgumentsToLogMessage(message, args);
			logger.info(message.toString(), args);
		}
	}

	 public static void printErrorLog(Class<?> throwerClass, StringBuilder message,  boolean printArguments, Throwable t, Object... args) {
		Logger logger = LoggerFactory.getLogger(throwerClass);
		if(args == null || !printArguments){
			logger.error(message.toString(), t);
		} else {
			addArgumentsToLogMessage(message, args);
			message.append(" :: exception={} ");
			logger.error(message.toString(), handleErrorArgs(t, args));
		}
	}

	private static void addArgumentsToLogMessage(StringBuilder message, Object[] args) {
		for(int i = 0; i < args.length; i++) {
			message.append(" :: arg" + i + "={} ");
		}
	}

	private static Object[] handleErrorArgs(Throwable t, Object... args) {
		return Stream.concat(Stream.of(args), Stream.of(t)).toArray(Object[]::new);
	}
}
