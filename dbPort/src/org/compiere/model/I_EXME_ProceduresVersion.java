/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProceduresVersion
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ProceduresVersion 
{

    /** TableName=EXME_ProceduresVersion */
    public static final String Table_Name = "EXME_ProceduresVersion";

    /** AD_Table_ID=1201381 */
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

    /** Column name EXME_ProceduresVersion_ID */
    public static final String COLUMNNAME_EXME_ProceduresVersion_ID = "EXME_ProceduresVersion_ID";

	/** Set Procedures Version	  */
	public void setEXME_ProceduresVersion_ID (int EXME_ProceduresVersion_ID);

	/** Get Procedures Version	  */
	public int getEXME_ProceduresVersion_ID();

    /** Column name EXME_ProcedureType_ID */
    public static final String COLUMNNAME_EXME_ProcedureType_ID = "EXME_ProcedureType_ID";

	/** Set Procedure Type	  */
	public void setEXME_ProcedureType_ID (int EXME_ProcedureType_ID);

	/** Get Procedure Type	  */
	public int getEXME_ProcedureType_ID();

	public I_EXME_ProcedureType getEXME_ProcedureType() throws RuntimeException;

    /** Column name Stage */
    public static final String COLUMNNAME_Stage = "Stage";

	/** Set Stage	  */
	public void setStage (String Stage);

	/** Get Stage	  */
	public String getStage();
}
