/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Metricas_IMC
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Metricas_IMC 
{

    /** TableName=EXME_Metricas_IMC */
    public static final String Table_Name = "EXME_Metricas_IMC";

    /** AD_Table_ID=1201030 */
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

    /** Column name EXME_Metricas_IMC_ID */
    public static final String COLUMNNAME_EXME_Metricas_IMC_ID = "EXME_Metricas_IMC_ID";

	/** Set BMI metrics.
	  * Body Mass Index metrics
	  */
	public void setEXME_Metricas_IMC_ID (int EXME_Metricas_IMC_ID);

	/** Get BMI metrics.
	  * Body Mass Index metrics
	  */
	public int getEXME_Metricas_IMC_ID();

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

    /** Column name EXME_RangoSV_ID */
    public static final String COLUMNNAME_EXME_RangoSV_ID = "EXME_RangoSV_ID";

	/** Set Vital Sign's Rank	  */
	public void setEXME_RangoSV_ID (int EXME_RangoSV_ID);

	/** Get Vital Sign's Rank	  */
	public int getEXME_RangoSV_ID();

	public I_EXME_RangoSV getEXME_RangoSV() throws RuntimeException;

    /** Column name EXME_SignoVitalDet_ID */
    public static final String COLUMNNAME_EXME_SignoVitalDet_ID = "EXME_SignoVitalDet_ID";

	/** Set Vital Signs Details.
	  * Vital Signs Details
	  */
	public void setEXME_SignoVitalDet_ID (int EXME_SignoVitalDet_ID);

	/** Get Vital Signs Details.
	  * Vital Signs Details
	  */
	public int getEXME_SignoVitalDet_ID();

	public I_EXME_SignoVitalDet getEXME_SignoVitalDet() throws RuntimeException;

    /** Column name Fecha_IMC */
    public static final String COLUMNNAME_Fecha_IMC = "Fecha_IMC";

	/** Set IMC Date	  */
	public void setFecha_IMC (Timestamp Fecha_IMC);

	/** Get IMC Date	  */
	public Timestamp getFecha_IMC();

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
