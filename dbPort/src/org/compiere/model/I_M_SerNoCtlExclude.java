/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_SerNoCtlExclude
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_SerNoCtlExclude 
{

    /** TableName=M_SerNoCtlExclude */
    public static final String Table_Name = "M_SerNoCtlExclude";

    /** AD_Table_ID=811 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name M_SerNoCtlExclude_ID */
    public static final String COLUMNNAME_M_SerNoCtlExclude_ID = "M_SerNoCtlExclude_ID";

	/** Set Exclude SerNo.
	  * Exclude the ability to create Serial Numbers in Attribute Sets
	  */
	public void setM_SerNoCtlExclude_ID (int M_SerNoCtlExclude_ID);

	/** Get Exclude SerNo.
	  * Exclude the ability to create Serial Numbers in Attribute Sets
	  */
	public int getM_SerNoCtlExclude_ID();

    /** Column name M_SerNoCtl_ID */
    public static final String COLUMNNAME_M_SerNoCtl_ID = "M_SerNoCtl_ID";

	/** Set Serial No Control.
	  * Product Serial Number Control
	  */
	public void setM_SerNoCtl_ID (int M_SerNoCtl_ID);

	/** Get Serial No Control.
	  * Product Serial Number Control
	  */
	public int getM_SerNoCtl_ID();

	public I_M_SerNoCtl getM_SerNoCtl() throws RuntimeException;
}
