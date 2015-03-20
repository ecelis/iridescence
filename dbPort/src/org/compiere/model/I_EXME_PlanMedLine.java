/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PlanMedLine
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PlanMedLine 
{

    /** TableName=EXME_PlanMedLine */
    public static final String Table_Name = "EXME_PlanMedLine";

    /** AD_Table_ID=1000120 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name ApliedDate */
    public static final String COLUMNNAME_ApliedDate = "ApliedDate";

	/** Set Applied Date	  */
	public void setApliedDate (Timestamp ApliedDate);

	/** Get Applied Date	  */
	public Timestamp getApliedDate();

    /** Column name DeliveryDate */
    public static final String COLUMNNAME_DeliveryDate = "DeliveryDate";

	/** Set Delivery Date	  */
	public void setDeliveryDate (Timestamp DeliveryDate);

	/** Get Delivery Date	  */
	public Timestamp getDeliveryDate();

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

    /** Column name EXME_ActPacienteIndH_ID */
    public static final String COLUMNNAME_EXME_ActPacienteIndH_ID = "EXME_ActPacienteIndH_ID";

	/** Set Patient's Indication.
	  * Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID);

	/** Get Patient's Indication.
	  * Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID();

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException;

    /** Column name EXME_Hist_Vacuna_ID */
    public static final String COLUMNNAME_EXME_Hist_Vacuna_ID = "EXME_Hist_Vacuna_ID";

	/** Set Vaccination History.
	  * Vaccination History
	  */
	public void setEXME_Hist_Vacuna_ID (int EXME_Hist_Vacuna_ID);

	/** Get Vaccination History.
	  * Vaccination History
	  */
	public int getEXME_Hist_Vacuna_ID();

	public I_EXME_Hist_Vacuna getEXME_Hist_Vacuna() throws RuntimeException;

    /** Column name EXME_PlanMed_ID */
    public static final String COLUMNNAME_EXME_PlanMed_ID = "EXME_PlanMed_ID";

	/** Set Medical Plan	  */
	public void setEXME_PlanMed_ID (int EXME_PlanMed_ID);

	/** Get Medical Plan	  */
	public int getEXME_PlanMed_ID();

	public I_EXME_PlanMed getEXME_PlanMed() throws RuntimeException;

    /** Column name EXME_PlanMedLine_ID */
    public static final String COLUMNNAME_EXME_PlanMedLine_ID = "EXME_PlanMedLine_ID";

	/** Set Scheduled Doctor.
	  * Scheduled Doctor
	  */
	public void setEXME_PlanMedLine_ID (int EXME_PlanMedLine_ID);

	/** Get Scheduled Doctor.
	  * Scheduled Doctor
	  */
	public int getEXME_PlanMedLine_ID();

    /** Column name IsIntravenosa */
    public static final String COLUMNNAME_IsIntravenosa = "IsIntravenosa";

	/** Set Is intravenous	  */
	public void setIsIntravenosa (boolean IsIntravenosa);

	/** Get Is intravenous	  */
	public boolean isIntravenosa();

    /** Column name Lot */
    public static final String COLUMNNAME_Lot = "Lot";

	/** Set Lot No.
	  * Lot number (alphanumeric)
	  */
	public void setLot (String Lot);

	/** Get Lot No.
	  * Lot number (alphanumeric)
	  */
	public String getLot();

    /** Column name Motivos */
    public static final String COLUMNNAME_Motivos = "Motivos";

	/** Set Reason	  */
	public void setMotivos (String Motivos);

	/** Get Reason	  */
	public String getMotivos();

    /** Column name ProgDate */
    public static final String COLUMNNAME_ProgDate = "ProgDate";

	/** Set Programming Day	  */
	public void setProgDate (Timestamp ProgDate);

	/** Get Programming Day	  */
	public Timestamp getProgDate();

    /** Column name QtyAplied */
    public static final String COLUMNNAME_QtyAplied = "QtyAplied";

	/** Set QtyAplied	  */
	public void setQtyAplied (BigDecimal QtyAplied);

	/** Get QtyAplied	  */
	public BigDecimal getQtyAplied();

    /** Column name QtyPlanned */
    public static final String COLUMNNAME_QtyPlanned = "QtyPlanned";

	/** Set Planned Quantity	  */
	public void setQtyPlanned (BigDecimal QtyPlanned);

	/** Get Planned Quantity	  */
	public BigDecimal getQtyPlanned();

    /** Column name Ref_PlanMedLine_ID */
    public static final String COLUMNNAME_Ref_PlanMedLine_ID = "Ref_PlanMedLine_ID";

	/** Set Plan Medication Line Reference	  */
	public void setRef_PlanMedLine_ID (int Ref_PlanMedLine_ID);

	/** Get Plan Medication Line Reference	  */
	public int getRef_PlanMedLine_ID();

    /** Column name Result */
    public static final String COLUMNNAME_Result = "Result";

	/** Set Result.
	  * Result of the action taken
	  */
	public void setResult (BigDecimal Result);

	/** Get Result.
	  * Result of the action taken
	  */
	public BigDecimal getResult();

    /** Column name Unidad */
    public static final String COLUMNNAME_Unidad = "Unidad";

	/** Set Unity	  */
	public void setUnidad (BigDecimal Unidad);

	/** Get Unity	  */
	public BigDecimal getUnidad();
}
