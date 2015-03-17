/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Hoja_Reclasificacion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Hoja_Reclasificacion 
{

    /** TableName=EXME_Hoja_Reclasificacion */
    public static final String Table_Name = "EXME_Hoja_Reclasificacion";

    /** AD_Table_ID=1200034 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name Aprobada */
    public static final String COLUMNNAME_Aprobada = "Aprobada";

	/** Set Approved.
	  * Approved
	  */
	public void setAprobada (boolean Aprobada);

	/** Get Approved.
	  * Approved
	  */
	public boolean isAprobada();

    /** Column name Autorizo */
    public static final String COLUMNNAME_Autorizo = "Autorizo";

	/** Set Authorized.
	  * Authorized
	  */
	public void setAutorizo (String Autorizo);

	/** Get Authorized.
	  * Authorized
	  */
	public String getAutorizo();

    /** Column name Cancelada */
    public static final String COLUMNNAME_Cancelada = "Cancelada";

	/** Set Cancelled.
	  * Cancelled
	  */
	public void setCancelada (boolean Cancelada);

	/** Get Cancelled.
	  * Cancelled
	  */
	public boolean isCancelada();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (int DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public int getDocumentNo();

    /** Column name Elaboro */
    public static final String COLUMNNAME_Elaboro = "Elaboro";

	/** Set Elaborated.
	  * Elaborated
	  */
	public void setElaboro (String Elaboro);

	/** Get Elaborated.
	  * Elaborated
	  */
	public String getElaboro();

    /** Column name EXME_Clas_Destino_ID */
    public static final String COLUMNNAME_EXME_Clas_Destino_ID = "EXME_Clas_Destino_ID";

	/** Set Target Classification.
	  * Target Classification
	  */
	public void setEXME_Clas_Destino_ID (int EXME_Clas_Destino_ID);

	/** Get Target Classification.
	  * Target Classification
	  */
	public int getEXME_Clas_Destino_ID();

    /** Column name EXME_Clas_Origen_ID */
    public static final String COLUMNNAME_EXME_Clas_Origen_ID = "EXME_Clas_Origen_ID";

	/** Set Origin Classification.
	  * Origin Classification
	  */
	public void setEXME_Clas_Origen_ID (int EXME_Clas_Origen_ID);

	/** Get Origin Classification.
	  * Origin Classification
	  */
	public int getEXME_Clas_Origen_ID();

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

    /** Column name EXME_Hoja_Reclasificacion_ID */
    public static final String COLUMNNAME_EXME_Hoja_Reclasificacion_ID = "EXME_Hoja_Reclasificacion_ID";

	/** Set Reclassification Sheet.
	  * Reclassification Sheet
	  */
	public void setEXME_Hoja_Reclasificacion_ID (int EXME_Hoja_Reclasificacion_ID);

	/** Get Reclassification Sheet.
	  * Reclassification Sheet
	  */
	public int getEXME_Hoja_Reclasificacion_ID();

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

    /** Column name Fecha_Alta */
    public static final String COLUMNNAME_Fecha_Alta = "Fecha_Alta";

	/** Set Creation Date.
	  * Creation Date
	  */
	public void setFecha_Alta (Timestamp Fecha_Alta);

	/** Get Creation Date.
	  * Creation Date
	  */
	public Timestamp getFecha_Alta();

    /** Column name Fecha_Hosp */
    public static final String COLUMNNAME_Fecha_Hosp = "Fecha_Hosp";

	/** Set Inpatient Date.
	  * Inpatient Date
	  */
	public void setFecha_Hosp (Timestamp Fecha_Hosp);

	/** Get Inpatient Date.
	  * Inpatient Date
	  */
	public Timestamp getFecha_Hosp();

    /** Column name Fecha_Impresion */
    public static final String COLUMNNAME_Fecha_Impresion = "Fecha_Impresion";

	/** Set Printing Date.
	  * Printing Date
	  */
	public void setFecha_Impresion (Timestamp Fecha_Impresion);

	/** Get Printing Date.
	  * Printing Date
	  */
	public Timestamp getFecha_Impresion();

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

    /** Column name Motivo_Rclasif */
    public static final String COLUMNNAME_Motivo_Rclasif = "Motivo_Rclasif";

	/** Set Classification Reason.
	  * Classification Reason
	  */
	public void setMotivo_Rclasif (String Motivo_Rclasif);

	/** Get Classification Reason.
	  * Classification Reason
	  */
	public String getMotivo_Rclasif();

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

    /** Column name Superviso */
    public static final String COLUMNNAME_Superviso = "Superviso";

	/** Set Supervised.
	  * Supervised
	  */
	public void setSuperviso (String Superviso);

	/** Get Supervised.
	  * Supervised
	  */
	public String getSuperviso();

    /** Column name Tipo_Rclas */
    public static final String COLUMNNAME_Tipo_Rclas = "Tipo_Rclas";

	/** Set Reclassification Type.
	  * Reclassification Type
	  */
	public void setTipo_Rclas (String Tipo_Rclas);

	/** Get Reclassification Type.
	  * Reclassification Type
	  */
	public String getTipo_Rclas();

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
