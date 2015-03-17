/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_I18N
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_I18N 
{

    /** TableName=EXME_I18N */
    public static final String Table_Name = "EXME_I18N";

    /** AD_Table_ID=1201270 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name canInsertNewProducts */
    public static final String COLUMNNAME_canInsertNewProducts = "canInsertNewProducts";

	/** Set Can Insert New Products.
	  * Can Insert New Products
	  */
	public void setcanInsertNewProducts (boolean canInsertNewProducts);

	/** Get Can Insert New Products.
	  * Can Insert New Products
	  */
	public boolean iscanInsertNewProducts();

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public I_C_Country getC_Country() throws RuntimeException;

    /** Column name Curp */
    public static final String COLUMNNAME_Curp = "Curp";

	/** Set National Identification Number.
	  * National Identification Number
	  */
	public void setCurp (String Curp);

	/** Get National Identification Number.
	  * National Identification Number
	  */
	public String getCurp();

    /** Column name EXME_I18N_ID */
    public static final String COLUMNNAME_EXME_I18N_ID = "EXME_I18N_ID";

	/** Set EXME_I18N_ID	  */
	public void setEXME_I18N_ID (int EXME_I18N_ID);

	/** Get EXME_I18N_ID	  */
	public int getEXME_I18N_ID();

    /** Column name HideChargesFields */
    public static final String COLUMNNAME_HideChargesFields = "HideChargesFields";

	/** Set Hide Fields in Charges.
	  * Hide Fields in Charges
	  */
	public void setHideChargesFields (boolean HideChargesFields);

	/** Get Hide Fields in Charges.
	  * Hide Fields in Charges
	  */
	public boolean isHideChargesFields();

    /** Column name moveTaxes */
    public static final String COLUMNNAME_moveTaxes = "moveTaxes";

	/** Set Move taxes between accounts.
	  * Move taxes between accounts
	  */
	public void setmoveTaxes (boolean moveTaxes);

	/** Get Move taxes between accounts.
	  * Move taxes between accounts
	  */
	public boolean ismoveTaxes();

    /** Column name noValidatePhysician */
    public static final String COLUMNNAME_noValidatePhysician = "noValidatePhysician";

	/** Set No Validate Physician.
	  * No Validate Physician
	  */
	public void setnoValidatePhysician (boolean noValidatePhysician);

	/** Get No Validate Physician.
	  * No Validate Physician
	  */
	public boolean isnoValidatePhysician();

    /** Column name patientFormMexico */
    public static final String COLUMNNAME_patientFormMexico = "patientFormMexico";

	/** Set Patient Form Mexico.
	  * Patient Form Mexico
	  */
	public void setpatientFormMexico (boolean patientFormMexico);

	/** Get Patient Form Mexico.
	  * Patient Form Mexico
	  */
	public boolean ispatientFormMexico();

    /** Column name ResponsibleMexico */
    public static final String COLUMNNAME_ResponsibleMexico = "ResponsibleMexico";

	/** Set Responsible Mexico	  */
	public void setResponsibleMexico (boolean ResponsibleMexico);

	/** Get Responsible Mexico	  */
	public boolean isResponsibleMexico();

    /** Column name spanishDefaultLanguage */
    public static final String COLUMNNAME_spanishDefaultLanguage = "spanishDefaultLanguage";

	/** Set Spanish Default Language.
	  * Spanish Default Language
	  */
	public void setspanishDefaultLanguage (boolean spanishDefaultLanguage);

	/** Get Spanish Default Language.
	  * Spanish Default Language
	  */
	public boolean isspanishDefaultLanguage();

    /** Column name SurtidoInterno */
    public static final String COLUMNNAME_SurtidoInterno = "SurtidoInterno";

	/** Set Internal Supply.
	  * Internal Supply
	  */
	public void setSurtidoInterno (boolean SurtidoInterno);

	/** Get Internal Supply.
	  * Internal Supply
	  */
	public boolean isSurtidoInterno();

    /** Column name validarCaja */
    public static final String COLUMNNAME_validarCaja = "validarCaja";

	/** Set validarCaja.
	  * Validate Cash Register
	  */
	public void setvalidarCaja (boolean validarCaja);

	/** Get validarCaja.
	  * Validate Cash Register
	  */
	public boolean isvalidarCaja();

    /** Column name validatecurp */
    public static final String COLUMNNAME_validatecurp = "validatecurp";

	/** Set validatecurp	  */
	public void setvalidatecurp (boolean validatecurp);

	/** Get validatecurp	  */
	public boolean isvalidatecurp();
}
