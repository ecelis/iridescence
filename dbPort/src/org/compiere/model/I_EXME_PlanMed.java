/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PlanMed
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_PlanMed 
{

    /** TableName=EXME_PlanMed */
    public static final String Table_Name = "EXME_PlanMed";

    /** AD_Table_ID=1000119 */
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

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

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

    /** Column name Duracion */
    public static final String COLUMNNAME_Duracion = "Duracion";

	/** Set Duration.
	  * Duration
	  */
	public void setDuracion (int Duracion);

	/** Get Duration.
	  * Duration
	  */
	public int getDuracion();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

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

    /** Column name EXME_Dosis_ID */
    public static final String COLUMNNAME_EXME_Dosis_ID = "EXME_Dosis_ID";

	/** Set Dose	  */
	public void setEXME_Dosis_ID (int EXME_Dosis_ID);

	/** Get Dose	  */
	public int getEXME_Dosis_ID();

	public I_EXME_Dosis getEXME_Dosis() throws RuntimeException;

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

    /** Column name EXME_EsqInsulina_ID */
    public static final String COLUMNNAME_EXME_EsqInsulina_ID = "EXME_EsqInsulina_ID";

	/** Set Insulin Scheme	  */
	public void setEXME_EsqInsulina_ID (int EXME_EsqInsulina_ID);

	/** Get Insulin Scheme	  */
	public int getEXME_EsqInsulina_ID();

	public I_EXME_EsqInsulina getEXME_EsqInsulina() throws RuntimeException;

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

    /** Column name EXME_PaqBase_Version_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_ID = "EXME_PaqBase_Version_ID";

	/** Set Package Version.
	  * Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID);

	/** Get Package Version.
	  * Package Version
	  */
	public int getEXME_PaqBase_Version_ID();

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException;

    /** Column name EXME_PlanMed_ID */
    public static final String COLUMNNAME_EXME_PlanMed_ID = "EXME_PlanMed_ID";

	/** Set Medical Plan	  */
	public void setEXME_PlanMed_ID (int EXME_PlanMed_ID);

	/** Get Medical Plan	  */
	public int getEXME_PlanMed_ID();

    /** Column name EXME_Prescription_ID */
    public static final String COLUMNNAME_EXME_Prescription_ID = "EXME_Prescription_ID";

	/** Set Prescription.
	  * Prescription
	  */
	public void setEXME_Prescription_ID (int EXME_Prescription_ID);

	/** Get Prescription.
	  * Prescription
	  */
	public int getEXME_Prescription_ID();

	public I_EXME_Prescription getEXME_Prescription() throws RuntimeException;

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

	public I_EXME_PrescRXDet getEXME_PrescRXDet() throws RuntimeException;

    /** Column name EXME_Tratamientos_Sesion_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Sesion_ID = "EXME_Tratamientos_Sesion_ID";

	/** Set Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID);

	/** Get Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID();

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException;

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException;

    /** Column name Intervalo */
    public static final String COLUMNNAME_Intervalo = "Intervalo";

	/** Set Interval.
	  * Interval
	  */
	public void setIntervalo (int Intervalo);

	/** Get Interval.
	  * Interval
	  */
	public int getIntervalo();

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

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name QtyPlanned */
    public static final String COLUMNNAME_QtyPlanned = "QtyPlanned";

	/** Set Planned Quantity	  */
	public void setQtyPlanned (BigDecimal QtyPlanned);

	/** Get Planned Quantity	  */
	public BigDecimal getQtyPlanned();

    /** Column name QtyTotAplied */
    public static final String COLUMNNAME_QtyTotAplied = "QtyTotAplied";

	/** Set Total Applied Quantity	  */
	public void setQtyTotAplied (BigDecimal QtyTotAplied);

	/** Get Total Applied Quantity	  */
	public BigDecimal getQtyTotAplied();

    /** Column name QtyTotPlanned */
    public static final String COLUMNNAME_QtyTotPlanned = "QtyTotPlanned";

	/** Set Planned Total Quantity	  */
	public void setQtyTotPlanned (BigDecimal QtyTotPlanned);

	/** Get Planned Total Quantity	  */
	public BigDecimal getQtyTotPlanned();

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

    /** Column name Surtir */
    public static final String COLUMNNAME_Surtir = "Surtir";

	/** Set Deliver.
	  * If the product or service will deliver internally
	  */
	public void setSurtir (boolean Surtir);

	/** Get Deliver.
	  * If the product or service will deliver internally
	  */
	public boolean isSurtir();

    /** Column name Tipo */
    public static final String COLUMNNAME_Tipo = "Tipo";

	/** Set Type.
	  * GL
	  */
	public void setTipo (String Tipo);

	/** Get Type.
	  * GL
	  */
	public String getTipo();

    /** Column name TomadoCasa */
    public static final String COLUMNNAME_TomadoCasa = "TomadoCasa";

	/** Set Taken at home	  */
	public void setTomadoCasa (boolean TomadoCasa);

	/** Get Taken at home	  */
	public boolean isTomadoCasa();

    /** Column name UOMDuracion */
    public static final String COLUMNNAME_UOMDuracion = "UOMDuracion";

	/** Set Duration UOM	  */
	public void setUOMDuracion (String UOMDuracion);

	/** Get Duration UOM	  */
	public String getUOMDuracion();

    /** Column name UOMIntervalo */
    public static final String COLUMNNAME_UOMIntervalo = "UOMIntervalo";

	/** Set Interval UOM.
	  * Interval UOM
	  */
	public void setUOMIntervalo (String UOMIntervalo);

	/** Get Interval UOM.
	  * Interval UOM
	  */
	public String getUOMIntervalo();

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
