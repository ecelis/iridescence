/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Frequency2
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Frequency2 
{

    /** TableName=EXME_Frequency2 */
    public static final String Table_Name = "EXME_Frequency2";

    /** AD_Table_ID=1201131 */
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

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException;

    /** Column name EXME_Frequency2_ID */
    public static final String COLUMNNAME_EXME_Frequency2_ID = "EXME_Frequency2_ID";

	/** Set Frequency 2.
	  * Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID);

	/** Get Frequency 2.
	  * Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID();

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

    /** Column name Reschedule */
    public static final String COLUMNNAME_Reschedule = "Reschedule";

	/** Set Reschedule	  */
	public void setReschedule (boolean Reschedule);

	/** Get Reschedule	  */
	public boolean isReschedule();

    /** Column name StartDateReq */
    public static final String COLUMNNAME_StartDateReq = "StartDateReq";

	/** Set Start Date/Time Required	  */
	public void setStartDateReq (boolean StartDateReq);

	/** Get Start Date/Time Required	  */
	public boolean isStartDateReq();

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
