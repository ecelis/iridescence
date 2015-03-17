/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Pre_Reclas
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Pre_Reclas 
{

    /** TableName=I_EXME_Pre_Reclas */
    public static final String Table_Name = "I_EXME_Pre_Reclas";

    /** AD_Table_ID=1200090 */
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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name AD_OrgTrx_Value */
    public static final String COLUMNNAME_AD_OrgTrx_Value = "AD_OrgTrx_Value";

	/** Set AD_OrgTrx_Value.
	  * Transactional Organization Value
	  */
	public void setAD_OrgTrx_Value (int AD_OrgTrx_Value);

	/** Get AD_OrgTrx_Value.
	  * Transactional Organization Value
	  */
	public int getAD_OrgTrx_Value();

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

    /** Column name AD_User_Name */
    public static final String COLUMNNAME_AD_User_Name = "AD_User_Name";

	/** Set User Name.
	  * User Name
	  */
	public void setAD_User_Name (String AD_User_Name);

	/** Get User Name.
	  * User Name
	  */
	public String getAD_User_Name();

    /** Column name CambiaClas_Name */
    public static final String COLUMNNAME_CambiaClas_Name = "CambiaClas_Name";

	/** Set Update Classification_Name.
	  * Update Classification_Name
	  */
	public void setCambiaClas_Name (String CambiaClas_Name);

	/** Get Update Classification_Name.
	  * Update Classification_Name
	  */
	public String getCambiaClas_Name();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DateVaidTo */
    public static final String COLUMNNAME_DateVaidTo = "DateVaidTo";

	/** Set Valid Date.
	  * Valid Date
	  */
	public void setDateVaidTo (Timestamp DateVaidTo);

	/** Get Valid Date.
	  * Valid Date
	  */
	public Timestamp getDateVaidTo();

    /** Column name EgrAlimentacion */
    public static final String COLUMNNAME_EgrAlimentacion = "EgrAlimentacion";

	/** Set Feeding Expense.
	  * Feeding Expense
	  */
	public void setEgrAlimentacion (BigDecimal EgrAlimentacion);

	/** Get Feeding Expense.
	  * Feeding Expense
	  */
	public BigDecimal getEgrAlimentacion();

    /** Column name EgrOtros */
    public static final String COLUMNNAME_EgrOtros = "EgrOtros";

	/** Set Other expense.
	  * Other expense
	  */
	public void setEgrOtros (BigDecimal EgrOtros);

	/** Get Other expense.
	  * Other expense
	  */
	public BigDecimal getEgrOtros();

    /** Column name EgrServicios */
    public static final String COLUMNNAME_EgrServicios = "EgrServicios";

	/** Set Service Expense.
	  * Service Expense
	  */
	public void setEgrServicios (BigDecimal EgrServicios);

	/** Get Service Expense.
	  * Service Expense
	  */
	public BigDecimal getEgrServicios();

    /** Column name EgrVivienda */
    public static final String COLUMNNAME_EgrVivienda = "EgrVivienda";

	/** Set House Expense.
	  * House Expense
	  */
	public void setEgrVivienda (BigDecimal EgrVivienda);

	/** Get House Expense.
	  * House Expense
	  */
	public BigDecimal getEgrVivienda();

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

    /** Column name EXME_ClasCliente_ID */
    public static final String COLUMNNAME_EXME_ClasCliente_ID = "EXME_ClasCliente_ID";

	/** Set Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID);

	/** Get Classification and Customer	  */
	public int getEXME_ClasCliente_ID();

	public I_EXME_ClasCliente getEXME_ClasCliente() throws RuntimeException;

    /** Column name EXME_ClasCliente_Value */
    public static final String COLUMNNAME_EXME_ClasCliente_Value = "EXME_ClasCliente_Value";

	/** Set Value of Classification and Customer.
	  * Value of Classification and Customer
	  */
	public void setEXME_ClasCliente_Value (String EXME_ClasCliente_Value);

	/** Get Value of Classification and Customer.
	  * Value of Classification and Customer
	  */
	public String getEXME_ClasCliente_Value();

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

    /** Column name EXME_MatConst_ID */
    public static final String COLUMNNAME_EXME_MatConst_ID = "EXME_MatConst_ID";

	/** Set Construction Material.
	  * Construction Material
	  */
	public void setEXME_MatConst_ID (int EXME_MatConst_ID);

	/** Get Construction Material.
	  * Construction Material
	  */
	public int getEXME_MatConst_ID();

    /** Column name EXME_Material_Const_Value */
    public static final String COLUMNNAME_EXME_Material_Const_Value = "EXME_Material_Const_Value";

	/** Set Construction Material's Value.
	  * Construction Material's Value
	  */
	public void setEXME_Material_Const_Value (String EXME_Material_Const_Value);

	/** Get Construction Material's Value.
	  * Construction Material's Value
	  */
	public String getEXME_Material_Const_Value();

    /** Column name EXME_NumEnferm_ID */
    public static final String COLUMNNAME_EXME_NumEnferm_ID = "EXME_NumEnferm_ID";

	/** Set Number of Sick People.
	  * Number of Sick People that  will be inside the patient's house
	  */
	public void setEXME_NumEnferm_ID (int EXME_NumEnferm_ID);

	/** Get Number of Sick People.
	  * Number of Sick People that  will be inside the patient's house
	  */
	public int getEXME_NumEnferm_ID();

	public I_EXME_NumEnferm getEXME_NumEnferm() throws RuntimeException;

    /** Column name EXME_NumEnferm_Value */
    public static final String COLUMNNAME_EXME_NumEnferm_Value = "EXME_NumEnferm_Value";

	/** Set EXME_NumEnferm_Value.
	  * Sickness Number
	  */
	public void setEXME_NumEnferm_Value (String EXME_NumEnferm_Value);

	/** Get EXME_NumEnferm_Value.
	  * Sickness Number
	  */
	public String getEXME_NumEnferm_Value();

    /** Column name EXME_NumHabts_ID */
    public static final String COLUMNNAME_EXME_NumHabts_ID = "EXME_NumHabts_ID";

	/** Set Number of Rooms.
	  * Number of Rooms
	  */
	public void setEXME_NumHabts_ID (int EXME_NumHabts_ID);

	/** Get Number of Rooms.
	  * Number of Rooms
	  */
	public int getEXME_NumHabts_ID();

	public I_EXME_NumHabts getEXME_NumHabts() throws RuntimeException;

    /** Column name EXME_NumHabts_Value */
    public static final String COLUMNNAME_EXME_NumHabts_Value = "EXME_NumHabts_Value";

	/** Set Number of Rooms_Value.
	  * Number of Rooms_Value
	  */
	public void setEXME_NumHabts_Value (String EXME_NumHabts_Value);

	/** Get Number of Rooms_Value.
	  * Number of Rooms_Value
	  */
	public String getEXME_NumHabts_Value();

    /** Column name EXME_NumPers_ID */
    public static final String COLUMNNAME_EXME_NumPers_ID = "EXME_NumPers_ID";

	/** Set Number of Persons.
	  * Number of Persons
	  */
	public void setEXME_NumPers_ID (int EXME_NumPers_ID);

	/** Get Number of Persons.
	  * Number of Persons
	  */
	public int getEXME_NumPers_ID();

	public I_EXME_NumPers getEXME_NumPers() throws RuntimeException;

    /** Column name EXME_NumPers_Value */
    public static final String COLUMNNAME_EXME_NumPers_Value = "EXME_NumPers_Value";

	/** Set Number of Persons_Value.
	  * Number of Persons_Value
	  */
	public void setEXME_NumPers_Value (String EXME_NumPers_Value);

	/** Get Number of Persons_Value.
	  * Number of Persons_Value
	  */
	public String getEXME_NumPers_Value();

    /** Column name EXME_Ocupacion_Clas_ID */
    public static final String COLUMNNAME_EXME_Ocupacion_Clas_ID = "EXME_Ocupacion_Clas_ID";

	/** Set Classified Ocupation.
	  * Classified Ocupation
	  */
	public void setEXME_Ocupacion_Clas_ID (int EXME_Ocupacion_Clas_ID);

	/** Get Classified Ocupation.
	  * Classified Ocupation
	  */
	public int getEXME_Ocupacion_Clas_ID();

	public I_EXME_Ocupacion_Clas getEXME_Ocupacion_Clas() throws RuntimeException;

    /** Column name EXME_Ocupacion_Clas_Value */
    public static final String COLUMNNAME_EXME_Ocupacion_Clas_Value = "EXME_Ocupacion_Clas_Value";

	/** Set Classified Ocupation_Value	  */
	public void setEXME_Ocupacion_Clas_Value (String EXME_Ocupacion_Clas_Value);

	/** Get Classified Ocupation_Value	  */
	public String getEXME_Ocupacion_Clas_Value();

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

    /** Column name EXME_Paciente_Value */
    public static final String COLUMNNAME_EXME_Paciente_Value = "EXME_Paciente_Value";

	/** Set Patient History Number.
	  * Patient History Number
	  */
	public void setEXME_Paciente_Value (String EXME_Paciente_Value);

	/** Get Patient History Number.
	  * Patient History Number
	  */
	public String getEXME_Paciente_Value();

    /** Column name EXME_Pre_Reclas_ID */
    public static final String COLUMNNAME_EXME_Pre_Reclas_ID = "EXME_Pre_Reclas_ID";

	/** Set Pre-reclassification	  */
	public void setEXME_Pre_Reclas_ID (int EXME_Pre_Reclas_ID);

	/** Get Pre-reclassification	  */
	public int getEXME_Pre_Reclas_ID();

	public I_EXME_Pre_Reclas getEXME_Pre_Reclas() throws RuntimeException;

    /** Column name EXME_Procedencia_ID */
    public static final String COLUMNNAME_EXME_Procedencia_ID = "EXME_Procedencia_ID";

	/** Set Provenance.
	  * Provenance
	  */
	public void setEXME_Procedencia_ID (int EXME_Procedencia_ID);

	/** Get Provenance.
	  * Provenance
	  */
	public int getEXME_Procedencia_ID();

	public I_EXME_Procedencia getEXME_Procedencia() throws RuntimeException;

    /** Column name EXME_Procedencia_Value */
    public static final String COLUMNNAME_EXME_Procedencia_Value = "EXME_Procedencia_Value";

	/** Set Provenance_Value	  */
	public void setEXME_Procedencia_Value (String EXME_Procedencia_Value);

	/** Get Provenance_Value	  */
	public String getEXME_Procedencia_Value();

    /** Column name EXME_ServPublico_ID */
    public static final String COLUMNNAME_EXME_ServPublico_ID = "EXME_ServPublico_ID";

	/** Set Public Services.
	  * Public Services
	  */
	public void setEXME_ServPublico_ID (int EXME_ServPublico_ID);

	/** Get Public Services.
	  * Public Services
	  */
	public int getEXME_ServPublico_ID();

	public I_EXME_ServPublico getEXME_ServPublico() throws RuntimeException;

    /** Column name EXME_ServPublico_Value */
    public static final String COLUMNNAME_EXME_ServPublico_Value = "EXME_ServPublico_Value";

	/** Set Public Services_Value	  */
	public void setEXME_ServPublico_Value (String EXME_ServPublico_Value);

	/** Get Public Services_Value	  */
	public String getEXME_ServPublico_Value();

    /** Column name EXME_Tenencia_ID */
    public static final String COLUMNNAME_EXME_Tenencia_ID = "EXME_Tenencia_ID";

	/** Set Tenancy.
	  * Housing tenure
	  */
	public void setEXME_Tenencia_ID (int EXME_Tenencia_ID);

	/** Get Tenancy.
	  * Housing tenure
	  */
	public int getEXME_Tenencia_ID();

	public I_EXME_Tenencia getEXME_Tenencia() throws RuntimeException;

    /** Column name EXME_Tenencia_Value */
    public static final String COLUMNNAME_EXME_Tenencia_Value = "EXME_Tenencia_Value";

	/** Set Tenure_Value	  */
	public void setEXME_Tenencia_Value (String EXME_Tenencia_Value);

	/** Get Tenure_Value	  */
	public String getEXME_Tenencia_Value();

    /** Column name EXME_TipoVivienda_ID */
    public static final String COLUMNNAME_EXME_TipoVivienda_ID = "EXME_TipoVivienda_ID";

	/** Set Housing type.
	  * Housing type
	  */
	public void setEXME_TipoVivienda_ID (int EXME_TipoVivienda_ID);

	/** Get Housing type.
	  * Housing type
	  */
	public int getEXME_TipoVivienda_ID();

	public I_EXME_TipoVivienda getEXME_TipoVivienda() throws RuntimeException;

    /** Column name EXME_TipoVivienda_Value */
    public static final String COLUMNNAME_EXME_TipoVivienda_Value = "EXME_TipoVivienda_Value";

	/** Set Housing type_Value	  */
	public void setEXME_TipoVivienda_Value (String EXME_TipoVivienda_Value);

	/** Get Housing type_Value	  */
	public String getEXME_TipoVivienda_Value();

    /** Column name EXME_Zona_ID */
    public static final String COLUMNNAME_EXME_Zona_ID = "EXME_Zona_ID";

	/** Set Zone.
	  * Zone
	  */
	public void setEXME_Zona_ID (int EXME_Zona_ID);

	/** Get Zone.
	  * Zone
	  */
	public int getEXME_Zona_ID();

	public I_EXME_Zona getEXME_Zona() throws RuntimeException;

    /** Column name EXME_Zona_Value */
    public static final String COLUMNNAME_EXME_Zona_Value = "EXME_Zona_Value";

	/** Set Zone_Value	  */
	public void setEXME_Zona_Value (String EXME_Zona_Value);

	/** Get Zone_Value	  */
	public String getEXME_Zona_Value();

    /** Column name Expediente */
    public static final String COLUMNNAME_Expediente = "Expediente";

	/** Set Medical Record.
	  * Medical Record
	  */
	public void setExpediente (String Expediente);

	/** Get Medical Record.
	  * Medical Record
	  */
	public String getExpediente();

    /** Column name Fecha_Ult_Act */
    public static final String COLUMNNAME_Fecha_Ult_Act = "Fecha_Ult_Act";

	/** Set Date of Last Change.
	  * Date of Last Change
	  */
	public void setFecha_Ult_Act (Timestamp Fecha_Ult_Act);

	/** Get Date of Last Change.
	  * Date of Last Change
	  */
	public Timestamp getFecha_Ult_Act();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_Pre_Reclas_ID */
    public static final String COLUMNNAME_I_EXME_Pre_Reclas_ID = "I_EXME_Pre_Reclas_ID";

	/** Set I_EXME_Pre_Reclas_ID	  */
	public void setI_EXME_Pre_Reclas_ID (int I_EXME_Pre_Reclas_ID);

	/** Get I_EXME_Pre_Reclas_ID	  */
	public int getI_EXME_Pre_Reclas_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name IngHijos */
    public static final String COLUMNNAME_IngHijos = "IngHijos";

	/** Set Children income.
	  * Children income
	  */
	public void setIngHijos (BigDecimal IngHijos);

	/** Get Children income.
	  * Children income
	  */
	public BigDecimal getIngHijos();

    /** Column name IngJefeFam */
    public static final String COLUMNNAME_IngJefeFam = "IngJefeFam";

	/** Set Income of Family Head.
	  * Income of Family Head
	  */
	public void setIngJefeFam (BigDecimal IngJefeFam);

	/** Get Income of Family Head.
	  * Income of Family Head
	  */
	public BigDecimal getIngJefeFam();

    /** Column name IngOtros */
    public static final String COLUMNNAME_IngOtros = "IngOtros";

	/** Set Other income.
	  * Other income
	  */
	public void setIngOtros (BigDecimal IngOtros);

	/** Get Other income.
	  * Other income
	  */
	public BigDecimal getIngOtros();

    /** Column name IsLocked */
    public static final String COLUMNNAME_IsLocked = "IsLocked";

	/** Set Locked.
	  * Locked
	  */
	public void setIsLocked (boolean IsLocked);

	/** Get Locked.
	  * Locked
	  */
	public boolean isLocked();

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

    /** Column name NumMiembros */
    public static final String COLUMNNAME_NumMiembros = "NumMiembros";

	/** Set Number of Members.
	  * Number of Members
	  */
	public void setNumMiembros (BigDecimal NumMiembros);

	/** Get Number of Members.
	  * Number of Members
	  */
	public BigDecimal getNumMiembros();

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

    /** Column name PorcAlimentacion */
    public static final String COLUMNNAME_PorcAlimentacion = "PorcAlimentacion";

	/** Set Percentage of Feeding.
	  * Percentage of Feeding
	  */
	public void setPorcAlimentacion (BigDecimal PorcAlimentacion);

	/** Get Percentage of Feeding.
	  * Percentage of Feeding
	  */
	public BigDecimal getPorcAlimentacion();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name PtsEgre */
    public static final String COLUMNNAME_PtsEgre = "PtsEgre";

	/** Set Discharge Points.
	  * Discharge Points
	  */
	public void setPtsEgre (BigDecimal PtsEgre);

	/** Get Discharge Points.
	  * Discharge Points
	  */
	public BigDecimal getPtsEgre();

    /** Column name PtsIngresos */
    public static final String COLUMNNAME_PtsIngresos = "PtsIngresos";

	/** Set Income Points.
	  * Income Points
	  */
	public void setPtsIngresos (BigDecimal PtsIngresos);

	/** Get Income Points.
	  * Income Points
	  */
	public BigDecimal getPtsIngresos();

    /** Column name PtsMatConst */
    public static final String COLUMNNAME_PtsMatConst = "PtsMatConst";

	/** Set Construction Equipment Points.
	  * Construction Equipment Points
	  */
	public void setPtsMatConst (BigDecimal PtsMatConst);

	/** Get Construction Equipment Points.
	  * Construction Equipment Points
	  */
	public BigDecimal getPtsMatConst();

    /** Column name PtsNumEnferm */
    public static final String COLUMNNAME_PtsNumEnferm = "PtsNumEnferm";

	/** Set Number of Patients Points.
	  * Number of Patients Points
	  */
	public void setPtsNumEnferm (BigDecimal PtsNumEnferm);

	/** Get Number of Patients Points.
	  * Number of Patients Points
	  */
	public BigDecimal getPtsNumEnferm();

    /** Column name PtsNumHabts */
    public static final String COLUMNNAME_PtsNumHabts = "PtsNumHabts";

	/** Set Number of Rooms Points.
	  * Number of Rooms Points
	  */
	public void setPtsNumHabts (BigDecimal PtsNumHabts);

	/** Get Number of Rooms Points.
	  * Number of Rooms Points
	  */
	public BigDecimal getPtsNumHabts();

    /** Column name PtsNumPers */
    public static final String COLUMNNAME_PtsNumPers = "PtsNumPers";

	/** Set Number of People Points.
	  * Number of People Points
	  */
	public void setPtsNumPers (BigDecimal PtsNumPers);

	/** Get Number of People Points.
	  * Number of People Points
	  */
	public BigDecimal getPtsNumPers();

    /** Column name PtsOcupacion */
    public static final String COLUMNNAME_PtsOcupacion = "PtsOcupacion";

	/** Set Occupation Points.
	  * Occupation Points
	  */
	public void setPtsOcupacion (BigDecimal PtsOcupacion);

	/** Get Occupation Points.
	  * Occupation Points
	  */
	public BigDecimal getPtsOcupacion();

    /** Column name PtsProcedencia */
    public static final String COLUMNNAME_PtsProcedencia = "PtsProcedencia";

	/** Set Origin Points.
	  * Origin Points
	  */
	public void setPtsProcedencia (BigDecimal PtsProcedencia);

	/** Get Origin Points.
	  * Origin Points
	  */
	public BigDecimal getPtsProcedencia();

    /** Column name PtsServPublico */
    public static final String COLUMNNAME_PtsServPublico = "PtsServPublico";

	/** Set Public Service Points.
	  * Public Service Points
	  */
	public void setPtsServPublico (BigDecimal PtsServPublico);

	/** Get Public Service Points.
	  * Public Service Points
	  */
	public BigDecimal getPtsServPublico();

    /** Column name PtsTenencia */
    public static final String COLUMNNAME_PtsTenencia = "PtsTenencia";

	/** Set Possession Points.
	  * Possession Points
	  */
	public void setPtsTenencia (BigDecimal PtsTenencia);

	/** Get Possession Points.
	  * Possession Points
	  */
	public BigDecimal getPtsTenencia();

    /** Column name PtsTipoVivienda */
    public static final String COLUMNNAME_PtsTipoVivienda = "PtsTipoVivienda";

	/** Set Type of House Points.
	  * Type of House Points
	  */
	public void setPtsTipoVivienda (BigDecimal PtsTipoVivienda);

	/** Get Type of House Points.
	  * Type of House Points
	  */
	public BigDecimal getPtsTipoVivienda();

    /** Column name PtsZona */
    public static final String COLUMNNAME_PtsZona = "PtsZona";

	/** Set Zone Points.
	  * Zone Points
	  */
	public void setPtsZona (BigDecimal PtsZona);

	/** Get Zone Points.
	  * Zone Points
	  */
	public BigDecimal getPtsZona();

    /** Column name TotalEgr */
    public static final String COLUMNNAME_TotalEgr = "TotalEgr";

	/** Set Total Expense.
	  * Total Expense
	  */
	public void setTotalEgr (BigDecimal TotalEgr);

	/** Get Total Expense.
	  * Total Expense
	  */
	public BigDecimal getTotalEgr();

    /** Column name TotalIng */
    public static final String COLUMNNAME_TotalIng = "TotalIng";

	/** Set Total Income.
	  * Total Income
	  */
	public void setTotalIng (BigDecimal TotalIng);

	/** Get Total Income.
	  * Total Income
	  */
	public BigDecimal getTotalIng();

    /** Column name TotalPts */
    public static final String COLUMNNAME_TotalPts = "TotalPts";

	/** Set Total Points.
	  * Total Points
	  */
	public void setTotalPts (BigDecimal TotalPts);

	/** Get Total Points.
	  * Total Points
	  */
	public BigDecimal getTotalPts();
}
