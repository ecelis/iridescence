/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_Surgeries
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_Surgeries 
{

    /** TableName=PHR_Surgeries */
    public static final String Table_Name = "PHR_Surgeries";

    /** AD_Table_ID=1201010 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set Intervention.
	  * Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get Intervention.
	  * Intervention
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name PHR_Evento_ID */
    public static final String COLUMNNAME_PHR_Evento_ID = "PHR_Evento_ID";

	/** Set Patient Event	  */
	public void setPHR_Evento_ID (int PHR_Evento_ID);

	/** Get Patient Event	  */
	public int getPHR_Evento_ID();

	public I_PHR_Evento getPHR_Evento() throws RuntimeException;

    /** Column name PHR_Surgeries_ID */
    public static final String COLUMNNAME_PHR_Surgeries_ID = "PHR_Surgeries_ID";

	/** Set Medical Procedures & Surgeries	  */
	public void setPHR_Surgeries_ID (int PHR_Surgeries_ID);

	/** Get Medical Procedures & Surgeries	  */
	public int getPHR_Surgeries_ID();
}
