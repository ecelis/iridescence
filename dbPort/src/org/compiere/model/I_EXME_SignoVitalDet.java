/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SignoVitalDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_SignoVitalDet 
{

    /** TableName=EXME_SignoVitalDet */
    public static final String Table_Name = "EXME_SignoVitalDet";

    /** AD_Table_ID=1200104 */
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

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

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

    /** Column name Edad */
    public static final String COLUMNNAME_Edad = "Edad";

	/** Set Age.
	  * Age
	  */
	public void setEdad (BigDecimal Edad);

	/** Get Age.
	  * Age
	  */
	public BigDecimal getEdad();

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

    /** Column name EXME_ActPaciente_ID */
    public static final String COLUMNNAME_EXME_ActPaciente_ID = "EXME_ActPaciente_ID";

	/** Set Patient Activity.
	  * Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID);

	/** Get Patient Activity.
	  * Patient Activity
	  */
	public int getEXME_ActPaciente_ID();

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException;

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

    /** Column name EXME_DiarioEnf_ID */
    public static final String COLUMNNAME_EXME_DiarioEnf_ID = "EXME_DiarioEnf_ID";

	/** Set Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID);

	/** Get Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID();

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException;

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_GraficaDefaultV_ID */
    public static final String COLUMNNAME_EXME_GraficaDefaultV_ID = "EXME_GraficaDefaultV_ID";

	/** Set Graphic Default Values	  */
	public void setEXME_GraficaDefaultV_ID (int EXME_GraficaDefaultV_ID);

	/** Get Graphic Default Values	  */
	public int getEXME_GraficaDefaultV_ID();

	public I_EXME_GraficaDefaultV getEXME_GraficaDefaultV() throws RuntimeException;

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

    /** Column name EXME_SignoVital_ID */
    public static final String COLUMNNAME_EXME_SignoVital_ID = "EXME_SignoVital_ID";

	/** Set Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID);

	/** Get Vital Sign	  */
	public int getEXME_SignoVital_ID();

	public I_EXME_SignoVital getEXME_SignoVital() throws RuntimeException;

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

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

    /** Column name MotivoCancelacion */
    public static final String COLUMNNAME_MotivoCancelacion = "MotivoCancelacion";

	/** Set Comment of the reason for suspension.
	  * Comment of the reason for suspension
	  */
	public void setMotivoCancelacion (String MotivoCancelacion);

	/** Get Comment of the reason for suspension.
	  * Comment of the reason for suspension
	  */
	public String getMotivoCancelacion();
	
	 /** Column name Ref_Folio */
    public static final String COLUMNNAME_Ref_Folio = "Ref_Folio";

	/** Set Reference Folio	  */
	public void setRef_Folio (int Ref_Folio);

	/** Get Reference Folio	  */
	public int getRef_Folio();

    /** Column name Seguimiento */
    public static final String COLUMNNAME_Seguimiento = "Seguimiento";

	/** Set Monitoring	  */
	public void setSeguimiento (String Seguimiento);

	/** Get Monitoring	  */
	public String getSeguimiento();

    /** Column name Valor */
    public static final String COLUMNNAME_Valor = "Valor";

	/** Set Value	  */
	public void setValor (BigDecimal Valor);

	/** Get Value	  */
	public BigDecimal getValor();
}
