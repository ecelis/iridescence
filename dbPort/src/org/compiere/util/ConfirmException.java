package org.compiere.util;

/**
 * Exception para manejo de ventanas de confirmacion
 * @author odelarosa
 *
 */
public class ConfirmException extends IllegalArgumentException {

	private static final long serialVersionUID = -7305421382275279348L;

	/**
	 * Constructor default
	 * @param message
	 */
	public ConfirmException(String message) {
		super(message);
	}

}
