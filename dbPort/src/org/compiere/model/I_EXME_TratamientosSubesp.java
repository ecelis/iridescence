/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TratamientosSubesp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TratamientosSubesp 
{

    /** TableName=EXME_TratamientosSubesp */
    public static final String Table_Name = "EXME_TratamientosSubesp";

    /** AD_Table_ID=1200648 */
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

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

    /** Column name EXME_Tratamientos_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_ID = "EXME_Tratamientos_ID";

	/** Set Treatments.
	  * Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID);

	/** Get Treatments.
	  * Treatments
	  */
	public int getEXME_Tratamientos_ID();

	public I_EXME_Tratamientos getEXME_Tratamientos() throws RuntimeException;

    /** Column name EXME_TratamientosSubesp_ID */
    public static final String COLUMNNAME_EXME_TratamientosSubesp_ID = "EXME_TratamientosSubesp_ID";

	/** Set Treatment subspecialty	  */
	public void setEXME_TratamientosSubesp_ID (int EXME_TratamientosSubesp_ID);

	/** Get Treatment subspecialty	  */
	public int getEXME_TratamientosSubesp_ID();

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
