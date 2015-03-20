/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_NotificaIngreso
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_NotificaIngreso 
{

    /** TableName=EXME_NotificaIngreso */
    public static final String Table_Name = "EXME_NotificaIngreso";

    /** AD_Table_ID=1200003 */
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

	public I_EXME_Cama getEXME_Cama() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

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

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException;

    /** Column name EXME_NotificaIngreso_ID */
    public static final String COLUMNNAME_EXME_NotificaIngreso_ID = "EXME_NotificaIngreso_ID";

	/** Set Checkin Notification	  */
	public void setEXME_NotificaIngreso_ID (int EXME_NotificaIngreso_ID);

	/** Get Checkin Notification	  */
	public int getEXME_NotificaIngreso_ID();

    /** Column name Fecha_Ingreso */
    public static final String COLUMNNAME_Fecha_Ingreso = "Fecha_Ingreso";

	/** Set Entrance Date	  */
	public void setFecha_Ingreso (Timestamp Fecha_Ingreso);

	/** Get Entrance Date	  */
	public Timestamp getFecha_Ingreso();

    /** Column name IngresoPor */
    public static final String COLUMNNAME_IngresoPor = "IngresoPor";

	/** Set Enter By.
	  * Enter By
	  */
	public void setIngresoPor (String IngresoPor);

	/** Get Enter By.
	  * Enter By
	  */
	public String getIngresoPor();

    /** Column name Print_AdmSegReso */
    public static final String COLUMNNAME_Print_AdmSegReso = "Print_AdmSegReso";

	/** Set Print AdmSegReso	  */
	public void setPrint_AdmSegReso (boolean Print_AdmSegReso);

	/** Get Print AdmSegReso	  */
	public boolean isPrint_AdmSegReso();

    /** Column name Print_Cargos */
    public static final String COLUMNNAME_Print_Cargos = "Print_Cargos";

	/** Set Print Charges	  */
	public void setPrint_Cargos (boolean Print_Cargos);

	/** Get Print Charges	  */
	public boolean isPrint_Cargos();

    /** Column name Print_HistClinica */
    public static final String COLUMNNAME_Print_HistClinica = "Print_HistClinica";

	/** Set Print Clinical History	  */
	public void setPrint_HistClinica (boolean Print_HistClinica);

	/** Get Print Clinical History	  */
	public boolean isPrint_HistClinica();

    /** Column name Print_HjFrontal */
    public static final String COLUMNNAME_Print_HjFrontal = "Print_HjFrontal";

	/** Set Print Front Sheet	  */
	public void setPrint_HjFrontal (boolean Print_HjFrontal);

	/** Get Print Front Sheet	  */
	public boolean isPrint_HjFrontal();

    /** Column name Print_IndcMedcs */
    public static final String COLUMNNAME_Print_IndcMedcs = "Print_IndcMedcs";

	/** Set Print Medical Instructions	  */
	public void setPrint_IndcMedcs (boolean Print_IndcMedcs);

	/** Get Print Medical Instructions	  */
	public boolean isPrint_IndcMedcs();

    /** Column name Print_NtMedcs */
    public static final String COLUMNNAME_Print_NtMedcs = "Print_NtMedcs";

	/** Set Print Medical Notes	  */
	public void setPrint_NtMedcs (boolean Print_NtMedcs);

	/** Get Print Medical Notes	  */
	public boolean isPrint_NtMedcs();

    /** Column name Print_Sometria */
    public static final String COLUMNNAME_Print_Sometria = "Print_Sometria";

	/** Set Print Sometría	  */
	public void setPrint_Sometria (boolean Print_Sometria);

	/** Get Print Sometría	  */
	public boolean isPrint_Sometria();
}
