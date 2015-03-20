/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_VistaElegibilidad
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_VistaElegibilidad 
{

    /** TableName=EXME_VistaElegibilidad */
    public static final String Table_Name = "EXME_VistaElegibilidad";

    /** AD_Table_ID=1201119 */
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

    /** Column name CreateBeneficiary */
    public static final String COLUMNNAME_CreateBeneficiary = "CreateBeneficiary";

	/** Set Create Beneficiary	  */
	public void setCreateBeneficiary (String CreateBeneficiary);

	/** Get Create Beneficiary	  */
	public String getCreateBeneficiary();

    /** Column name CreateReport */
    public static final String COLUMNNAME_CreateReport = "CreateReport";

	/** Set View Report.
	  * View Report of patient data
	  */
	public void setCreateReport (String CreateReport);

	/** Get View Report.
	  * View Report of patient data
	  */
	public String getCreateReport();

    /** Column name Dias */
    public static final String COLUMNNAME_Dias = "Dias";

	/** Set Days	  */
	public void setDias (int Dias);

	/** Get Days	  */
	public int getDias();

    /** Column name DrugEligibilityApplication */
    public static final String COLUMNNAME_DrugEligibilityApplication = "DrugEligibilityApplication";

	/** Set Drug Eligibility Application	  */
	public void setDrugEligibilityApplication (String DrugEligibilityApplication);

	/** Get Drug Eligibility Application	  */
	public String getDrugEligibilityApplication();

    /** Column name EligibilityApplication */
    public static final String COLUMNNAME_EligibilityApplication = "EligibilityApplication";

	/** Set Eligibility Application	  */
	public void setEligibilityApplication (String EligibilityApplication);

	/** Get Eligibility Application	  */
	public String getEligibilityApplication();

    /** Column name Entity_IdCode */
    public static final String COLUMNNAME_Entity_IdCode = "Entity_IdCode";

	/** Set Entity_IdCode	  */
	public void setEntity_IdCode (String Entity_IdCode);

	/** Get Entity_IdCode	  */
	public String getEntity_IdCode();

    /** Column name Entity_TypeQual */
    public static final String COLUMNNAME_Entity_TypeQual = "Entity_TypeQual";

	/** Set Entity_TypeQual	  */
	public void setEntity_TypeQual (String Entity_TypeQual);

	/** Get Entity_TypeQual	  */
	public String getEntity_TypeQual();

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

    /** Column name EXME_Paciente_Dep_ID */
    public static final String COLUMNNAME_EXME_Paciente_Dep_ID = "EXME_Paciente_Dep_ID";

	/** Set EXME_Paciente_Dep_ID	  */
	public void setEXME_Paciente_Dep_ID (int EXME_Paciente_Dep_ID);

	/** Get EXME_Paciente_Dep_ID	  */
	public int getEXME_Paciente_Dep_ID();

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

    /** Column name EXME_VistaElegibilidad_ID */
    public static final String COLUMNNAME_EXME_VistaElegibilidad_ID = "EXME_VistaElegibilidad_ID";

	/** Set EXME_VistaElegibilidad_ID	  */
	public void setEXME_VistaElegibilidad_ID (int EXME_VistaElegibilidad_ID);

	/** Get EXME_VistaElegibilidad_ID	  */
	public int getEXME_VistaElegibilidad_ID();

    /** Column name Fecha_Expira */
    public static final String COLUMNNAME_Fecha_Expira = "Fecha_Expira";

	/** Set Expiration Date	  */
	public void setFecha_Expira (Timestamp Fecha_Expira);

	/** Get Expiration Date	  */
	public Timestamp getFecha_Expira();

    /** Column name Fecha_Solicitud */
    public static final String COLUMNNAME_Fecha_Solicitud = "Fecha_Solicitud";

	/** Set Requested Date	  */
	public void setFecha_Solicitud (Timestamp Fecha_Solicitud);

	/** Get Requested Date	  */
	public Timestamp getFecha_Solicitud();

    /** Column name Horas */
    public static final String COLUMNNAME_Horas = "Horas";

	/** Set Hour	  */
	public void setHoras (int Horas);

	/** Get Hour	  */
	public int getHoras();

    /** Column name IsExpired */
    public static final String COLUMNNAME_IsExpired = "IsExpired";

	/** Set IsExpired	  */
	public void setIsExpired (boolean IsExpired);

	/** Get IsExpired	  */
	public boolean isExpired();

    /** Column name LastName */
    public static final String COLUMNNAME_LastName = "LastName";

	/** Set LastName	  */
	public void setLastName (String LastName);

	/** Get LastName	  */
	public String getLastName();

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

    /** Column name NPI */
    public static final String COLUMNNAME_NPI = "NPI";

	/** Set NPI	  */
	public void setNPI (String NPI);

	/** Get NPI	  */
	public String getNPI();

    /** Column name Poliza */
    public static final String COLUMNNAME_Poliza = "Poliza";

	/** Set Insurance Policy.
	  * Insurance Policy
	  */
	public void setPoliza (String Poliza);

	/** Get Insurance Policy.
	  * Insurance Policy
	  */
	public String getPoliza();
}
