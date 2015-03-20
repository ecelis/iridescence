/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RecursoEducativoRes
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RecursoEducativoRes 
{

    /** TableName=EXME_RecursoEducativoRes */
    public static final String Table_Name = "EXME_RecursoEducativoRes";

    /** AD_Table_ID=1201032 */
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

    /** Column name EXME_Loinc_ID */
    public static final String COLUMNNAME_EXME_Loinc_ID = "EXME_Loinc_ID";

	/** Set LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID);

	/** Get LOINC Code	  */
	public int getEXME_Loinc_ID();

	public I_EXME_Loinc getEXME_Loinc() throws RuntimeException;

    /** Column name EXME_RecursoEducativo_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativo_ID = "EXME_RecursoEducativo_ID";

	/** Set Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID);

	/** Get Education Resource	  */
	public int getEXME_RecursoEducativo_ID();

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException;

    /** Column name EXME_RecursoEducativoRes_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativoRes_ID = "EXME_RecursoEducativoRes_ID";

	/** Set Results Education Resources	  */
	public void setEXME_RecursoEducativoRes_ID (int EXME_RecursoEducativoRes_ID);

	/** Get Results Education Resources	  */
	public int getEXME_RecursoEducativoRes_ID();

    /** Column name Level_Max */
    public static final String COLUMNNAME_Level_Max = "Level_Max";

	/** Set Maximum Level.
	  * Maximum Inventory level for this product
	  */
	public void setLevel_Max (BigDecimal Level_Max);

	/** Get Maximum Level.
	  * Maximum Inventory level for this product
	  */
	public BigDecimal getLevel_Max();

    /** Column name Level_Min */
    public static final String COLUMNNAME_Level_Min = "Level_Min";

	/** Set Minimum Level.
	  * Minimum Inventory level for this product
	  */
	public void setLevel_Min (BigDecimal Level_Min);

	/** Get Minimum Level.
	  * Minimum Inventory level for this product
	  */
	public BigDecimal getLevel_Min();
}
