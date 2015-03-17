/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ReferVal
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ReferVal 
{

    /** TableName=EXME_ReferVal */
    public static final String Table_Name = "EXME_ReferVal";

    /** AD_Table_ID=1000139 */
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

    /** Column name AD_OrgRec_ID */
    public static final String COLUMNNAME_AD_OrgRec_ID = "AD_OrgRec_ID";

	/** Set Receiving Organization.
	  * Receiving Organization for Referenced Patient
	  */
	public void setAD_OrgRec_ID (int AD_OrgRec_ID);

	/** Get Receiving Organization.
	  * Receiving Organization for Referenced Patient
	  */
	public int getAD_OrgRec_ID();

    /** Column name EXME_ReferVal_ID */
    public static final String COLUMNNAME_EXME_ReferVal_ID = "EXME_ReferVal_ID";

	/** Set Valid References.
	  * Valid References
	  */
	public void setEXME_ReferVal_ID (int EXME_ReferVal_ID);

	/** Get Valid References.
	  * Valid References
	  */
	public int getEXME_ReferVal_ID();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();

    /** Column name TipoAreaRec */
    public static final String COLUMNNAME_TipoAreaRec = "TipoAreaRec";

	/** Set Receiving Area.
	  * Type of receiving area
	  */
	public void setTipoAreaRec (String TipoAreaRec);

	/** Get Receiving Area.
	  * Type of receiving area
	  */
	public String getTipoAreaRec();
}
