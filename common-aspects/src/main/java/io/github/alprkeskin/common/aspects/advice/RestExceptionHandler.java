package io.github.alprkeskin.common.aspects.advice;


import static io.github.alprkeskin.common.model.response.result.ResultEntities.FAIL;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

import javax.servlet.http.HttpServletRequest;

import io.github.alprkeskin.common.exception.CustomException;
import io.github.alprkeskin.common.model.response.result.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	@ExceptionHandler({ CustomException.class })
	public ResponseEntity<ResultEntity> handleServiceException(HttpServletRequest req, CustomException e) {
		ResultEntity resultEntity = FAIL.getResultEntity();

		resultEntity.setMessage(e.getResponseMessage());

		return status(e.getHttpStatus()).body(resultEntity);
	}

	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<?> handleRespositoryException(HttpServletRequest req, Throwable e) {
		logger.error(e.getMessage(), e.getCause());

		return status(INTERNAL_SERVER_ERROR).body(FAIL.getResultEntity());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResultEntity> handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException e) {
		Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

		String message = "RestController was called with unvalid request body. :: requestUrl={}";
		logger.error(message, req.getRequestURI(), e);

		return status(BAD_REQUEST).body(FAIL.getResultEntity());
	}
}
