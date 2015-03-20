/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_BancoDeSangreH
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_BancoDeSangreH 
{

    /** TableName=EXME_BancoDeSangreH */
    public static final String Table_Name = "EXME_BancoDeSangreH";

    /** AD_Table_ID=1200508 */
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

    /** Column name CostoEstudio */
    public static final String COLUMNNAME_CostoEstudio = "CostoEstudio";

	/** Set Cost of the Study.
	  * Cost of the Study
	  */
	public void setCostoEstudio (int CostoEstudio);

	/** Get Cost of the Study.
	  * Cost of the Study
	  */
	public int getCostoEstudio();

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

    /** Column name EstudioSolicitado */
    public static final String COLUMNNAME_EstudioSolicitado = "EstudioSolicitado";

	/** Set Requested Study name.
	  * Requested Study name
	  */
	public void setEstudioSolicitado (String EstudioSolicitado);

	/** Get Requested Study name.
	  * Requested Study name
	  */
	public String getEstudioSolicitado();

    /** Column name EXME_BancoDeSangreH_ID */
    public static final String COLUMNNAME_EXME_BancoDeSangreH_ID = "EXME_BancoDeSangreH_ID";

	/** Set Blood Bank.
	  * Blood Bank
	  */
	public void setEXME_BancoDeSangreH_ID (int EXME_BancoDeSangreH_ID);

	/** Get Blood Bank.
	  * Blood Bank
	  */
	public int getEXME_BancoDeSangreH_ID();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_Hist_Exp_ID */
    public static final String COLUMNNAME_EXME_Hist_Exp_ID = "EXME_Hist_Exp_ID";

	/** Set Medical File.
	  * Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID);

	/** Get Medical File.
	  * Medical File
	  */
	public int getEXME_Hist_Exp_ID();

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException;

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

    /** Column name FechaEstudio */
    public static final String COLUMNNAME_FechaEstudio = "FechaEstudio";

	/** Set Study Date.
	  * Study Date
	  */
	public void setFechaEstudio (Timestamp FechaEstudio);

	/** Get Study Date.
	  * Study Date
	  */
	public Timestamp getFechaEstudio();

    /** Column name FolioAnalisis */
    public static final String COLUMNNAME_FolioAnalisis = "FolioAnalisis";

	/** Set Analisys of the Folio.
	  * Analisys of the Folio
	  */
	public void setFolioAnalisis (BigDecimal FolioAnalisis);

	/** Get Analisys of the Folio.
	  * Analisys of the Folio
	  */
	public BigDecimal getFolioAnalisis();

    /** Column name ObservacionesAnalisis */
    public static final String COLUMNNAME_ObservacionesAnalisis = "ObservacionesAnalisis";

	/** Set Analysis of Observations.
	  * Analysis of Observations
	  */
	public void setObservacionesAnalisis (String ObservacionesAnalisis);

	/** Get Analysis of Observations.
	  * Analysis of Observations
	  */
	public String getObservacionesAnalisis();
}
