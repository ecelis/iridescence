package com.ecaresoft.util;

/**
 * 
 * @author odelarosa
 * 
 */
public class EcsException extends RuntimeException {

	private static final long serialVersionUID = 2672909249436733211L;

	public EcsException() {
	}

	public EcsException(String message) {
		super(message);
	}

	public EcsException(Throwable cause) {
		super(cause);
	}

	public EcsException(String message, Throwable cause) {
		super(message, cause);
	}

}
