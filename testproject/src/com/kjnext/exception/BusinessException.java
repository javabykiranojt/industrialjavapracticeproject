package com.kjnext.exception;

import org.apache.log4j.Logger;

public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 202L;
	private static Logger logger = Logger.getLogger(BusinessException.class);

	public BusinessException() {
		
	}

	public BusinessException(String message) {
		super(message);
		logger.error(message, this);
	}

	public BusinessException(Throwable cause) {
		super(cause);
		logger.error(cause.getMessage(), this);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message, this);
	}

}
