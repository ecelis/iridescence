/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProcedureTypeDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ProcedureTypeDet 
{

    /** TableName=EXME_ProcedureTypeDet */
    public static final String Table_Name = "EXME_ProcedureTypeDet";

    /** AD_Table_ID=1201100 */
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

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set CPT Code.
	  * Current Procedural Terminology (CPT) 
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get CPT Code.
	  * Current Procedural Terminology (CPT) 
	  */
	public int getEXME_Intervencion_ID();

    /** Column name EXME_ProceduresVersion_ID */
    public static final String COLUMNNAME_EXME_ProceduresVersion_ID = "EXME_ProceduresVersion_ID";

	/** Set Procedures Version	  */
	public void setEXME_ProceduresVersion_ID (int EXME_ProceduresVersion_ID);

	/** Get Procedures Version	  */
	public int getEXME_ProceduresVersion_ID();

    /** Column name EXME_ProcedureTypeDet_ID */
    public static final String COLUMNNAME_EXME_ProcedureTypeDet_ID = "EXME_ProcedureTypeDet_ID";

	/** Set Procedure Type Detail	  */
	public void setEXME_ProcedureTypeDet_ID (int EXME_ProcedureTypeDet_ID);

	/** Get Procedure Type Detail	  */
	public int getEXME_ProcedureTypeDet_ID();

    /** Column name EXME_ProcedureType_ID */
    public static final String COLUMNNAME_EXME_ProcedureType_ID = "EXME_ProcedureType_ID";

	/** Set Procedure Type	  */
	public void setEXME_ProcedureType_ID (int EXME_ProcedureType_ID);

	/** Get Procedure Type	  */
	public int getEXME_ProcedureType_ID();

	public I_EXME_ProcedureType getEXME_ProcedureType() throws RuntimeException;
}
