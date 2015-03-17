/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DH_PatientInfo
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_DH_PatientInfo 
{

    /** TableName=DH_PatientInfo */
    public static final String Table_Name = "DH_PatientInfo";

    /** AD_Table_ID=1201567 */
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

    /** Column name BancaComunal */
    public static final String COLUMNNAME_BancaComunal = "BancaComunal";

	/** Set Community Bank	  */
	public void setBancaComunal (String BancaComunal);

	/** Get Community Bank	  */
	public String getBancaComunal();

    /** Column name Ciclo */
    public static final String COLUMNNAME_Ciclo = "Ciclo";

	/** Set Cycle	  */
	public void setCiclo (String Ciclo);

	/** Get Cycle	  */
	public String getCiclo();

    /** Column name CreditType */
    public static final String COLUMNNAME_CreditType = "CreditType";

	/** Set Credit Type	  */
	public void setCreditType (String CreditType);

	/** Get Credit Type	  */
	public String getCreditType();

    /** Column name DH_PatientInfo_ID */
    public static final String COLUMNNAME_DH_PatientInfo_ID = "DH_PatientInfo_ID";

	/** Set Patient Info (right holder)	  */
	public void setDH_PatientInfo_ID (int DH_PatientInfo_ID);

	/** Get Patient Info (right holder)	  */
	public int getDH_PatientInfo_ID();

    /** Column name DocType */
    public static final String COLUMNNAME_DocType = "DocType";

	/** Set Document Type	  */
	public void setDocType (String DocType);

	/** Get Document Type	  */
	public String getDocType();

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

    /** Column name EXME_PacienteRel_ID */
    public static final String COLUMNNAME_EXME_PacienteRel_ID = "EXME_PacienteRel_ID";

	/** Set Patient Relations.
	  * Patient Relations
	  */
	public void setEXME_PacienteRel_ID (int EXME_PacienteRel_ID);

	/** Get Patient Relations.
	  * Patient Relations
	  */
	public int getEXME_PacienteRel_ID();

	public I_EXME_PacienteRel getEXME_PacienteRel() throws RuntimeException;

    /** Column name FechaDesem */
    public static final String COLUMNNAME_FechaDesem = "FechaDesem";

	/** Set Disbursement date	  */
	public void setFechaDesem (Timestamp FechaDesem);

	/** Get Disbursement date	  */
	public Timestamp getFechaDesem();

    /** Column name FechaUltPago */
    public static final String COLUMNNAME_FechaUltPago = "FechaUltPago";

	/** Set Date of last payment	  */
	public void setFechaUltPago (Timestamp FechaUltPago);

	/** Get Date of last payment	  */
	public Timestamp getFechaUltPago();

    /** Column name NombreAsesor */
    public static final String COLUMNNAME_NombreAsesor = "NombreAsesor";

	/** Set Advisor name	  */
	public void setNombreAsesor (String NombreAsesor);

	/** Get Advisor name	  */
	public String getNombreAsesor();

    /** Column name SOCreditStatus */
    public static final String COLUMNNAME_SOCreditStatus = "SOCreditStatus";

	/** Set Credit Status.
	  * Business Partner Credit Status
	  */
	public void setSOCreditStatus (String SOCreditStatus);

	/** Get Credit Status.
	  * Business Partner Credit Status
	  */
	public String getSOCreditStatus();
}
