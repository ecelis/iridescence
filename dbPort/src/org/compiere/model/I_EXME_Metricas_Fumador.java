/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Metricas_Fumador
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Metricas_Fumador 
{

    /** TableName=EXME_Metricas_Fumador */
    public static final String Table_Name = "EXME_Metricas_Fumador";

    /** AD_Table_ID=1201031 */
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

    /** Column name EXME_EstiloVidaPaciente_ID */
    public static final String COLUMNNAME_EXME_EstiloVidaPaciente_ID = "EXME_EstiloVidaPaciente_ID";

	/** Set Patient's Life Style	  */
	public void setEXME_EstiloVidaPaciente_ID (int EXME_EstiloVidaPaciente_ID);

	/** Get Patient's Life Style	  */
	public int getEXME_EstiloVidaPaciente_ID();

	public I_EXME_EstiloVidaPaciente getEXME_EstiloVidaPaciente() throws RuntimeException;

    /** Column name EXME_Metricas_Fumador_ID */
    public static final String COLUMNNAME_EXME_Metricas_Fumador_ID = "EXME_Metricas_Fumador_ID";

	/** Set Smoker metrics.
	  * Smoker metrics
	  */
	public void setEXME_Metricas_Fumador_ID (int EXME_Metricas_Fumador_ID);

	/** Get Smoker metrics.
	  * Smoker metrics
	  */
	public int getEXME_Metricas_Fumador_ID();

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

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();
}
