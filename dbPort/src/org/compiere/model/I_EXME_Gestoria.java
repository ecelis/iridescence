/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Gestoria
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Gestoria 
{

    /** TableName=EXME_Gestoria */
    public static final String Table_Name = "EXME_Gestoria";

    /** AD_Table_ID=1200020 */
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

    /** Column name Estudio_Esp */
    public static final String COLUMNNAME_Estudio_Esp = "Estudio_Esp";

	/** Set Special Study.
	  * Special Study
	  */
	public void setEstudio_Esp (String Estudio_Esp);

	/** Get Special Study.
	  * Special Study
	  */
	public String getEstudio_Esp();

    /** Column name EXME_Gestoria_ID */
    public static final String COLUMNNAME_EXME_Gestoria_ID = "EXME_Gestoria_ID";

	/** Set Agency.
	  * Agency
	  */
	public void setEXME_Gestoria_ID (int EXME_Gestoria_ID);

	/** Get Agency.
	  * Agency
	  */
	public int getEXME_Gestoria_ID();

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

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Institution.
	  * Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Institution.
	  * Institution
	  */
	public int getEXME_Institucion_ID();

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

    /** Column name Fecha_Cita */
    public static final String COLUMNNAME_Fecha_Cita = "Fecha_Cita";

	/** Set Appointment Date.
	  * Appointment Date
	  */
	public void setFecha_Cita (Timestamp Fecha_Cita);

	/** Get Appointment Date.
	  * Appointment Date
	  */
	public Timestamp getFecha_Cita();

    /** Column name Fecha_Entr */
    public static final String COLUMNNAME_Fecha_Entr = "Fecha_Entr";

	/** Set Delivery Date (social work).
	  * Delivery Date (social work)
	  */
	public void setFecha_Entr (Timestamp Fecha_Entr);

	/** Get Delivery Date (social work).
	  * Delivery Date (social work)
	  */
	public Timestamp getFecha_Entr();

    /** Column name Fecha_Oficio */
    public static final String COLUMNNAME_Fecha_Oficio = "Fecha_Oficio";

	/** Set Office Date.
	  * Office Date
	  */
	public void setFecha_Oficio (Timestamp Fecha_Oficio);

	/** Get Office Date.
	  * Office Date
	  */
	public Timestamp getFecha_Oficio();

    /** Column name InterCons */
    public static final String COLUMNNAME_InterCons = "InterCons";

	/** Set Inter. Consulta.
	  * Inter. Consulta
	  */
	public void setInterCons (String InterCons);

	/** Get Inter. Consulta.
	  * Inter. Consulta
	  */
	public String getInterCons();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name Nombre_Pac */
    public static final String COLUMNNAME_Nombre_Pac = "Nombre_Pac";

	/** Set Patient Name.
	  * Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac);

	/** Get Patient Name.
	  * Patient Name
	  */
	public String getNombre_Pac();

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

    /** Column name Traslado */
    public static final String COLUMNNAME_Traslado = "Traslado";

	/** Set Transfer.
	  * Transfer
	  */
	public void setTraslado (String Traslado);

	/** Get Transfer.
	  * Transfer
	  */
	public String getTraslado();

    /** Column name TSCama */
    public static final String COLUMNNAME_TSCama = "TSCama";

	/** Set Social Work Bed	  */
	public void setTSCama (String TSCama);

	/** Get Social Work Bed	  */
	public String getTSCama();

    /** Column name TSInstitucion */
    public static final String COLUMNNAME_TSInstitucion = "TSInstitucion";

	/** Set SW Institution	  */
	public void setTSInstitucion (String TSInstitucion);

	/** Get SW Institution	  */
	public String getTSInstitucion();
}
