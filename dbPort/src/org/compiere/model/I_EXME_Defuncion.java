/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Defuncion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Defuncion 
{

    /** TableName=EXME_Defuncion */
    public static final String Table_Name = "EXME_Defuncion";

    /** AD_Table_ID=1000216 */
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

    /** Column name AvisoFamiliar */
    public static final String COLUMNNAME_AvisoFamiliar = "AvisoFamiliar";

	/** Set Relatives Notice.
	  * Notice to patient relatives
	  */
	public void setAvisoFamiliar (Timestamp AvisoFamiliar);

	/** Get Relatives Notice.
	  * Notice to patient relatives
	  */
	public Timestamp getAvisoFamiliar();

    /** Column name AvisoTrabSoc */
    public static final String COLUMNNAME_AvisoTrabSoc = "AvisoTrabSoc";

	/** Set Social Work Notice.
	  * Notice to Social work
	  */
	public void setAvisoTrabSoc (Timestamp AvisoTrabSoc);

	/** Get Social Work Notice.
	  * Notice to Social work
	  */
	public Timestamp getAvisoTrabSoc();

    /** Column name Direccion */
    public static final String COLUMNNAME_Direccion = "Direccion";

	/** Set Address	  */
	public void setDireccion (String Direccion);

	/** Get Address	  */
	public String getDireccion();

    /** Column name EXME_Defuncion_ID */
    public static final String COLUMNNAME_EXME_Defuncion_ID = "EXME_Defuncion_ID";

	/** Set Deaths.
	  * Deaths
	  */
	public void setEXME_Defuncion_ID (int EXME_Defuncion_ID);

	/** Get Deaths.
	  * Deaths
	  */
	public int getEXME_Defuncion_ID();

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

    /** Column name FechaHr */
    public static final String COLUMNNAME_FechaHr = "FechaHr";

	/** Set Hr and Date.
	  * Hr and Date
	  */
	public void setFechaHr (Timestamp FechaHr);

	/** Get Hr and Date.
	  * Hr and Date
	  */
	public Timestamp getFechaHr();

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

    /** Column name Observacion */
    public static final String COLUMNNAME_Observacion = "Observacion";

	/** Set Observation.
	  * Observation
	  */
	public void setObservacion (String Observacion);

	/** Get Observation.
	  * Observation
	  */
	public String getObservacion();

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
}
