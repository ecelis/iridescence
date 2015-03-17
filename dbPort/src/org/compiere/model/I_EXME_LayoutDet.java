/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LayoutDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_LayoutDet 
{

    /** TableName=EXME_LayoutDet */
    public static final String Table_Name = "EXME_LayoutDet";

    /** AD_Table_ID=1200074 */
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

    /** Column name EXME_LayoutDet_ID */
    public static final String COLUMNNAME_EXME_LayoutDet_ID = "EXME_LayoutDet_ID";

	/** Set Layout Detail.
	  * Layout Detail
	  */
	public void setEXME_LayoutDet_ID (int EXME_LayoutDet_ID);

	/** Get Layout Detail.
	  * Layout Detail
	  */
	public int getEXME_LayoutDet_ID();

    /** Column name EXME_Layout_ID */
    public static final String COLUMNNAME_EXME_Layout_ID = "EXME_Layout_ID";

	/** Set Layout.
	  * Layout
	  */
	public void setEXME_Layout_ID (int EXME_Layout_ID);

	/** Get Layout.
	  * Layout
	  */
	public int getEXME_Layout_ID();

	public I_EXME_Layout getEXME_Layout() throws RuntimeException;

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

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public I_M_Product_Category getM_Product_Category() throws RuntimeException;

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
