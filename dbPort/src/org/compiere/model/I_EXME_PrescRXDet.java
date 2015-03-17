/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PrescRXDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PrescRXDet 
{

    /** TableName=EXME_PrescRXDet */
    public static final String Table_Name = "EXME_PrescRXDet";

    /** AD_Table_ID=1201142 */
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

    /** Column name ApprovalUser */
    public static final String COLUMNNAME_ApprovalUser = "ApprovalUser";

	/** Set ApprovalUser	  */
	public void setApprovalUser (int ApprovalUser);

	/** Get ApprovalUser	  */
	public int getApprovalUser();

    /** Column name Authenticated */
    public static final String COLUMNNAME_Authenticated = "Authenticated";

	/** Set Authenticated	  */
	public void setAuthenticated (boolean Authenticated);

	/** Get Authenticated	  */
	public boolean isAuthenticated();

    /** Column name AuthenticatedBy */
    public static final String COLUMNNAME_AuthenticatedBy = "AuthenticatedBy";

	/** Set Authenticated By	  */
	public void setAuthenticatedBy (int AuthenticatedBy);

	/** Get Authenticated By	  */
	public int getAuthenticatedBy();

    /** Column name Authenticated_Date */
    public static final String COLUMNNAME_Authenticated_Date = "Authenticated_Date";

	/** Set Authentication Date	  */
	public void setAuthenticated_Date (Timestamp Authenticated_Date);

	/** Get Authentication Date	  */
	public Timestamp getAuthenticated_Date();

    /** Column name DiscontinuedDate */
    public static final String COLUMNNAME_DiscontinuedDate = "DiscontinuedDate";

	/** Set Discontinued Date	  */
	public void setDiscontinuedDate (Timestamp DiscontinuedDate);

	/** Get Discontinued Date	  */
	public Timestamp getDiscontinuedDate();

    /** Column name Dose */
    public static final String COLUMNNAME_Dose = "Dose";

	/** Set Medication Doses.
	  * Medication Doses
	  */
	public void setDose (String Dose);

	/** Get Medication Doses.
	  * Medication Doses
	  */
	public String getDose();

    /** Column name DoseRate */
    public static final String COLUMNNAME_DoseRate = "DoseRate";

	/** Set Dose / Rate.
	  * Dose / Rate
	  */
	public void setDoseRate (String DoseRate);

	/** Get Dose / Rate.
	  * Dose / Rate
	  */
	public String getDoseRate();

    /** Column name ERxStatus */
    public static final String COLUMNNAME_ERxStatus = "ERxStatus";

	/** Set ePrescribing Status	  */
	public void setERxStatus (String ERxStatus);

	/** Get ePrescribing Status	  */
	public String getERxStatus();

    /** Column name EXME_ActPacienteInd_ID */
    public static final String COLUMNNAME_EXME_ActPacienteInd_ID = "EXME_ActPacienteInd_ID";

	/** Set Indication's detail.
	  * Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID);

	/** Get Indication's detail.
	  * Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID();

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException;

    /** Column name EXME_EncounterForms_ID */
    public static final String COLUMNNAME_EXME_EncounterForms_ID = "EXME_EncounterForms_ID";

	/** Set Encounter Forms.
	  * Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID);

	/** Get Encounter Forms.
	  * Encounter Forms
	  */
	public int getEXME_EncounterForms_ID();

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException;

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Unit.
	  * Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Unit.
	  * Service Unit
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_Farmacia_ID */
    public static final String COLUMNNAME_EXME_Farmacia_ID = "EXME_Farmacia_ID";

	/** Set Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID);

	/** Get Pharmacy	  */
	public int getEXME_Farmacia_ID();

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException;

    /** Column name EXME_Frequency1_ID */
    public static final String COLUMNNAME_EXME_Frequency1_ID = "EXME_Frequency1_ID";

	/** Set Frequency 1.
	  * Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID);

	/** Get Frequency 1.
	  * Frequency Header ID
	  */
	public int getEXME_Frequency1_ID();

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException;

    /** Column name EXME_Frequency2_ID */
    public static final String COLUMNNAME_EXME_Frequency2_ID = "EXME_Frequency2_ID";

	/** Set Frequency 2.
	  * Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID);

	/** Get Frequency 2.
	  * Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID();

	public I_EXME_Frequency2 getEXME_Frequency2() throws RuntimeException;

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException;

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

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException;

    /** Column name EXME_Pharmacistc_ID */
    public static final String COLUMNNAME_EXME_Pharmacistc_ID = "EXME_Pharmacistc_ID";

	/** Set Pharmacist Certified	  */
	public void setEXME_Pharmacistc_ID (int EXME_Pharmacistc_ID);

	/** Get Pharmacist Certified	  */
	public int getEXME_Pharmacistc_ID();

    /** Column name EXME_PharmacistT_ID */
    public static final String COLUMNNAME_EXME_PharmacistT_ID = "EXME_PharmacistT_ID";

	/** Set Pharmacist Technician	  */
	public void setEXME_PharmacistT_ID (int EXME_PharmacistT_ID);

	/** Get Pharmacist Technician	  */
	public int getEXME_PharmacistT_ID();

    /** Column name EXME_PrescRXDet_ID */
    public static final String COLUMNNAME_EXME_PrescRXDet_ID = "EXME_PrescRXDet_ID";

	/** Set RXNorm Prescription Detail.
	  * RXNorm Prescription Detail
	  */
	public void setEXME_PrescRXDet_ID (int EXME_PrescRXDet_ID);

	/** Get RXNorm Prescription Detail.
	  * RXNorm Prescription Detail
	  */
	public int getEXME_PrescRXDet_ID();

    /** Column name EXME_PrescRX_ID */
    public static final String COLUMNNAME_EXME_PrescRX_ID = "EXME_PrescRX_ID";

	/** Set RXNorm Prescription.
	  * RXNorm Prescription
	  */
	public void setEXME_PrescRX_ID (int EXME_PrescRX_ID);

	/** Get RXNorm Prescription.
	  * RXNorm Prescription
	  */
	public int getEXME_PrescRX_ID();

	public I_EXME_PrescRX getEXME_PrescRX() throws RuntimeException;

    /** Column name EXME_Route_ID */
    public static final String COLUMNNAME_EXME_Route_ID = "EXME_Route_ID";

	/** Set Route.
	  * Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID);

	/** Get Route.
	  * Route
	  */
	public int getEXME_Route_ID();

	public I_EXME_Route getEXME_Route() throws RuntimeException;

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException;

    /** Column name Indicaciones */
    public static final String COLUMNNAME_Indicaciones = "Indicaciones";

	/** Set Indications	  */
	public void setIndicaciones (String Indicaciones);

	/** Get Indications	  */
	public String getIndicaciones();

    /** Column name IsDAW */
    public static final String COLUMNNAME_IsDAW = "IsDAW";

	/** Set Dispense as Written	  */
	public void setIsDAW (boolean IsDAW);

	/** Get Dispense as Written	  */
	public boolean isDAW();

    /** Column name IsDEA */
    public static final String COLUMNNAME_IsDEA = "IsDEA";

	/** Set Print DEA Number	  */
	public void setIsDEA (boolean IsDEA);

	/** Get Print DEA Number	  */
	public boolean isDEA();

    /** Column name IsDelivered */
    public static final String COLUMNNAME_IsDelivered = "IsDelivered";

	/** Set Delivered	  */
	public void setIsDelivered (boolean IsDelivered);

	/** Get Delivered	  */
	public boolean isDelivered();

    /** Column name IsPrefer */
    public static final String COLUMNNAME_IsPrefer = "IsPrefer";

	/** Set Is Prefered Medication.
	  * Is Prefered Medication
	  */
	public void setIsPrefer (boolean IsPrefer);

	/** Get Is Prefered Medication.
	  * Is Prefered Medication
	  */
	public boolean isPrefer();

    /** Column name IsPRN */
    public static final String COLUMNNAME_IsPRN = "IsPRN";

	/** Set When necessary	  */
	public void setIsPRN (boolean IsPRN);

	/** Get When necessary	  */
	public boolean isPRN();

    /** Column name LastDay */
    public static final String COLUMNNAME_LastDay = "LastDay";

	/** Set Date of Medication Last Shot	  */
	public void setLastDay (Timestamp LastDay);

	/** Get Date of Medication Last Shot	  */
	public Timestamp getLastDay();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name Notas */
    public static final String COLUMNNAME_Notas = "Notas";

	/** Set Notes	  */
	public void setNotas (String Notas);

	/** Get Notes	  */
	public String getNotas();

    /** Column name NotedBy */
    public static final String COLUMNNAME_NotedBy = "NotedBy";

	/** Set Noted By	  */
	public void setNotedBy (int NotedBy);

	/** Get Noted By	  */
	public int getNotedBy();

    /** Column name NotedDate */
    public static final String COLUMNNAME_NotedDate = "NotedDate";

	/** Set Noted Date	  */
	public void setNotedDate (Timestamp NotedDate);

	/** Get Noted Date	  */
	public Timestamp getNotedDate();

    /** Column name Notes */
    public static final String COLUMNNAME_Notes = "Notes";

	/** Set Notes	  */
	public void setNotes (String Notes);

	/** Get Notes	  */
	public String getNotes();

    /** Column name NumDias */
    public static final String COLUMNNAME_NumDias = "NumDias";

	/** Set Number of days	  */
	public void setNumDias (int NumDias);

	/** Get Number of days	  */
	public int getNumDias();

    /** Column name OrderType */
    public static final String COLUMNNAME_OrderType = "OrderType";

	/** Set OrderType	  */
	public void setOrderType (String OrderType);

	/** Get OrderType	  */
	public String getOrderType();

    /** Column name ORGEXME_PrescRXDet_ID */
    public static final String COLUMNNAME_ORGEXME_PrescRXDet_ID = "ORGEXME_PrescRXDet_ID";

	/** Set ORGEXME_PrescRXDet_ID.
	  * ORGEXME_PrescRXDet_ID
	  */
	public void setORGEXME_PrescRXDet_ID (int ORGEXME_PrescRXDet_ID);

	/** Get ORGEXME_PrescRXDet_ID.
	  * ORGEXME_PrescRXDet_ID
	  */
	public int getORGEXME_PrescRXDet_ID();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name QtyPlan */
    public static final String COLUMNNAME_QtyPlan = "QtyPlan";

	/** Set Quantity Plan.
	  * Planned Quantity
	  */
	public void setQtyPlan (BigDecimal QtyPlan);

	/** Get Quantity Plan.
	  * Planned Quantity
	  */
	public BigDecimal getQtyPlan();

    /** Column name Quantity */
    public static final String COLUMNNAME_Quantity = "Quantity";

	/** Set Quantity	  */
	public void setQuantity (BigDecimal Quantity);

	/** Get Quantity	  */
	public BigDecimal getQuantity();

    /** Column name ReadBack */
    public static final String COLUMNNAME_ReadBack = "ReadBack";

	/** Set Read Back.
	  * Read Back
	  */
	public void setReadBack (String ReadBack);

	/** Get Read Back.
	  * Read Back
	  */
	public String getReadBack();

    /** Column name Reconciliation */
    public static final String COLUMNNAME_Reconciliation = "Reconciliation";

	/** Set Medication reconciliation.
	  * Medication reconciliation
	  */
	public void setReconciliation (boolean Reconciliation);

	/** Get Medication reconciliation.
	  * Medication reconciliation
	  */
	public boolean isReconciliation();

    /** Column name Resurtidos */
    public static final String COLUMNNAME_Resurtidos = "Resurtidos";

	/** Set Refills	  */
	public void setResurtidos (int Resurtidos);

	/** Get Refills	  */
	public int getResurtidos();

    /** Column name RXReview */
    public static final String COLUMNNAME_RXReview = "RXReview";

	/** Set Pharmacist Review	  */
	public void setRXReview (Timestamp RXReview);

	/** Get Pharmacist Review	  */
	public Timestamp getRXReview();

    /** Column name RXReviewC */
    public static final String COLUMNNAME_RXReviewC = "RXReviewC";

	/** Set Pharmacist Review	  */
	public void setRXReviewC (Timestamp RXReviewC);

	/** Get Pharmacist Review	  */
	public Timestamp getRXReviewC();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (boolean Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public boolean isStatus();

    /** Column name SurtidoPorEnfermera */
    public static final String COLUMNNAME_SurtidoPorEnfermera = "SurtidoPorEnfermera";

	/** Set Supplied by Infirmary Staff.
	  * Medication supplied by Infirmary Staff
	  */
	public void setSurtidoPorEnfermera (boolean SurtidoPorEnfermera);

	/** Get Supplied by Infirmary Staff.
	  * Medication supplied by Infirmary Staff
	  */
	public boolean isSurtidoPorEnfermera();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation
	  */
	public String getType();
}
