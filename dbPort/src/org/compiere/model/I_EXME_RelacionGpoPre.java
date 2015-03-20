/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RelacionGpoPre
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RelacionGpoPre 
{

    /** TableName=EXME_RelacionGpoPre */
    public static final String Table_Name = "EXME_RelacionGpoPre";

    /** AD_Table_ID=1200194 */
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
