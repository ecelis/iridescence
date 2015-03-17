/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PaqBaseAtributo
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_PaqBaseAtributo 
{

    /** TableName=EXME_PaqBaseAtributo */
    public static final String Table_Name = "EXME_PaqBaseAtributo";

    /** AD_Table_ID=1201164 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name EXME_PaqBaseAtributo_ID */
    public static final String COLUMNNAME_EXME_PaqBaseAtributo_ID = "EXME_PaqBaseAtributo_ID";

	/** Set Package attributes	  */
	public void setEXME_PaqBaseAtributo_ID (int EXME_PaqBaseAtributo_ID);

	/** Get Package attributes	  */
	public int getEXME_PaqBaseAtributo_ID();

    /** Column name EXME_PaqBaseDet_ID */
    public static final String COLUMNNAME_EXME_PaqBaseDet_ID = "EXME_PaqBaseDet_ID";

	/** Set Detail of Base Package.
	  * Detail of Base Package
	  */
	public void setEXME_PaqBaseDet_ID (int EXME_PaqBaseDet_ID);

	/** Get Detail of Base Package.
	  * Detail of Base Package
	  */
	public int getEXME_PaqBaseDet_ID();

	public I_EXME_PaqBaseDet getEXME_PaqBaseDet() throws RuntimeException;

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (BigDecimal Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public BigDecimal getLine();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();
}
