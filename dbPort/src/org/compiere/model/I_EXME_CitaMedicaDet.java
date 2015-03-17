/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CitaMedicaDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CitaMedicaDet 
{

    /** TableName=EXME_CitaMedicaDet */
    public static final String Table_Name = "EXME_CitaMedicaDet";

    /** AD_Table_ID=1200495 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_Org_Dest_ID */
    public static final String COLUMNNAME_AD_Org_Dest_ID = "AD_Org_Dest_ID";

	/** Set Target Organization.
	  * The organization to refer to
	  */
	public void setAD_Org_Dest_ID (int AD_Org_Dest_ID);

	/** Get Target Organization.
	  * The organization to refer to
	  */
	public int getAD_Org_Dest_ID();

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

    /** Column name Aplicar */
    public static final String COLUMNNAME_Aplicar = "Aplicar";

	/** Set Apply Service	  */
	public void setAplicar (boolean Aplicar);

	/** Get Apply Service	  */
	public boolean isAplicar();

    /** Column name Billable */
    public static final String COLUMNNAME_Billable = "Billable";

	/** Set Billable	  */
	public void setBillable (boolean Billable);

	/** Get Billable	  */
	public boolean isBillable();

    /** Column name CantTomar */
    public static final String COLUMNNAME_CantTomar = "CantTomar";

	/** Set Quantity dosis	  */
	public void setCantTomar (BigDecimal CantTomar);

	/** Get Quantity dosis	  */
	public BigDecimal getCantTomar();

    /** Column name ConsultingProvider */
    public static final String COLUMNNAME_ConsultingProvider = "ConsultingProvider";

	/** Set Consulting Provider.
	  * Consulting Provider / Facility
	  */
	public void setConsultingProvider (String ConsultingProvider);

	/** Get Consulting Provider.
	  * Consulting Provider / Facility
	  */
	public String getConsultingProvider();

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

    /** Column name DatePromised */
    public static final String COLUMNNAME_DatePromised = "DatePromised";

	/** Set Date Promised.
	  * Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised);

	/** Get Date Promised.
	  * Date Order was promised
	  */
	public Timestamp getDatePromised();

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

    /** Column name estatusDiag */
    public static final String COLUMNNAME_estatusDiag = "estatusDiag";

	/** Set Diagnosis Status.
	  * Diagnosis Status
	  */
	public void setestatusDiag (String estatusDiag);

	/** Get Diagnosis Status.
	  * Diagnosis Status
	  */
	public String getestatusDiag();

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

    /** Column name EXME_CitaMedicaDet_ID */
    public static final String COLUMNNAME_EXME_CitaMedicaDet_ID = "EXME_CitaMedicaDet_ID";

	/** Set Appointment's execution detail	  */
	public void setEXME_CitaMedicaDet_ID (int EXME_CitaMedicaDet_ID);

	/** Get Appointment's execution detail	  */
	public int getEXME_CitaMedicaDet_ID();

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

    /** Column name EXME_Diagnostico2_ID */
    public static final String COLUMNNAME_EXME_Diagnostico2_ID = "EXME_Diagnostico2_ID";

	/** Set Second Diagnostic.
	  * Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID);

	/** Get Second Diagnostic.
	  * Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID();

    /** Column name EXME_Diagnostico3_ID */
    public static final String COLUMNNAME_EXME_Diagnostico3_ID = "EXME_Diagnostico3_ID";

	/** Set Third Diagnostic.
	  * Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID);

	/** Get Third Diagnostic.
	  * Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID();

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name EXME_Dosis_ID */
    public static final String COLUMNNAME_EXME_Dosis_ID = "EXME_Dosis_ID";

	/** Set Dose	  */
	public void setEXME_Dosis_ID (int EXME_Dosis_ID);

	/** Get Dose	  */
	public int getEXME_Dosis_ID();

	public I_EXME_Dosis getEXME_Dosis() throws RuntimeException;

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Service Facility.
	  * Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Service Facility.
	  * Service Facility
	  */
	public int getEXME_Institucion_ID();

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException;

    /** Column name EXME_Instructions_ID */
    public static final String COLUMNNAME_EXME_Instructions_ID = "EXME_Instructions_ID";

	/** Set Instructions.
	  * General instructions
	  */
	public void setEXME_Instructions_ID (int EXME_Instructions_ID);

	/** Get Instructions.
	  * General instructions
	  */
	public int getEXME_Instructions_ID();

	public I_EXME_Instructions getEXME_Instructions() throws RuntimeException;

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

    /** Column name EXME_Modifiers_ID */
    public static final String COLUMNNAME_EXME_Modifiers_ID = "EXME_Modifiers_ID";

	/** Set EXME_Modifiers_ID	  */
	public void setEXME_Modifiers_ID (int EXME_Modifiers_ID);

	/** Get EXME_Modifiers_ID	  */
	public int getEXME_Modifiers_ID();

	public I_EXME_Modifiers getEXME_Modifiers() throws RuntimeException;

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException;

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

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException;

    /** Column name ISCC */
    public static final String COLUMNNAME_ISCC = "ISCC";

	/** Set ISCC	  */
	public void setISCC (boolean ISCC);

	/** Get ISCC	  */
	public boolean isCC();

    /** Column name IsExternal */
    public static final String COLUMNNAME_IsExternal = "IsExternal";

	/** Set External.
	  * External
	  */
	public void setIsExternal (boolean IsExternal);

	/** Get External.
	  * External
	  */
	public boolean isExternal();

    /** Column name IsInstruction */
    public static final String COLUMNNAME_IsInstruction = "IsInstruction";

	/** Set Is Instruction.
	  * Indicates if is an instruction
	  */
	public void setIsInstruction (boolean IsInstruction);

	/** Get Is Instruction.
	  * Indicates if is an instruction
	  */
	public boolean isInstruction();

    /** Column name IsProblem */
    public static final String COLUMNNAME_IsProblem = "IsProblem";

	/** Set IsProblem.
	  * Is Problem
	  */
	public void setIsProblem (boolean IsProblem);

	/** Get IsProblem.
	  * Is Problem
	  */
	public boolean isProblem();

    /** Column name IsService */
    public static final String COLUMNNAME_IsService = "IsService";

	/** Set Is Service.
	  * Is Service
	  */
	public void setIsService (boolean IsService);

	/** Get Is Service.
	  * Is Service
	  */
	public boolean isService();

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

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name M_Warehouse_Sol_ID */
    public static final String COLUMNNAME_M_Warehouse_Sol_ID = "M_Warehouse_Sol_ID";

	/** Set Requesting Warehouse.
	  * Requesting Warehouse
	  */
	public void setM_Warehouse_Sol_ID (int M_Warehouse_Sol_ID);

	/** Get Requesting Warehouse.
	  * Requesting Warehouse
	  */
	public int getM_Warehouse_Sol_ID();

    /** Column name NumDias */
    public static final String COLUMNNAME_NumDias = "NumDias";

	/** Set Number of days	  */
	public void setNumDias (int NumDias);

	/** Get Number of days	  */
	public int getNumDias();

    /** Column name OtherInstructions */
    public static final String COLUMNNAME_OtherInstructions = "OtherInstructions";

	/** Set Other Instructions.
	  * Other Instructions
	  */
	public void setOtherInstructions (String OtherInstructions);

	/** Get Other Instructions.
	  * Other Instructions
	  */
	public String getOtherInstructions();

    /** Column name PriorityRule */
    public static final String COLUMNNAME_PriorityRule = "PriorityRule";

	/** Set Priority.
	  * Priority of a document
	  */
	public void setPriorityRule (String PriorityRule);

	/** Get Priority.
	  * Priority of a document
	  */
	public String getPriorityRule();

    /** Column name ProductType */
    public static final String COLUMNNAME_ProductType = "ProductType";

	/** Set Product Type.
	  * Type of product
	  */
	public void setProductType (String ProductType);

	/** Get Product Type.
	  * Type of product
	  */
	public String getProductType();

    /** Column name Protocolo */
    public static final String COLUMNNAME_Protocolo = "Protocolo";

	/** Set Protocol.
	  * Protocol
	  */
	public void setProtocolo (String Protocolo);

	/** Get Protocol.
	  * Protocol
	  */
	public String getProtocolo();

    /** Column name Proveedor */
    public static final String COLUMNNAME_Proveedor = "Proveedor";

	/** Set Supplier	  */
	public void setProveedor (String Proveedor);

	/** Get Supplier	  */
	public String getProveedor();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Quantity.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Quantity.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name Resurtidos */
    public static final String COLUMNNAME_Resurtidos = "Resurtidos";

	/** Set Refills	  */
	public void setResurtidos (BigDecimal Resurtidos);

	/** Get Refills	  */
	public BigDecimal getResurtidos();

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

    /** Column name TipoSurtido */
    public static final String COLUMNNAME_TipoSurtido = "TipoSurtido";

	/** Set Dispense as written.
	  * Dispense as written  (DAW)
	  */
	public void setTipoSurtido (String TipoSurtido);

	/** Get Dispense as written.
	  * Dispense as written  (DAW)
	  */
	public String getTipoSurtido();

    /** Column name TomadoCasa */
    public static final String COLUMNNAME_TomadoCasa = "TomadoCasa";

	/** Set Taken at home	  */
	public void setTomadoCasa (boolean TomadoCasa);

	/** Get Taken at home	  */
	public boolean isTomadoCasa();

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

    /** Column name VecesDia */
    public static final String COLUMNNAME_VecesDia = "VecesDia";

	/** Set Day Timing	  */
	public void setVecesDia (int VecesDia);

	/** Get Day Timing	  */
	public int getVecesDia();
}
