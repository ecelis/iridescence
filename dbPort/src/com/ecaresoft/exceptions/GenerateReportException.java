/**
 * 
 */
package com.ecaresoft.exceptions;

/**
 * @author mrojas
 *
 */
public class GenerateReportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8395005503834629664L;

	/**
	 * 
	 */
	public GenerateReportException() {
		super();
	}

	/**
	 * @param message
	 */
	public GenerateReportException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public GenerateReportException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GenerateReportException(String message, Throwable cause) {
		super(message, cause);
	}

}
