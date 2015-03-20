package org.compiere.util;

/**
 * @author odelarosa
 * 
 */
public class ExpertException extends Exception {

	private static final long serialVersionUID = 5170392410451267402L;

	/**
	 * 
	 */
	public ExpertException() {
		super();
	}

	/**
	 * @param message
	 */
	public ExpertException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ExpertException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ExpertException(Throwable cause) {
		super(cause);
	}

	/**
	 * No imprimir en stacktrace para mayor performace cuando se controla el flujo por medio de excepciones
	 */
	@Override
	public synchronized Throwable fillInStackTrace(){
		return this;
	}
}
