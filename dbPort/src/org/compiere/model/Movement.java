package org.compiere.model;


import java.util.Date;

/**
 * Modelo que representa una movimiento
 * <p>
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2005/12/15 18:14:06 $ <p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.1 $
*/
public class Movement{

	/**
	 * Propiedad MovementID.
	 */
	private long movementID = 0;

	/**
	 * Obtener la propiedad movementID.
	 * 
	 *@return La propiedad MovementID.
	 */
	public long getMovementID() {
		return this.movementID;
	}

	/**
	 * Establecer la propiedad movementID.
	 * 
	 *@param movementID Nueva propiedad movementID.
	 */
	public void setMovementID(long movementID) {
		this.movementID = movementID;
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
	 * Propiedad MovementDate.
	 */
	private Date movementDate = null;

	/**
	 * Obtener la propiedad movementDate.
	 * 
	 *@return La propiedad MovementDate.
	 */
	public Date getMovementDate() {
		return this.movementDate;
	}

	/**
	 * Establecer la propiedad movementDate.
	 * 
	 *@param movementDate Nueva propiedad movementDate.
	 */
	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
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
	* Propiedad LocatorTo.
	*/
	private String locatorTo = null;

	/**
	 * Obtener la propiedad locatorTo.
	 * 
	 *@return La propiedad LocatorTo.
	 */
	public String getLocatorTo() {
		return this.locatorTo;
	}

	/**
	 * Establecer la propiedad locatorTo.
	 * 
	 *@param locatorTo Nueva propiedad locatorTo.
	 */
	public void setLocatorTo(String locatorTo) {
		this.locatorTo = locatorTo;
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
	 * Propiedad DocStatus.
	 */
	private String docStatus = null;

	/**
	 * Obtener la propiedad docStatus.
	 * 
	 *@return La propiedad DocStatus.
	 */
	public String getDocStatus() {
		return this.docStatus;
	}

	/**
	 * Establecer la propiedad docStatus.
	 * 
	 *@param docStatus Nueva propiedad docStatus.
	 */
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	/**
	 * Propiedad EstacionID.
	 */
	private long estacionID = 0;

	/**
	 * Obtener la propiedad estacionID.
	 * 
	 *@return La propiedad EstacionID.
	 */
	public long getEstacionID() {
		return this.estacionID;
	}

	/**
	 * Establecer la propiedad estacionID.
	 * 
	 *@param estacionID Nueva propiedad estacionID.
	 */
	public void setEstacionID(long estacionID) {
		this.estacionID = estacionID;
	}

	/**
	 * Propiedad CtaPac.
	 */
	private String ctaPac = null;

	/**
	 * Obtener la propiedad ctaPac.
	 * 
	 *@return La propiedad CtaPac.
	 */
	public String getCtaPac() {
		return this.ctaPac;
	}

	/**
	 * Establecer la propiedad ctaPac.
	 * 
	 *@param ctaPac Nueva propiedad ctaPac.
	 */
	public void setCtaPac(String ctaPac) {
		this.ctaPac = ctaPac;
	}

}
