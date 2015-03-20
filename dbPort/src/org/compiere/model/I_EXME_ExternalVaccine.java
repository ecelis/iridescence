/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ExternalVaccine
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ExternalVaccine 
{

    /** TableName=EXME_ExternalVaccine */
    public static final String Table_Name = "EXME_ExternalVaccine";

    /** AD_Table_ID=1201281 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name EXME_ExternalVaccine_ID */
    public static final String COLUMNNAME_EXME_ExternalVaccine_ID = "EXME_ExternalVaccine_ID";

	/** Set EXME_ExternalVaccine_ID	  */
	public void setEXME_ExternalVaccine_ID (int EXME_ExternalVaccine_ID);

	/** Get EXME_ExternalVaccine_ID	  */
	public int getEXME_ExternalVaccine_ID();

    /** Column name EXME_Vacuna_ID */
    public static final String COLUMNNAME_EXME_Vacuna_ID = "EXME_Vacuna_ID";

	/** Set Vaccine.
	  * Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID);

	/** Get Vaccine.
	  * Vaccine
	  */
	public int getEXME_Vacuna_ID();

	public I_EXME_Vacuna getEXME_Vacuna() throws RuntimeException;

    /** Column name FechaAplica */
    public static final String COLUMNNAME_FechaAplica = "FechaAplica";

	/** Set Date of Application.
	  * Date of Application
	  */
	public void setFechaAplica (Timestamp FechaAplica);

	/** Get Date of Application.
	  * Date of Application
	  */
	public Timestamp getFechaAplica();

    /** Column name IsClosed */
    public static final String COLUMNNAME_IsClosed = "IsClosed";

	/** Set Closed Status.
	  * The status is closed
	  */
	public void setIsClosed (boolean IsClosed);

	/** Get Closed Status.
	  * The status is closed
	  */
	public boolean isClosed();

    /** Column name Location */
    public static final String COLUMNNAME_Location = "Location";

	/** Set Location	  */
	public void setLocation (String Location);

	/** Get Location	  */
	public String getLocation();
}
