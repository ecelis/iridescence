/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for K_EntryRelated
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_K_EntryRelated 
{

    /** TableName=K_EntryRelated */
    public static final String Table_Name = "K_EntryRelated";

    /** AD_Table_ID=610 */
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

    /** Column name K_EntryRelated_ID */
    public static final String COLUMNNAME_K_EntryRelated_ID = "K_EntryRelated_ID";

	/** Set Related Entry.
	  * Related Entry for this Enntry
	  */
	public void setK_EntryRelated_ID (int K_EntryRelated_ID);

	/** Get Related Entry.
	  * Related Entry for this Enntry
	  */
	public int getK_EntryRelated_ID();

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
}
