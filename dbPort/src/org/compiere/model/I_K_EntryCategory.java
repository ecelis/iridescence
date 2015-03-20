/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for K_EntryCategory
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_K_EntryCategory 
{

    /** TableName=K_EntryCategory */
    public static final String Table_Name = "K_EntryCategory";

    /** AD_Table_ID=611 */
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

    /** Column name K_Category_ID */
    public static final String COLUMNNAME_K_Category_ID = "K_Category_ID";

	/** Set Knowledge Category.
	  * Knowledge Category
	  */
	public void setK_Category_ID (int K_Category_ID);

	/** Get Knowledge Category.
	  * Knowledge Category
	  */
	public int getK_Category_ID();

	public I_K_Category getK_Category() throws RuntimeException;

    /** Column name K_CategoryValue_ID */
    public static final String COLUMNNAME_K_CategoryValue_ID = "K_CategoryValue_ID";

	/** Set Category Value.
	  * The value of the category
	  */
	public void setK_CategoryValue_ID (int K_CategoryValue_ID);

	/** Get Category Value.
	  * The value of the category
	  */
	public int getK_CategoryValue_ID();

	public I_K_CategoryValue getK_CategoryValue() throws RuntimeException;

    /** Column name K_Entry_ID */
    public static final String COLUMNNAME_K_Entry_ID = "K_Entry_ID";

	/** Set Entry.
	  * Knowledge Entry
	  */
	public void setK_Entry_ID (int K_Entry_ID);

	/** Get Entry.
	  * Knowledge Entry
	  */
	public int getK_Entry_ID();

	public I_K_Entry getK_Entry() throws RuntimeException;
}
