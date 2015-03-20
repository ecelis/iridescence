/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_LotCtl
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_LotCtl 
{

    /** TableName=M_LotCtl */
    public static final String Table_Name = "M_LotCtl";

    /** AD_Table_ID=556 */
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

    /** Column name CurrentNext */
    public static final String COLUMNNAME_CurrentNext = "CurrentNext";

	/** Set Current Next.
	  * The next number to be used
	  */
	public void setCurrentNext (int CurrentNext);

	/** Get Current Next.
	  * The next number to be used
	  */
	public int getCurrentNext();

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

    /** Column name IncrementNo */
    public static final String COLUMNNAME_IncrementNo = "IncrementNo";

	/** Set Increment.
	  * The number to increment the last document number by
	  */
	public void setIncrementNo (int IncrementNo);

	/** Get Increment.
	  * The number to increment the last document number by
	  */
	public int getIncrementNo();

    /** Column name M_LotCtl_ID */
    public static final String COLUMNNAME_M_LotCtl_ID = "M_LotCtl_ID";

	/** Set Lot Control.
	  * Product Lot Control
	  */
	public void setM_LotCtl_ID (int M_LotCtl_ID);

	/** Get Lot Control.
	  * Product Lot Control
	  */
	public int getM_LotCtl_ID();

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

    /** Column name Prefix */
    public static final String COLUMNNAME_Prefix = "Prefix";

	/** Set Prefix.
	  * Prefix before the sequence number
	  */
	public void setPrefix (String Prefix);

	/** Get Prefix.
	  * Prefix before the sequence number
	  */
	public String getPrefix();

    /** Column name StartNo */
    public static final String COLUMNNAME_StartNo = "StartNo";

	/** Set Start No.
	  * Starting number/position
	  */
	public void setStartNo (int StartNo);

	/** Get Start No.
	  * Starting number/position
	  */
	public int getStartNo();

    /** Column name Suffix */
    public static final String COLUMNNAME_Suffix = "Suffix";

	/** Set Suffix.
	  * Suffix after the number
	  */
	public void setSuffix (String Suffix);

	/** Get Suffix.
	  * Suffix after the number
	  */
	public String getSuffix();
}
