/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Terapia_Producto
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Terapia_Producto 
{

    /** TableName=EXME_Terapia_Producto */
    public static final String Table_Name = "EXME_Terapia_Producto";

    /** AD_Table_ID=1201063 */
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

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

    /** Column name EXME_Terapia_ID */
    public static final String COLUMNNAME_EXME_Terapia_ID = "EXME_Terapia_ID";

	/** Set Therapy	  */
	public void setEXME_Terapia_ID (int EXME_Terapia_ID);

	/** Get Therapy	  */
	public int getEXME_Terapia_ID();

	public I_EXME_Terapia getEXME_Terapia() throws RuntimeException;

    /** Column name EXME_Terapia_Producto_ID */
    public static final String COLUMNNAME_EXME_Terapia_Producto_ID = "EXME_Terapia_Producto_ID";

	/** Set Therapy - Product	  */
	public void setEXME_Terapia_Producto_ID (int EXME_Terapia_Producto_ID);

	/** Get Therapy - Product	  */
	public int getEXME_Terapia_Producto_ID();

    /** Column name EXME_TherapiesVersion_ID */
    public static final String COLUMNNAME_EXME_TherapiesVersion_ID = "EXME_TherapiesVersion_ID";

	/** Set Therapies Version	  */
	public void setEXME_TherapiesVersion_ID (int EXME_TherapiesVersion_ID);

	/** Get Therapies Version	  */
	public int getEXME_TherapiesVersion_ID();

    /** Column name Medication */
    public static final String COLUMNNAME_Medication = "Medication";

	/** Set Medication.
	  * Medication
	  */
	public void setMedication (String Medication);

	/** Get Medication.
	  * Medication
	  */
	public String getMedication();
}
