/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;



import java.io.Serializable;



/**

 * Modelo de campos obligatorios

 * <p>

 * Modificado por:  $Author: taniap $<p>

 * Fecha:  $Date: 2006/09/02 00:22:06 $<p>

 *

 * @author Hassan Reyes

 * @version $Revision: 1.1 $
@deprecated
 */

public class BaseModel implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**

	 * Propiedad ID.

	 */

	private long iD = 0;



	/**

	 * Obtener la propiedad iD.

	 * 

	 *@return La propiedad ID.

	 */

	public long getID() {

		return this.iD;

	}



	/**

	 * Establecer la propiedad iD.

	 * 

	 *@param iD Nueva propiedad iD.

	 */

	public void setID(long iD) {

		this.iD = iD;

	}



	/**

	  * Propiedad ADClientID.

	  */

	protected long aDClientID = 0;



	/**

	 * Obtener la propiedad aDClientID.

	 * 

	 *@return La propiedad ADClientID.

	 */

	public long getADClientID() {

		return this.aDClientID;

	}



	/**

	 * Establecer la propiedad aDClientID.

	 * 

	 *@param aDClientID Nueva propiedad aDClientID.

	 */

	public void setADClientID(long aDClientID) {

		this.aDClientID = aDClientID;

	}





	/**

	 * Propiedad ADOrgID.

	 */

	protected long aDOrgID = 0;



	/**

	 * Obtener la propiedad aDOrgID.

	 * 

	 *@return La propiedad ADOrgID.

	 */

	public long getADOrgID() {

		return this.aDOrgID;

	}



	/**

	 * Establecer la propiedad aDOrgID.

	 * 

	 *@param aDOrgID Nueva propiedad aDOrgID.

	 */

	public void setADOrgID(long aDOrgID) {

		this.aDOrgID = aDOrgID;

	}





	/**

	 * Propiedad IsActive.

	 */

	protected String isActive = null;



	/**

	 * Obtener la propiedad isActive.

	 * 

	 *@return La propiedad IsActive.

	 */

	public String getIsActive() {

		return this.isActive;

	}



	/**

	 * Establecer la propiedad isActive.

	 * 

	 *@param isActive Nueva propiedad isActive.

	 */

	public void setIsActive(String isActive) {

		this.isActive = isActive;

	}





	/**

	 * Propiedad Created.

	 */

	protected java.util.Date created = null;



	/**

	 * Obtener la propiedad created.

	 * 

	 *@return La propiedad Created.

	 */

	public java.util.Date getCreated() {

		return this.created;

	}



	/**

	 * Establecer la propiedad created.

	 * 

	 *@param created Nueva propiedad created.

	 */

	public void setCreated(java.util.Date created) {

		this.created = created;

	}





	/**

	 * Propiedad CreatedBy.

	 */

	protected long createdBy = 0;



	/**

	 * Obtener la propiedad createdBy.

	 * 

	 *@return La propiedad CreatedBy.

	 */

	public long getCreatedBy() {

		return this.createdBy;

	}



	/**

	 * Establecer la propiedad createdBy.

	 * 

	 *@param createdBy Nueva propiedad createdBy.

	 */

	public void setCreatedBy(long createdBy) {

		this.createdBy = createdBy;

	}





	/**

	 * Propiedad Updated.

	 */

	protected java.util.Date updated = null;



	/**

	 * Obtener la propiedad updated.

	 * 

	 *@return La propiedad Updated.

	 */

	public java.util.Date getUpdated() {

		return this.updated;

	}



	/**

	 * Establecer la propiedad updated.

	 * 

	 *@param updated Nueva propiedad updated.

	 */

	public void setUpdated(java.util.Date updated) {

		this.updated = updated;

	}





	/**

	 * Propiedad UpdatedBy.

	 */

	protected long updatedBy = 0;



	/**

	 * Obtener la propiedad updatedBy.

	 * 

	 *@return La propiedad UpdatedBy.

	 */

	public long getUpdatedBy() {

		return this.updatedBy;

	}



	/**

	 * Establecer la propiedad updatedBy.

	 * 

	 *@param updatedBy Nueva propiedad updatedBy.

	 */

	public void setUpdatedBy(long updatedBy) {

		this.updatedBy = updatedBy;

	}





	/**

	 * Propiedad Value.

	 */

	protected String value = null;



	/**

	 * Obtener la propiedad value.

	 * 

	 *@return La propiedad Value.

	 */

	public String getValue() {

		return this.value;

	}



	/**

	 * Establecer la propiedad value.

	 * 

	 *@param value Nueva propiedad value.

	 */

	public void setValue(String value) {

		this.value = value;

	}





	/**

	 * Propiedad Name.

	 */

	protected String name = null;



	/**

	 * Obtener la propiedad name.

	 * 

	 *@return La propiedad Name.

	 */

	public String getName() {

		return this.name;

	}



	/**

	 * Establecer la propiedad name.

	 * 

	 *@param name Nueva propiedad name.

	 */

	public void setName(String name) {

		this.name = name;

	}





	/**

	 * Propiedad Description.

	 */

	protected String description = null;



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



}

