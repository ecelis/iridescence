/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MedicoAsist
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MedicoAsist 
{

    /** TableName=EXME_MedicoAsist */
    public static final String Table_Name = "EXME_MedicoAsist";

    /** AD_Table_ID=1000085 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name EXME_Asistente_ID */
    public static final String COLUMNNAME_EXME_Asistente_ID = "EXME_Asistente_ID";

	/** Set Assistant.
	  * Assistant
	  */
	public void setEXME_Asistente_ID (int EXME_Asistente_ID);

	/** Get Assistant.
	  * Assistant
	  */
	public int getEXME_Asistente_ID();

    /** Column name EXME_MedicoAsist_ID */
    public static final String COLUMNNAME_EXME_MedicoAsist_ID = "EXME_MedicoAsist_ID";

	/** Set Assistant Doctor.
	  * Assistant Doctor
	  */
	public void setEXME_MedicoAsist_ID (int EXME_MedicoAsist_ID);

	/** Get Assistant Doctor.
	  * Assistant Doctor
	  */
	public int getEXME_MedicoAsist_ID();

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();
}
