/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AlertaPac
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_AlertaPac 
{

    /** TableName=EXME_AlertaPac */
    public static final String Table_Name = "EXME_AlertaPac";

    /** AD_Table_ID=1201284 */
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

    /** Column name EXME_Alerta_ID */
    public static final String COLUMNNAME_EXME_Alerta_ID = "EXME_Alerta_ID";

	/** Set EXME_Alerta_ID	  */
	public void setEXME_Alerta_ID (int EXME_Alerta_ID);

	/** Get EXME_Alerta_ID	  */
	public int getEXME_Alerta_ID();

	public I_EXME_Alerta getEXME_Alerta() throws RuntimeException;

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

    /** Column name Frequency */
    public static final String COLUMNNAME_Frequency = "Frequency";

	/** Set Frequency.
	  * Frequency of events
	  */
	public void setFrequency (BigDecimal Frequency);

	/** Get Frequency.
	  * Frequency of events
	  */
	public BigDecimal getFrequency();
}
