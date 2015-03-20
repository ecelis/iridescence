/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Trasplantes_Espera
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Trasplantes_Espera 
{

    /** TableName=EXME_Trasplantes_Espera */
    public static final String Table_Name = "EXME_Trasplantes_Espera";

    /** AD_Table_ID=1200862 */
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

    /** Column name Antiguedad_Registro */
    public static final String COLUMNNAME_Antiguedad_Registro = "Antiguedad_Registro";

	/** Set Joined.
	  * Joined
	  */
	public void setAntiguedad_Registro (String Antiguedad_Registro);

	/** Get Joined.
	  * Joined
	  */
	public String getAntiguedad_Registro();

    /** Column name Criterios */
    public static final String COLUMNNAME_Criterios = "Criterios";

	/** Set Criteria.
	  * Criteria
	  */
	public void setCriterios (String Criterios);

	/** Get Criteria.
	  * Criteria
	  */
	public String getCriterios();

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (String Estatus);

	/** Get Status.
	  * Status
	  */
	public String getEstatus();

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

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

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

	public I_EXME_Organos_Tejidos getEXME_Organos_Tejidos() throws RuntimeException;

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

    /** Column name EXME_Prioridad_ID */
    public static final String COLUMNNAME_EXME_Prioridad_ID = "EXME_Prioridad_ID";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority
	  */
	public void setEXME_Prioridad_ID (int EXME_Prioridad_ID);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority
	  */
	public int getEXME_Prioridad_ID();

	public I_EXME_Prioridad getEXME_Prioridad() throws RuntimeException;

    /** Column name EXME_Trasplantes_Espera_ID */
    public static final String COLUMNNAME_EXME_Trasplantes_Espera_ID = "EXME_Trasplantes_Espera_ID";

	/** Set Transplant Waiting.
	  * Transplant Waiting
	  */
	public void setEXME_Trasplantes_Espera_ID (int EXME_Trasplantes_Espera_ID);

	/** Get Transplant Waiting.
	  * Transplant Waiting
	  */
	public int getEXME_Trasplantes_Espera_ID();

    /** Column name Fecha_Estatus */
    public static final String COLUMNNAME_Fecha_Estatus = "Fecha_Estatus";

	/** Set Fecha_Estatus	  */
	public void setFecha_Estatus (Timestamp Fecha_Estatus);

	/** Get Fecha_Estatus	  */
	public Timestamp getFecha_Estatus();

    /** Column name Fecha_Registro */
    public static final String COLUMNNAME_Fecha_Registro = "Fecha_Registro";

	/** Set Date of Record.
	  * Date of Record
	  */
	public void setFecha_Registro (Timestamp Fecha_Registro);

	/** Get Date of Record.
	  * Date of Record
	  */
	public Timestamp getFecha_Registro();

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

    /** Column name Peso */
    public static final String COLUMNNAME_Peso = "Peso";

	/** Set Weight.
	  * Weight
	  */
	public void setPeso (String Peso);

	/** Get Weight.
	  * Weight
	  */
	public String getPeso();

    /** Column name Posicion_Calificacion */
    public static final String COLUMNNAME_Posicion_Calificacion = "Posicion_Calificacion";

	/** Set Position / Rating.
	  * Position / Rating
	  */
	public void setPosicion_Calificacion (String Posicion_Calificacion);

	/** Get Position / Rating.
	  * Position / Rating
	  */
	public String getPosicion_Calificacion();

    /** Column name Talla */
    public static final String COLUMNNAME_Talla = "Talla";

	/** Set Height.
	  * Height
	  */
	public void setTalla (String Talla);

	/** Get Height.
	  * Height
	  */
	public String getTalla();

    /** Column name TipoSangre */
    public static final String COLUMNNAME_TipoSangre = "TipoSangre";

	/** Set Bloody Type	  */
	public void setTipoSangre (String TipoSangre);

	/** Get Bloody Type	  */
	public String getTipoSangre();

    /** Column name Valoracion */
    public static final String COLUMNNAME_Valoracion = "Valoracion";

	/** Set Valuation	  */
	public void setValoracion (String Valoracion);

	/** Get Valuation	  */
	public String getValoracion();
}
