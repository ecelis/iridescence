/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TipoMedico
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TipoMedico 
{

    /** TableName=EXME_TipoMedico */
    public static final String Table_Name = "EXME_TipoMedico";

    /** AD_Table_ID=1000010 */
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

    /** Column name DedRetenImptoHon */
    public static final String COLUMNNAME_DedRetenImptoHon = "DedRetenImptoHon";

	/** Set Withholding Tax Deductible by Receipt Payments.
	  * Withholding Tax deductible by receipt payment
	  */
	public void setDedRetenImptoHon (BigDecimal DedRetenImptoHon);

	/** Get Withholding Tax Deductible by Receipt Payments.
	  * Withholding Tax deductible by receipt payment
	  */
	public BigDecimal getDedRetenImptoHon();

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

    /** Column name EXME_TipoMedico_ID */
    public static final String COLUMNNAME_EXME_TipoMedico_ID = "EXME_TipoMedico_ID";

	/** Set Type of Doctor.
	  * Type of Doctor
	  */
	public void setEXME_TipoMedico_ID (int EXME_TipoMedico_ID);

	/** Get Type of Doctor.
	  * Type of Doctor
	  */
	public int getEXME_TipoMedico_ID();

    /** Column name MaxRetenAdmon */
    public static final String COLUMNNAME_MaxRetenAdmon = "MaxRetenAdmon";

	/** Set Max withholded by Administration.
	  * Maximum amount withholded by administration
	  */
	public void setMaxRetenAdmon (BigDecimal MaxRetenAdmon);

	/** Get Max withholded by Administration.
	  * Maximum amount withholded by administration
	  */
	public BigDecimal getMaxRetenAdmon();

    /** Column name MaxRetenImptoHon */
    public static final String COLUMNNAME_MaxRetenImptoHon = "MaxRetenImptoHon";

	/** Set Max tax withholded by payment notes.
	  * Max. tax withholded by payment notes
	  */
	public void setMaxRetenImptoHon (BigDecimal MaxRetenImptoHon);

	/** Get Max tax withholded by payment notes.
	  * Max. tax withholded by payment notes
	  */
	public BigDecimal getMaxRetenImptoHon();

    /** Column name MinRetenAdmon */
    public static final String COLUMNNAME_MinRetenAdmon = "MinRetenAdmon";

	/** Set Min withholded by administration.
	  * Min amount withholded by administration
	  */
	public void setMinRetenAdmon (BigDecimal MinRetenAdmon);

	/** Get Min withholded by administration.
	  * Min amount withholded by administration
	  */
	public BigDecimal getMinRetenAdmon();

    /** Column name MinRetenImptoHon */
    public static final String COLUMNNAME_MinRetenImptoHon = "MinRetenImptoHon";

	/** Set Min tax withholded by notes payments.
	  * Minimum tax withholded by notes payments
	  */
	public void setMinRetenImptoHon (BigDecimal MinRetenImptoHon);

	/** Get Min tax withholded by notes payments.
	  * Minimum tax withholded by notes payments
	  */
	public BigDecimal getMinRetenImptoHon();

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

    /** Column name RetenAdmon */
    public static final String COLUMNNAME_RetenAdmon = "RetenAdmon";

	/** Set Withholded by Administration.
	  * Withholded by Administration
	  */
	public void setRetenAdmon (BigDecimal RetenAdmon);

	/** Get Withholded by Administration.
	  * Withholded by Administration
	  */
	public BigDecimal getRetenAdmon();

    /** Column name RetenImptoHon */
    public static final String COLUMNNAME_RetenImptoHon = "RetenImptoHon";

	/** Set Wthholded Tax by Payment Note.
	  * Withholded tax by Payment Note
	  */
	public void setRetenImptoHon (BigDecimal RetenImptoHon);

	/** Get Wthholded Tax by Payment Note.
	  * Withholded tax by Payment Note
	  */
	public BigDecimal getRetenImptoHon();

    /** Column name RetenOtros */
    public static final String COLUMNNAME_RetenOtros = "RetenOtros";

	/** Set Other Withholding.
	  * Other withholding
	  */
	public void setRetenOtros (BigDecimal RetenOtros);

	/** Get Other Withholding.
	  * Other withholding
	  */
	public BigDecimal getRetenOtros();

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
