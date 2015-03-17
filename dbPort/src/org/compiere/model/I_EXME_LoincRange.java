/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LoincRange
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_LoincRange 
{

    /** TableName=EXME_LoincRange */
    public static final String Table_Name = "EXME_LoincRange";

    /** AD_Table_ID=1201334 */
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

    /** Column name EXME_LoincRange_ID */
    public static final String COLUMNNAME_EXME_LoincRange_ID = "EXME_LoincRange_ID";

	/** Set Loinc Range	  */
	public void setEXME_LoincRange_ID (int EXME_LoincRange_ID);

	/** Get Loinc Range	  */
	public int getEXME_LoincRange_ID();

    /** Column name EXME_Loinc_ID */
    public static final String COLUMNNAME_EXME_Loinc_ID = "EXME_Loinc_ID";

	/** Set LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID);

	/** Get LOINC Code	  */
	public int getEXME_Loinc_ID();

	public I_EXME_Loinc getEXME_Loinc() throws RuntimeException;

    /** Column name EdadMaxima */
    public static final String COLUMNNAME_EdadMaxima = "EdadMaxima";

	/** Set Maximum age	  */
	public void setEdadMaxima (BigDecimal EdadMaxima);

	/** Get Maximum age	  */
	public BigDecimal getEdadMaxima();

    /** Column name EdadMinima */
    public static final String COLUMNNAME_EdadMinima = "EdadMinima";

	/** Set Minimum age	  */
	public void setEdadMinima (BigDecimal EdadMinima);

	/** Get Minimum age	  */
	public BigDecimal getEdadMinima();

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (String Sexo);

	/** Get Sex.
	  * Sex
	  */
	public String getSexo();

    /** Column name ValueMax */
    public static final String COLUMNNAME_ValueMax = "ValueMax";

	/** Set Max. Value.
	  * Maximum Value for a field
	  */
	public void setValueMax (BigDecimal ValueMax);

	/** Get Max. Value.
	  * Maximum Value for a field
	  */
	public BigDecimal getValueMax();

    /** Column name ValueMin */
    public static final String COLUMNNAME_ValueMin = "ValueMin";

	/** Set Min. Value.
	  * Minimum Value for a field
	  */
	public void setValueMin (BigDecimal ValueMin);

	/** Get Min. Value.
	  * Minimum Value for a field
	  */
	public BigDecimal getValueMin();
}
