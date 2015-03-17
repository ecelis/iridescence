/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Control_Exp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Control_Exp 
{

    /** TableName=EXME_Control_Exp */
    public static final String Table_Name = "EXME_Control_Exp";

    /** AD_Table_ID=1200076 */
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

    /** Column name AD_User_Devol_ID */
    public static final String COLUMNNAME_AD_User_Devol_ID = "AD_User_Devol_ID";

	/** Set User Doing the Return	  */
	public void setAD_User_Devol_ID (int AD_User_Devol_ID);

	/** Get User Doing the Return	  */
	public int getAD_User_Devol_ID();

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

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name Documentos */
    public static final String COLUMNNAME_Documentos = "Documentos";

	/** Set Documents	  */
	public void setDocumentos (String Documentos);

	/** Get Documents	  */
	public String getDocumentos();

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

    /** Column name EXME_Control_Exp_ID */
    public static final String COLUMNNAME_EXME_Control_Exp_ID = "EXME_Control_Exp_ID";

	/** Set Patient File Control	  */
	public void setEXME_Control_Exp_ID (int EXME_Control_Exp_ID);

	/** Get Patient File Control	  */
	public int getEXME_Control_Exp_ID();

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException;

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

    /** Column name FechaDevol */
    public static final String COLUMNNAME_FechaDevol = "FechaDevol";

	/** Set Return Date.
	  * Return Date
	  */
	public void setFechaDevol (Timestamp FechaDevol);

	/** Get Return Date.
	  * Return Date
	  */
	public Timestamp getFechaDevol();

    /** Column name FechaPtmo */
    public static final String COLUMNNAME_FechaPtmo = "FechaPtmo";

	/** Set Lending Date.
	  * Lending Date and Time
	  */
	public void setFechaPtmo (Timestamp FechaPtmo);

	/** Get Lending Date.
	  * Lending Date and Time
	  */
	public Timestamp getFechaPtmo();

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

    /** Column name TipoExp */
    public static final String COLUMNNAME_TipoExp = "TipoExp";

	/** Set Patient File Type	  */
	public void setTipoExp (String TipoExp);

	/** Get Patient File Type	  */
	public String getTipoExp();

    /** Column name TipoMovto */
    public static final String COLUMNNAME_TipoMovto = "TipoMovto";

	/** Set Movement Type	  */
	public void setTipoMovto (String TipoMovto);

	/** Get Movement Type	  */
	public String getTipoMovto();

    /** Column name TipoPrestamo */
    public static final String COLUMNNAME_TipoPrestamo = "TipoPrestamo";

	/** Set Lending Type	  */
	public void setTipoPrestamo (boolean TipoPrestamo);

	/** Get Lending Type	  */
	public boolean isTipoPrestamo();

    /** Column name Tomos */
    public static final String COLUMNNAME_Tomos = "Tomos";

	/** Set Volumes	  */
	public void setTomos (BigDecimal Tomos);

	/** Get Volumes	  */
	public BigDecimal getTomos();
}
