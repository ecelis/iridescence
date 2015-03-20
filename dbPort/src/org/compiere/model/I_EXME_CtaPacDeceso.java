/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacDeceso
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CtaPacDeceso 
{

    /** TableName=EXME_CtaPacDeceso */
    public static final String Table_Name = "EXME_CtaPacDeceso";

    /** AD_Table_ID=1200560 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AbortoGestacion */
    public static final String COLUMNNAME_AbortoGestacion = "AbortoGestacion";

	/** Set Gestational week of abortion	  */
	public void setAbortoGestacion (BigDecimal AbortoGestacion);

	/** Get Gestational week of abortion	  */
	public BigDecimal getAbortoGestacion();

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

    /** Column name CanceledBy */
    public static final String COLUMNNAME_CanceledBy = "CanceledBy";

	/** Set Canceled By.
	  * Canceled By
	  */
	public void setCanceledBy (int CanceledBy);

	/** Get Canceled By.
	  * Canceled By
	  */
	public int getCanceledBy();

    /** Column name CausaExterna */
    public static final String COLUMNNAME_CausaExterna = "CausaExterna";

	/** Set External Causes	  */
	public void setCausaExterna (String CausaExterna);

	/** Get External Causes	  */
	public String getCausaExterna();

    /** Column name CitaDe */
    public static final String COLUMNNAME_CitaDe = "CitaDe";

	/** Set Appointment Of.
	  * Appointment Of
	  */
	public void setCitaDe (String CitaDe);

	/** Get Appointment Of.
	  * Appointment Of
	  */
	public String getCitaDe();

    /** Column name DateCanceled */
    public static final String COLUMNNAME_DateCanceled = "DateCanceled";

	/** Set Date Canceled	  */
	public void setDateCanceled (Timestamp DateCanceled);

	/** Get Date Canceled	  */
	public Timestamp getDateCanceled();

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

    /** Column name EXME_CtaPacDeceso_ID */
    public static final String COLUMNNAME_EXME_CtaPacDeceso_ID = "EXME_CtaPacDeceso_ID";

	/** Set Death of Encounter	  */
	public void setEXME_CtaPacDeceso_ID (int EXME_CtaPacDeceso_ID);

	/** Get Death of Encounter	  */
	public int getEXME_CtaPacDeceso_ID();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_MotivoCancel_ID */
    public static final String COLUMNNAME_EXME_MotivoCancel_ID = "EXME_MotivoCancel_ID";

	/** Set Cancel Reason.
	  * Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID);

	/** Get Cancel Reason.
	  * Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID();

	public I_EXME_MotivoCancel getEXME_MotivoCancel() throws RuntimeException;

    /** Column name EXME_MotivoMuerte_ID */
    public static final String COLUMNNAME_EXME_MotivoMuerte_ID = "EXME_MotivoMuerte_ID";

	/** Set Death Cause.
	  * Death Cause
	  */
	public void setEXME_MotivoMuerte_ID (int EXME_MotivoMuerte_ID);

	/** Get Death Cause.
	  * Death Cause
	  */
	public int getEXME_MotivoMuerte_ID();

	public I_EXME_MotivoMuerte getEXME_MotivoMuerte() throws RuntimeException;

    /** Column name Fecha_Muerte */
    public static final String COLUMNNAME_Fecha_Muerte = "Fecha_Muerte";

	/** Set Date of Death.
	  * Date of Death
	  */
	public void setFecha_Muerte (Timestamp Fecha_Muerte);

	/** Get Date of Death.
	  * Date of Death
	  */
	public Timestamp getFecha_Muerte();

    /** Column name IntoxEtilica */
    public static final String COLUMNNAME_IntoxEtilica = "IntoxEtilica";

	/** Set Alcohol Poisoning	  */
	public void setIntoxEtilica (String IntoxEtilica);

	/** Get Alcohol Poisoning	  */
	public String getIntoxEtilica();

    /** Column name MortMaternaCausa */
    public static final String COLUMNNAME_MortMaternaCausa = "MortMaternaCausa";

	/** Set Maternal Mortality in Relation to the Cause	  */
	public void setMortMaternaCausa (String MortMaternaCausa);

	/** Get Maternal Mortality in Relation to the Cause	  */
	public String getMortMaternaCausa();

    /** Column name MortMaternaTiempo */
    public static final String COLUMNNAME_MortMaternaTiempo = "MortMaternaTiempo";

	/** Set Maternal Mortality in Relation to Time	  */
	public void setMortMaternaTiempo (String MortMaternaTiempo);

	/** Get Maternal Mortality in Relation to Time	  */
	public String getMortMaternaTiempo();

    /** Column name MotivoCancel */
    public static final String COLUMNNAME_MotivoCancel = "MotivoCancel";

	/** Set Cancel Reason.
	  * Cancel Reason
	  */
	public void setMotivoCancel (String MotivoCancel);

	/** Get Cancel Reason.
	  * Cancel Reason
	  */
	public String getMotivoCancel();

    /** Column name Peso */
    public static final String COLUMNNAME_Peso = "Peso";

	/** Set Weight.
	  * Weight
	  */
	public void setPeso (BigDecimal Peso);

	/** Get Weight.
	  * Weight
	  */
	public BigDecimal getPeso();

    /** Column name SemGestacion */
    public static final String COLUMNNAME_SemGestacion = "SemGestacion";

	/** Set Weeks Gestation	  */
	public void setSemGestacion (BigDecimal SemGestacion);

	/** Get Weeks Gestation	  */
	public BigDecimal getSemGestacion();

    /** Column name TipoCtaPac */
    public static final String COLUMNNAME_TipoCtaPac = "TipoCtaPac";

	/** Set Encounter Type	  */
	public void setTipoCtaPac (String TipoCtaPac);

	/** Get Encounter Type	  */
	public String getTipoCtaPac();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
