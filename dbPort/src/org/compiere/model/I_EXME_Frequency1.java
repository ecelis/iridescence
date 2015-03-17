/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Frequency1
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Frequency1 
{

    /** TableName=EXME_Frequency1 */
    public static final String Table_Name = "EXME_Frequency1";

    /** AD_Table_ID=1201130 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name AutoCalculate */
    public static final String COLUMNNAME_AutoCalculate = "AutoCalculate";

	/** Set Auto Calculate.
	  * Auto Calculate Times and Frequencies
	  */
	public void setAutoCalculate (boolean AutoCalculate);

	/** Get Auto Calculate.
	  * Auto Calculate Times and Frequencies
	  */
	public boolean isAutoCalculate();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name EXME_Frequency1_ID */
    public static final String COLUMNNAME_EXME_Frequency1_ID = "EXME_Frequency1_ID";

	/** Set Frequency 1.
	  * Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID);

	/** Get Frequency 1.
	  * Frequency Header ID
	  */
	public int getEXME_Frequency1_ID();

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

    /** Column name Quantity */
    public static final String COLUMNNAME_Quantity = "Quantity";

	/** Set Quantity	  */
	public void setQuantity (int Quantity);

	/** Get Quantity	  */
	public int getQuantity();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();

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
