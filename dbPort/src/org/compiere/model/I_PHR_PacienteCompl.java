/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_PacienteCompl
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_PacienteCompl 
{

    /** TableName=PHR_PacienteCompl */
    public static final String Table_Name = "PHR_PacienteCompl";

    /** AD_Table_ID=1200920 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name HistoriaFamiliar */
    public static final String COLUMNNAME_HistoriaFamiliar = "HistoriaFamiliar";

	/** Set Family History.
	  * Family History
	  */
	public void setHistoriaFamiliar (String HistoriaFamiliar);

	/** Get Family History.
	  * Family History
	  */
	public String getHistoriaFamiliar();

    /** Column name HistoriaSocial */
    public static final String COLUMNNAME_HistoriaSocial = "HistoriaSocial";

	/** Set Social History.
	  * Social History
	  */
	public void setHistoriaSocial (String HistoriaSocial);

	/** Get Social History.
	  * Social History
	  */
	public String getHistoriaSocial();

    /** Column name PHR_PacienteCompl_ID */
    public static final String COLUMNNAME_PHR_PacienteCompl_ID = "PHR_PacienteCompl_ID";

	/** Set Additional information of the patient.
	  * Additional information of the patient
	  */
	public void setPHR_PacienteCompl_ID (int PHR_PacienteCompl_ID);

	/** Get Additional information of the patient.
	  * Additional information of the patient
	  */
	public int getPHR_PacienteCompl_ID();

    /** Column name TipoSangre */
    public static final String COLUMNNAME_TipoSangre = "TipoSangre";

	/** Set Bloody Type	  */
	public void setTipoSangre (String TipoSangre);

	/** Get Bloody Type	  */
	public String getTipoSangre();
}
