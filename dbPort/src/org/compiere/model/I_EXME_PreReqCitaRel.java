/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PreReqCitaRel
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PreReqCitaRel 
{

    /** TableName=EXME_PreReqCitaRel */
    public static final String Table_Name = "EXME_PreReqCitaRel";

    /** AD_Table_ID=1200310 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXME_CitaMedica_ID */
    public static final String COLUMNNAME_EXME_CitaMedica_ID = "EXME_CitaMedica_ID";

	/** Set Medical Appointment.
	  * Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID);

	/** Get Medical Appointment.
	  * Medical appointment
	  */
	public int getEXME_CitaMedica_ID();

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException;

    /** Column name EXME_PreReqCita_ID */
    public static final String COLUMNNAME_EXME_PreReqCita_ID = "EXME_PreReqCita_ID";

	/** Set Medical Consultation's Prerequisites Catalog	  */
	public void setEXME_PreReqCita_ID (int EXME_PreReqCita_ID);

	/** Get Medical Consultation's Prerequisites Catalog	  */
	public int getEXME_PreReqCita_ID();

	public I_EXME_PreReqCita getEXME_PreReqCita() throws RuntimeException;

    /** Column name EXME_PreReqCitaRel_ID */
    public static final String COLUMNNAME_EXME_PreReqCitaRel_ID = "EXME_PreReqCitaRel_ID";

	/** Set Medical Consultation's Prerequistes	  */
	public void setEXME_PreReqCitaRel_ID (int EXME_PreReqCitaRel_ID);

	/** Get Medical Consultation's Prerequistes	  */
	public int getEXME_PreReqCitaRel_ID();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
