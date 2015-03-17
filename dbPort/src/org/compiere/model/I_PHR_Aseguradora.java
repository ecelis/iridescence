/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_Aseguradora
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_Aseguradora 
{

    /** TableName=PHR_Aseguradora */
    public static final String Table_Name = "PHR_Aseguradora";

    /** AD_Table_ID=1200926 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name Deducible */
    public static final String COLUMNNAME_Deducible = "Deducible";

	/** Set Deductible.
	  * Deductible
	  */
	public void setDeducible (BigDecimal Deducible);

	/** Get Deductible.
	  * Deductible
	  */
	public BigDecimal getDeducible();

    /** Column name DeduciblePorcentaje */
    public static final String COLUMNNAME_DeduciblePorcentaje = "DeduciblePorcentaje";

	/** Set Mount-Percentage	  */
	public void setDeduciblePorcentaje (BigDecimal DeduciblePorcentaje);

	/** Get Mount-Percentage	  */
	public BigDecimal getDeduciblePorcentaje();

    /** Column name EXME_InsuranceType_ID */
    public static final String COLUMNNAME_EXME_InsuranceType_ID = "EXME_InsuranceType_ID";

	/** Set Insurance Type	  */
	public void setEXME_InsuranceType_ID (int EXME_InsuranceType_ID);

	/** Get Insurance Type	  */
	public int getEXME_InsuranceType_ID();

	public I_EXME_InsuranceType getEXME_InsuranceType() throws RuntimeException;

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

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

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

    /** Column name NumeroGrupo */
    public static final String COLUMNNAME_NumeroGrupo = "NumeroGrupo";

	/** Set Group number	  */
	public void setNumeroGrupo (BigDecimal NumeroGrupo);

	/** Get Group number	  */
	public BigDecimal getNumeroGrupo();

    /** Column name NumeroPoliza */
    public static final String COLUMNNAME_NumeroPoliza = "NumeroPoliza";

	/** Set Police number	  */
	public void setNumeroPoliza (String NumeroPoliza);

	/** Get Police number	  */
	public String getNumeroPoliza();

    /** Column name PHR_Aseguradora_ID */
    public static final String COLUMNNAME_PHR_Aseguradora_ID = "PHR_Aseguradora_ID";

	/** Set Insurance	  */
	public void setPHR_Aseguradora_ID (int PHR_Aseguradora_ID);

	/** Get Insurance	  */
	public int getPHR_Aseguradora_ID();

    /** Column name Telefono */
    public static final String COLUMNNAME_Telefono = "Telefono";

	/** Set Telephone.
	  * friend telephone
	  */
	public void setTelefono (String Telefono);

	/** Get Telephone.
	  * friend telephone
	  */
	public String getTelefono();
}
