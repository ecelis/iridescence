/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.io.Serializable;


/**
 * Modelo que representa un movimiento pendiente de confirmar
 * <p>
 * Modificado por: $Author: mrojas $ <p>
 * Fecha: $Date: 2006/09/14 21:35:39 $ <p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.1 $
*/
public class ConfirmaView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Propiedad MovementConfirmID.
	 */
	private long movementConfirmID = 0;

	/**
	 * Obtener la propiedad movementConfirmID.
	 * 
	 *@return La propiedad MovementConfirmID.
	 */
	public long getMovementConfirmID() {
		return this.movementConfirmID;
	}

	/**
	 * Establecer la propiedad movementConfirmID.
	 * 
	 *@param movementConfirmID Nueva propiedad movementConfirmID.
	 */
	public void setMovementConfirmID(long movementConfirmID) {
		this.movementConfirmID = movementConfirmID;
	}

	/**
	 * Propiedad DocumentNo.
	 */
	private String documentNo = null;

	/**
	 * Obtener la propiedad documentNo.
	 * 
	 *@return La propiedad DocumentNo.
	 */
	public String getDocumentNo() {
		return this.documentNo;
	}

	/**
	 * Establecer la propiedad documentNo.
	 * 
	 *@param documentNo Nueva propiedad documentNo.
	 */
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	/**
	 * Propiedad DocNoMovement.
	 */
	private String docNoMovement = null;

	/**
	 * Obtener la propiedad docNoMovement.
	 * 
	 *@return La propiedad DocNoMovement.
	 */
	public String getDocNoMovement() {
		return this.docNoMovement;
	}

	/**
	 * Establecer la propiedad docNoMovement.
	 * 
	 *@param docNoMovement Nueva propiedad docNoMovement.
	 */
	public void setDocNoMovement(String docNoMovement) {
		this.docNoMovement = docNoMovement;
	}

	/**
	* Propiedad Description.
	*/
	private String description = null;

	/**
	 * Obtener la propiedad description.
	 * 
	 *@return La propiedad Description.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Establecer la propiedad description.
	 * 
	 *@param description Nueva propiedad description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Propiedad Locator.
	 */
	private String locator = null;

	/**
	 * Obtener la propiedad locator.
	 * 
	 *@return La propiedad Locator.
	 */
	public String getLocator() {
		return this.locator;
	}

	/**
	 * Establecer la propiedad locator.
	 * 
	 *@param locator Nueva propiedad locator.
	 */
	public void setLocator(String locator) {
		this.locator = locator;
	}

	/**
	 * Propiedad User.
	 */
	private String user = null;

	/**
	 * Obtener la propiedad user.
	 * 
	 *@return La propiedad User.
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * Establecer la propiedad user.
	 * 
	 *@param user Nueva propiedad user.
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/**
	 * Propiedad MovementID.
	 */
	private long MovementID = 0;

	/**
	 * Obtener la propiedad MovementID.
	 * 
	 *@return La propiedad MovementID.
	 */
	public long getMovementID() {
		return this.MovementID;
	}

	/**
	 * Establecer la propiedad MovementID.
	 * 
	 *@param MovementID Nueva propiedad MovementID.
	 */
	public void setMovementID(long MovementID) {
		this.MovementID = MovementID;
	}
	private String userSol = null;
	private String userSurte = null;



	public String getUserSol() {
		return userSol;
	}



	public void setUserSol(String userSol) {
		this.userSol = userSol;
	}



	public String getUserSurte() {
		return userSurte;
	}



	public void setUserSurte(String userSurte) {
		this.userSurte = userSurte;
	}

}
