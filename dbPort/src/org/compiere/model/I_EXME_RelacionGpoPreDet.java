/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RelacionGpoPreDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RelacionGpoPreDet 
{

    /** TableName=EXME_RelacionGpoPreDet */
    public static final String Table_Name = "EXME_RelacionGpoPreDet";

    /** AD_Table_ID=1200195 */
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

    /** Column name EXME_RelacionGpoPreDet_ID */
    public static final String COLUMNNAME_EXME_RelacionGpoPreDet_ID = "EXME_RelacionGpoPreDet_ID";

	/** Set Price Group Relation Detail.
	  * Price Group Relation Detail
	  */
	public void setEXME_RelacionGpoPreDet_ID (int EXME_RelacionGpoPreDet_ID);

	/** Get Price Group Relation Detail.
	  * Price Group Relation Detail
	  */
	public int getEXME_RelacionGpoPreDet_ID();

    /** Column name EXME_RelacionGpoPre_ID */
    public static final String COLUMNNAME_EXME_RelacionGpoPre_ID = "EXME_RelacionGpoPre_ID";

	/** Set Price Group Relation.
	  * Price Group Relation
	  */
	public void setEXME_RelacionGpoPre_ID (int EXME_RelacionGpoPre_ID);

	/** Get Price Group Relation.
	  * Price Group Relation
	  */
	public int getEXME_RelacionGpoPre_ID();

    /** Column name LineNo */
    public static final String COLUMNNAME_LineNo = "LineNo";

	/** Set Line.
	  * Line No
	  */
	public void setLineNo (int LineNo);

	/** Get Line.
	  * Line No
	  */
	public int getLineNo();

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
