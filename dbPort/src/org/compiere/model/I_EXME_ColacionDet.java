/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ColacionDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ColacionDet 
{

    /** TableName=EXME_ColacionDet */
    public static final String Table_Name = "EXME_ColacionDet";

    /** AD_Table_ID=1200593 */
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

    /** Column name EXME_ColacionDet_ID */
    public static final String COLUMNNAME_EXME_ColacionDet_ID = "EXME_ColacionDet_ID";

	/** Set Snack Detail	  */
	public void setEXME_ColacionDet_ID (int EXME_ColacionDet_ID);

	/** Get Snack Detail	  */
	public int getEXME_ColacionDet_ID();

    /** Column name EXME_Colacion_ID */
    public static final String COLUMNNAME_EXME_Colacion_ID = "EXME_Colacion_ID";

	/** Set Snack	  */
	public void setEXME_Colacion_ID (int EXME_Colacion_ID);

	/** Get Snack	  */
	public int getEXME_Colacion_ID();

	public I_EXME_Colacion getEXME_Colacion() throws RuntimeException;

    /** Column name EXME_PlatilloHdr_ID */
    public static final String COLUMNNAME_EXME_PlatilloHdr_ID = "EXME_PlatilloHdr_ID";

	/** Set Saucer	  */
	public void setEXME_PlatilloHdr_ID (int EXME_PlatilloHdr_ID);

	/** Get Saucer	  */
	public int getEXME_PlatilloHdr_ID();

	public I_EXME_PlatilloHdr getEXME_PlatilloHdr() throws RuntimeException;

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
