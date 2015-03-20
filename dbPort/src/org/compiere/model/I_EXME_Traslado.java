/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Traslado
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Traslado 
{

    /** TableName=EXME_Traslado */
    public static final String Table_Name = "EXME_Traslado";

    /** AD_Table_ID=1200027 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name Diagnostico */
    public static final String COLUMNNAME_Diagnostico = "Diagnostico";

	/** Set Diagnostic.
	  * Diagnostic
	  */
	public void setDiagnostico (String Diagnostico);

	/** Get Diagnostic.
	  * Diagnostic
	  */
	public String getDiagnostico();

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

    /** Column name EXME_Traslado_ID */
    public static final String COLUMNNAME_EXME_Traslado_ID = "EXME_Traslado_ID";

	/** Set Transfer.
	  * Transfer
	  */
	public void setEXME_Traslado_ID (int EXME_Traslado_ID);

	/** Get Transfer.
	  * Transfer
	  */
	public int getEXME_Traslado_ID();

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

    /** Column name Lugar_Tras */
    public static final String COLUMNNAME_Lugar_Tras = "Lugar_Tras";

	/** Set Transfer Place.
	  * Transfer Place
	  */
	public void setLugar_Tras (String Lugar_Tras);

	/** Get Transfer Place.
	  * Transfer Place
	  */
	public String getLugar_Tras();

    /** Column name Medico_Resp */
    public static final String COLUMNNAME_Medico_Resp = "Medico_Resp";

	/** Set Medical Manager.
	  * Medical Manager
	  */
	public void setMedico_Resp (String Medico_Resp);

	/** Get Medical Manager.
	  * Medical Manager
	  */
	public String getMedico_Resp();

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

    /** Column name TSCama */
    public static final String COLUMNNAME_TSCama = "TSCama";

	/** Set Social Work Bed	  */
	public void setTSCama (String TSCama);

	/** Get Social Work Bed	  */
	public String getTSCama();

    /** Column name TSClinico */
    public static final String COLUMNNAME_TSClinico = "TSClinico";

	/** Set Social Work Clinical.
	  * Social Work Clinical
	  */
	public void setTSClinico (String TSClinico);

	/** Get Social Work Clinical.
	  * Social Work Clinical
	  */
	public String getTSClinico();

    /** Column name User_T_S */
    public static final String COLUMNNAME_User_T_S = "User_T_S";

	/** Set User T.S..
	  * User T.S.
	  */
	public void setUser_T_S (int User_T_S);

	/** Get User T.S..
	  * User T.S.
	  */
	public int getUser_T_S();
}
