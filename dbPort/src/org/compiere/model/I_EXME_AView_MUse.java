/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AView_MUse
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_AView_MUse 
{

    /** TableName=EXME_AView_MUse */
    public static final String Table_Name = "EXME_AView_MUse";

    /** AD_Table_ID=1201208 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name EXME_AView_MUse_ID */
    public static final String COLUMNNAME_EXME_AView_MUse_ID = "EXME_AView_MUse_ID";

	/** Set EXME_AView_MUse_ID	  */
	public void setEXME_AView_MUse_ID (int EXME_AView_MUse_ID);

	/** Get EXME_AView_MUse_ID	  */
	public int getEXME_AView_MUse_ID();

    /** Column name EXME_FView_MUse_ID */
    public static final String COLUMNNAME_EXME_FView_MUse_ID = "EXME_FView_MUse_ID";

	/** Set EXME_FView_MUse_ID	  */
	public void setEXME_FView_MUse_ID (int EXME_FView_MUse_ID);

	/** Get EXME_FView_MUse_ID	  */
	public int getEXME_FView_MUse_ID();

	public I_EXME_FView_MUse getEXME_FView_MUse() throws RuntimeException;

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

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();
}
