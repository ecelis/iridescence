/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Egreso_Hora
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Egreso_Hora 
{

    /** TableName=EXME_Egreso_Hora */
    public static final String Table_Name = "EXME_Egreso_Hora";

    /** AD_Table_ID=1200002 */
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

    /** Column name Consecutivo */
    public static final String COLUMNNAME_Consecutivo = "Consecutivo";

	/** Set Consecutive.
	  * Consecutive
	  */
	public void setConsecutivo (int Consecutivo);

	/** Get Consecutive.
	  * Consecutive
	  */
	public int getConsecutivo();

    /** Column name Ctas_C */
    public static final String COLUMNNAME_Ctas_C = "Ctas_C";

	/** Set Ctas_C	  */
	public void setCtas_C (String Ctas_C);

	/** Get Ctas_C	  */
	public String getCtas_C();

    /** Column name EXME_Cama_ID */
    public static final String COLUMNNAME_EXME_Cama_ID = "EXME_Cama_ID";

	/** Set Bed.
	  * Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID);

	/** Get Bed.
	  * Bed
	  */
	public int getEXME_Cama_ID();

    /** Column name EXME_Egreso_Hora_ID */
    public static final String COLUMNNAME_EXME_Egreso_Hora_ID = "EXME_Egreso_Hora_ID";

	/** Set Time of Discharge	  */
	public void setEXME_Egreso_Hora_ID (int EXME_Egreso_Hora_ID);

	/** Get Time of Discharge	  */
	public int getEXME_Egreso_Hora_ID();

    /** Column name EXME_Egresos_ID */
    public static final String COLUMNNAME_EXME_Egresos_ID = "EXME_Egresos_ID";

	/** Set Discharges.
	  * Discharges
	  */
	public void setEXME_Egresos_ID (int EXME_Egresos_ID);

	/** Get Discharges.
	  * Discharges
	  */
	public int getEXME_Egresos_ID();

	public I_EXME_Egresos getEXME_Egresos() throws RuntimeException;

    /** Column name EXME_Habitacion_ID */
    public static final String COLUMNNAME_EXME_Habitacion_ID = "EXME_Habitacion_ID";

	/** Set Room.
	  * Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID);

	/** Get Room.
	  * Room
	  */
	public int getEXME_Habitacion_ID();

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

    /** Column name Expediente */
    public static final String COLUMNNAME_Expediente = "Expediente";

	/** Set Medical Record.
	  * Medical Record
	  */
	public void setExpediente (String Expediente);

	/** Get Medical Record.
	  * Medical Record
	  */
	public String getExpediente();

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

    /** Column name Hora */
    public static final String COLUMNNAME_Hora = "Hora";

	/** Set Hour.
	  * Hour
	  */
	public void setHora (String Hora);

	/** Get Hour.
	  * Hour
	  */
	public String getHora();

    /** Column name Motivo */
    public static final String COLUMNNAME_Motivo = "Motivo";

	/** Set Motive.
	  * Motive / Reason
	  */
	public void setMotivo (String Motivo);

	/** Get Motive.
	  * Motive / Reason
	  */
	public String getMotivo();

    /** Column name Trab_Soc */
    public static final String COLUMNNAME_Trab_Soc = "Trab_Soc";

	/** Set Social Work	  */
	public void setTrab_Soc (String Trab_Soc);

	/** Get Social Work	  */
	public String getTrab_Soc();

    /** Column name Ult_Act */
    public static final String COLUMNNAME_Ult_Act = "Ult_Act";

	/** Set Ult_Act	  */
	public void setUlt_Act (Timestamp Ult_Act);

	/** Get Ult_Act	  */
	public Timestamp getUlt_Act();

    /** Column name VoBo */
    public static final String COLUMNNAME_VoBo = "VoBo";

	/** Set OK.
	  * OK
	  */
	public void setVoBo (String VoBo);

	/** Get OK.
	  * OK
	  */
	public String getVoBo();
}
