/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_BeneficiosH
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_BeneficiosH 
{

    /** TableName=EXME_BeneficiosH */
    public static final String Table_Name = "EXME_BeneficiosH";

    /** AD_Table_ID=1201043 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name ElegibilityType */
    public static final String COLUMNNAME_ElegibilityType = "ElegibilityType";

	/** Set Elegibility Type	  */
	public void setElegibilityType (String ElegibilityType);

	/** Get Elegibility Type	  */
	public String getElegibilityType();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name EXME_Alternative_ID */
    public static final String COLUMNNAME_EXME_Alternative_ID = "EXME_Alternative_ID";

	/** Set Formulary Alternative	  */
	public void setEXME_Alternative_ID (int EXME_Alternative_ID);

	/** Get Formulary Alternative	  */
	public int getEXME_Alternative_ID();

	public I_EXME_Alternative getEXME_Alternative() throws RuntimeException;

    /** Column name EXME_BeneficiosH_ID */
    public static final String COLUMNNAME_EXME_BeneficiosH_ID = "EXME_BeneficiosH_ID";

	/** Set EXME_BeneficiosH_ID	  */
	public void setEXME_BeneficiosH_ID (int EXME_BeneficiosH_ID);

	/** Get EXME_BeneficiosH_ID	  */
	public int getEXME_BeneficiosH_ID();

    /** Column name EXME_Copay_ID */
    public static final String COLUMNNAME_EXME_Copay_ID = "EXME_Copay_ID";

	/** Set Benefit Copay	  */
	public void setEXME_Copay_ID (int EXME_Copay_ID);

	/** Get Benefit Copay	  */
	public int getEXME_Copay_ID();

	public I_EXME_Copay getEXME_Copay() throws RuntimeException;

    /** Column name EXME_Coverage_ID */
    public static final String COLUMNNAME_EXME_Coverage_ID = "EXME_Coverage_ID";

	/** Set Benfit Coverage	  */
	public void setEXME_Coverage_ID (int EXME_Coverage_ID);

	/** Get Benfit Coverage	  */
	public int getEXME_Coverage_ID();

	public I_EXME_Coverage getEXME_Coverage() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_Formulary_ID */
    public static final String COLUMNNAME_EXME_Formulary_ID = "EXME_Formulary_ID";

	/** Set Formulary status	  */
	public void setEXME_Formulary_ID (int EXME_Formulary_ID);

	/** Get Formulary status	  */
	public int getEXME_Formulary_ID();

	public I_EXME_Formulary getEXME_Formulary() throws RuntimeException;

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name EXME_Paciente_Sus_ID */
    public static final String COLUMNNAME_EXME_Paciente_Sus_ID = "EXME_Paciente_Sus_ID";

	/** Set EXME_Paciente_Sus_ID	  */
	public void setEXME_Paciente_Sus_ID (int EXME_Paciente_Sus_ID);

	/** Get EXME_Paciente_Sus_ID	  */
	public int getEXME_Paciente_Sus_ID();

    /** Column name IsElegibleMailOrder */
    public static final String COLUMNNAME_IsElegibleMailOrder = "IsElegibleMailOrder";

	/** Set Elegible for mail order pharmacy	  */
	public void setIsElegibleMailOrder (String IsElegibleMailOrder);

	/** Get Elegible for mail order pharmacy	  */
	public String getIsElegibleMailOrder();

    /** Column name IsElegibleRetail */
    public static final String COLUMNNAME_IsElegibleRetail = "IsElegibleRetail";

	/** Set Elegible for retail pharmacy	  */
	public void setIsElegibleRetail (String IsElegibleRetail);

	/** Get Elegible for retail pharmacy	  */
	public String getIsElegibleRetail();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();
}
