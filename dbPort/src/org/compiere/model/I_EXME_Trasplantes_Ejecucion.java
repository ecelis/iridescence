/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Trasplantes_Ejecucion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Trasplantes_Ejecucion 
{

    /** TableName=EXME_Trasplantes_Ejecucion */
    public static final String Table_Name = "EXME_Trasplantes_Ejecucion";

    /** AD_Table_ID=1200865 */
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

    /** Column name Comentarios */
    public static final String COLUMNNAME_Comentarios = "Comentarios";

	/** Set Notes	  */
	public void setComentarios (String Comentarios);

	/** Get Notes	  */
	public String getComentarios();

    /** Column name Exitoso */
    public static final String COLUMNNAME_Exitoso = "Exitoso";

	/** Set Successful.
	  * Successful
	  */
	public void setExitoso (boolean Exitoso);

	/** Get Successful.
	  * Successful
	  */
	public boolean isExitoso();

    /** Column name EXME_MedicoCirujano_ID */
    public static final String COLUMNNAME_EXME_MedicoCirujano_ID = "EXME_MedicoCirujano_ID";

	/** Set Medical Surgeon.
	  * Medical in charge of Surgery
	  */
	public void setEXME_MedicoCirujano_ID (int EXME_MedicoCirujano_ID);

	/** Get Medical Surgeon.
	  * Medical in charge of Surgery
	  */
	public int getEXME_MedicoCirujano_ID();

    /** Column name EXME_MedicoTratante_ID */
    public static final String COLUMNNAME_EXME_MedicoTratante_ID = "EXME_MedicoTratante_ID";

	/** Set Medical Handler.
	  * Medical Patient processor
	  */
	public void setEXME_MedicoTratante_ID (int EXME_MedicoTratante_ID);

	/** Get Medical Handler.
	  * Medical Patient processor
	  */
	public int getEXME_MedicoTratante_ID();

    /** Column name EXME_Organos_Tejidos_ID */
    public static final String COLUMNNAME_EXME_Organos_Tejidos_ID = "EXME_Organos_Tejidos_ID";

	/** Set Organs/Tissues .
	  * ID de table organs and tissues
	  */
	public void setEXME_Organos_Tejidos_ID (int EXME_Organos_Tejidos_ID);

	/** Get Organs/Tissues .
	  * ID de table organs and tissues
	  */
	public int getEXME_Organos_Tejidos_ID();

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

    /** Column name EXME_Trasplantes_Ejecucion_ID */
    public static final String COLUMNNAME_EXME_Trasplantes_Ejecucion_ID = "EXME_Trasplantes_Ejecucion_ID";

	/** Set Transplantation.
	  * Implementation of Transplantation
	  */
	public void setEXME_Trasplantes_Ejecucion_ID (int EXME_Trasplantes_Ejecucion_ID);

	/** Get Transplantation.
	  * Implementation of Transplantation
	  */
	public int getEXME_Trasplantes_Ejecucion_ID();

    /** Column name FechaRegListaEspera */
    public static final String COLUMNNAME_FechaRegListaEspera = "FechaRegListaEspera";

	/** Set Waiting List Registration.
	  * Registration Date Waiting List
	  */
	public void setFechaRegListaEspera (Timestamp FechaRegListaEspera);

	/** Get Waiting List Registration.
	  * Registration Date Waiting List
	  */
	public Timestamp getFechaRegListaEspera();

    /** Column name FechaTrasplante */
    public static final String COLUMNNAME_FechaTrasplante = "FechaTrasplante";

	/** Set Transplant Date.
	  * Transplant Date
	  */
	public void setFechaTrasplante (Timestamp FechaTrasplante);

	/** Get Transplant Date.
	  * Transplant Date
	  */
	public Timestamp getFechaTrasplante();

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
