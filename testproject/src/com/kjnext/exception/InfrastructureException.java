package com.kjnext.exception;

public class InfrastructureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InfrastructureException() {

	}

	public InfrastructureException(String message) {
		super(message);
	}

	public InfrastructureException(Throwable cause) {
		super(cause);
	}

	public InfrastructureException(String message, Throwable cause) {
		super(message, cause);
	}

}
