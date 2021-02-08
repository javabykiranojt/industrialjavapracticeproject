package com.kjnext.exception;

public class DataAccessException extends Exception {
	
	private static final long serialVersionUID = 201L;
	
	public DataAccessException() {
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
