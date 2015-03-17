/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_IndicacionesPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_IndicacionesPac 
{

    /** TableName=EXME_MO_IndicacionesPac */
    public static final String Table_Name = "EXME_MO_IndicacionesPac";

    /** AD_Table_ID=1200388 */
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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EsOdontograma */
    public static final String COLUMNNAME_EsOdontograma = "EsOdontograma";

	/** Set Is Odontogram.
	  * Is Odontogram
	  */
	public void setEsOdontograma (boolean EsOdontograma);

	/** Get Is Odontogram.
	  * Is Odontogram
	  */
	public boolean isEsOdontograma();

    /** Column name EXME_CitaMedica_ID */
    public static final String COLUMNNAME_EXME_CitaMedica_ID = "EXME_CitaMedica_ID";

	/** Set Medical Appointment.
	  * Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID);

	/** Get Medical Appointment.
	  * Medical appointment
	  */
	public int getEXME_CitaMedica_ID();

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException;

    /** Column name EXME_MO_IndicacionesPac_ID */
    public static final String COLUMNNAME_EXME_MO_IndicacionesPac_ID = "EXME_MO_IndicacionesPac_ID";

	/** Set Patient Indication.
	  * Patient Indication
	  */
	public void setEXME_MO_IndicacionesPac_ID (int EXME_MO_IndicacionesPac_ID);

	/** Get Patient Indication.
	  * Patient Indication
	  */
	public int getEXME_MO_IndicacionesPac_ID();

    /** Column name EXME_MO_PiezaDental_ID */
    public static final String COLUMNNAME_EXME_MO_PiezaDental_ID = "EXME_MO_PiezaDental_ID";

	/** Set Dental Piece	  */
	public void setEXME_MO_PiezaDental_ID (int EXME_MO_PiezaDental_ID);

	/** Get Dental Piece	  */
	public int getEXME_MO_PiezaDental_ID();

	public I_EXME_MO_PiezaDental getEXME_MO_PiezaDental() throws RuntimeException;

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

    /** Column name Fecha_Indicacion */
    public static final String COLUMNNAME_Fecha_Indicacion = "Fecha_Indicacion";

	/** Set Indication Date.
	  * Indication Date
	  */
	public void setFecha_Indicacion (Timestamp Fecha_Indicacion);

	/** Get Indication Date.
	  * Indication Date
	  */
	public Timestamp getFecha_Indicacion();

    /** Column name TipoIndicacion */
    public static final String COLUMNNAME_TipoIndicacion = "TipoIndicacion";

	/** Set Indication Type.
	  * Indication Type
	  */
	public void setTipoIndicacion (String TipoIndicacion);

	/** Get Indication Type.
	  * Indication Type
	  */
	public String getTipoIndicacion();
}
