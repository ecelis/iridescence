/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Level_Of_Service
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Level_Of_Service 
{

    /** TableName=EXME_Level_Of_Service */
    public static final String Table_Name = "EXME_Level_Of_Service";

    /** AD_Table_ID=1201325 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name EXME_Level_Of_Service_ID */
    public static final String COLUMNNAME_EXME_Level_Of_Service_ID = "EXME_Level_Of_Service_ID";

	/** Set Level of service.
	  * level of service
	  */
	public void setEXME_Level_Of_Service_ID (int EXME_Level_Of_Service_ID);

	/** Get Level of service.
	  * level of service
	  */
	public int getEXME_Level_Of_Service_ID();

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

    /** Column name Patient_Type */
    public static final String COLUMNNAME_Patient_Type = "Patient_Type";

	/** Set Patient Type	  */
	public void setPatient_Type (String Patient_Type);

	/** Get Patient Type	  */
	public String getPatient_Type();
}
