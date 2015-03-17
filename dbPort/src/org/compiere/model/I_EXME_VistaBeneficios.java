/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_VistaBeneficios
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_VistaBeneficios 
{

    /** TableName=EXME_VistaBeneficios */
    public static final String Table_Name = "EXME_VistaBeneficios";

    /** AD_Table_ID=1201118 */
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

    /** Column name EXME_BeneficiosH_ID */
    public static final String COLUMNNAME_EXME_BeneficiosH_ID = "EXME_BeneficiosH_ID";

	/** Set EXME_BeneficiosH_ID	  */
	public void setEXME_BeneficiosH_ID (int EXME_BeneficiosH_ID);

	/** Get EXME_BeneficiosH_ID	  */
	public int getEXME_BeneficiosH_ID();

	public I_EXME_BeneficiosH getEXME_BeneficiosH() throws RuntimeException;

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

    /** Column name EXME_InsuranceType_ID */
    public static final String COLUMNNAME_EXME_InsuranceType_ID = "EXME_InsuranceType_ID";

	/** Set Insurance Type	  */
	public void setEXME_InsuranceType_ID (int EXME_InsuranceType_ID);

	/** Get Insurance Type	  */
	public int getEXME_InsuranceType_ID();

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

    /** Column name EXME_Paciente_Sus_ID */
    public static final String COLUMNNAME_EXME_Paciente_Sus_ID = "EXME_Paciente_Sus_ID";

	/** Set EXME_Paciente_Sus_ID	  */
	public void setEXME_Paciente_Sus_ID (int EXME_Paciente_Sus_ID);

	/** Get EXME_Paciente_Sus_ID	  */
	public int getEXME_Paciente_Sus_ID();

    /** Column name EXME_TipoProd_ID */
    public static final String COLUMNNAME_EXME_TipoProd_ID = "EXME_TipoProd_ID";

	/** Set Product Subtype.
	  * Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID);

	/** Get Product Subtype.
	  * Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID();

    /** Column name EXME_VistaBeneficios_ID */
    public static final String COLUMNNAME_EXME_VistaBeneficios_ID = "EXME_VistaBeneficios_ID";

	/** Set EXME_VistaBeneficios_ID	  */
	public void setEXME_VistaBeneficios_ID (int EXME_VistaBeneficios_ID);

	/** Get EXME_VistaBeneficios_ID	  */
	public int getEXME_VistaBeneficios_ID();

    /** Column name IsData */
    public static final String COLUMNNAME_IsData = "IsData";

	/** Set IsData	  */
	public void setIsData (boolean IsData);

	/** Get IsData	  */
	public boolean isData();

    /** Column name IsData_In */
    public static final String COLUMNNAME_IsData_In = "IsData_In";

	/** Set IsData_In	  */
	public void setIsData_In (boolean IsData_In);

	/** Get IsData_In	  */
	public boolean isData_In();

    /** Column name IsData_Out */
    public static final String COLUMNNAME_IsData_Out = "IsData_Out";

	/** Set IsData_Out	  */
	public void setIsData_Out (boolean IsData_Out);

	/** Get IsData_Out	  */
	public boolean isData_Out();

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

    /** Column name Valores */
    public static final String COLUMNNAME_Valores = "Valores";

	/** Set Valores	  */
	public void setValores (String Valores);

	/** Get Valores	  */
	public String getValores();

    /** Column name Valores_In */
    public static final String COLUMNNAME_Valores_In = "Valores_In";

	/** Set Valores_In	  */
	public void setValores_In (String Valores_In);

	/** Get Valores_In	  */
	public String getValores_In();

    /** Column name Valores_Out */
    public static final String COLUMNNAME_Valores_Out = "Valores_Out";

	/** Set Valores_Out	  */
	public void setValores_Out (String Valores_Out);

	/** Get Valores_Out	  */
	public String getValores_Out();
}
