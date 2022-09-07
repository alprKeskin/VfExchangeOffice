package io.github.alprkeskin.common.aspects.util;

import org.aspectj.lang.annotation.Pointcut;

public final class PointcutUtil {

	private PointcutUtil() {

	}

	@Pointcut("within(@org.aspectj.lang.annotation.Aspect *) " +
			"|| within(@org.springframework.web.bind.annotation.ControllerAdvice *) " +
			"|| within(@org.springframework.web.bind.annotation.RestControllerAdvice *)")
	private static void aspectAnnotation() {
	}

	@Pointcut("aspectAnnotation() && !within(io.github.alprkeskin.common.aspects..*)")
	public static void aspectPointcut() {
	}

	@Pointcut("within(io.github.alprkeskin..*) && aspectPointcut()")
	private static void basePackagePointcut(){
	}

	@Pointcut("within(@org.springframework.boot.context.properties.ConfigurationProperties *) " +
			"|| within(@org.springframework.context.annotation.Configuration *)")
	private static void configAnnotation() {
	}

	@Pointcut("configAnnotation()")
	public static void configPointcut() {
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	private static void repositoryAnnotation() {
	}

	@Pointcut("repositoryAnnotation()")
	public static void repositoryPointcut() {
	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	private static void restControllerAnnotation() {
	}

	@Pointcut("restControllerAnnotation()")
	public static void restControllerPointcut() {
	}

	@Pointcut("within(@org.springframework.stereotype.Service *)")
	private static void serviceAnnotation() {
	}

	@Pointcut("serviceAnnotation()")
	public static void servicePointcut() {
	}


	@Pointcut("basePackagePointcut() && !configPointcut()")
	public static void logInfoPointcut() {
	}

	@Pointcut("basePackagePointcut() && !restControllerPointcut() && !servicePointcut() && !repositoryPointcut()")
	public static void logErrorPointcut() {
	}
}
