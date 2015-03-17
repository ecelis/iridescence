/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProgramaInvLine
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ProgramaInvLine 
{

    /** TableName=EXME_ProgramaInvLine */
    public static final String Table_Name = "EXME_ProgramaInvLine";

    /** AD_Table_ID=1200326 */
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

    /** Column name EXME_ProgramaInv_ID */
    public static final String COLUMNNAME_EXME_ProgramaInv_ID = "EXME_ProgramaInv_ID";

	/** Set Research Program	  */
	public void setEXME_ProgramaInv_ID (int EXME_ProgramaInv_ID);

	/** Get Research Program	  */
	public int getEXME_ProgramaInv_ID();

	public I_EXME_ProgramaInv getEXME_ProgramaInv() throws RuntimeException;

    /** Column name EXME_ProgramaInvLine_ID */
    public static final String COLUMNNAME_EXME_ProgramaInvLine_ID = "EXME_ProgramaInvLine_ID";

	/** Set Research Program Detail	  */
	public void setEXME_ProgramaInvLine_ID (int EXME_ProgramaInvLine_ID);

	/** Get Research Program Detail	  */
	public int getEXME_ProgramaInvLine_ID();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();
}
