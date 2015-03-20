/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Clasificacion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Clasificacion 
{

    /** TableName=EXME_Clasificacion */
    public static final String Table_Name = "EXME_Clasificacion";

    /** AD_Table_ID=1200032 */
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

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name Antiguedad_Ocupacion */
    public static final String COLUMNNAME_Antiguedad_Ocupacion = "Antiguedad_Ocupacion";

	/** Set Occupation's Antiquity	  */
	public void setAntiguedad_Ocupacion (String Antiguedad_Ocupacion);

	/** Get Occupation's Antiquity	  */
	public String getAntiguedad_Ocupacion();

    /** Column name CambiaClas */
    public static final String COLUMNNAME_CambiaClas = "CambiaClas";

	/** Set Update Classification.
	  * Update Classification
	  */
	public void setCambiaClas (String CambiaClas);

	/** Get Update Classification.
	  * Update Classification
	  */
	public String getCambiaClas();

    /** Column name Comentario_Alimentacion */
    public static final String COLUMNNAME_Comentario_Alimentacion = "Comentario_Alimentacion";

	/** Set Feeding Notes	  */
	public void setComentario_Alimentacion (String Comentario_Alimentacion);

	/** Get Feeding Notes	  */
	public String getComentario_Alimentacion();

    /** Column name Comentario_IngrFamiliar */
    public static final String COLUMNNAME_Comentario_IngrFamiliar = "Comentario_IngrFamiliar";

	/** Set household income Notes	  */
	public void setComentario_IngrFamiliar (String Comentario_IngrFamiliar);

	/** Get household income Notes	  */
	public String getComentario_IngrFamiliar();

    /** Column name Comentario_MatConstruccion */
    public static final String COLUMNNAME_Comentario_MatConstruccion = "Comentario_MatConstruccion";

	/** Set boulding material Notes	  */
	public void setComentario_MatConstruccion (String Comentario_MatConstruccion);

	/** Get boulding material Notes	  */
	public String getComentario_MatConstruccion();

    /** Column name Comentario_NumHabit */
    public static final String COLUMNNAME_Comentario_NumHabit = "Comentario_NumHabit";

	/** Set Rooms Number's Notes.
	  * Notes for the total number of rooms in the house
	  */
	public void setComentario_NumHabit (String Comentario_NumHabit);

	/** Get Rooms Number's Notes.
	  * Notes for the total number of rooms in the house
	  */
	public String getComentario_NumHabit();

    /** Column name Comentario_NumPers */
    public static final String COLUMNNAME_Comentario_NumPers = "Comentario_NumPers";

	/** Set Notes of number of people	  */
	public void setComentario_NumPers (String Comentario_NumPers);

	/** Get Notes of number of people	  */
	public String getComentario_NumPers();

    /** Column name Comentario_Ocupacion */
    public static final String COLUMNNAME_Comentario_Ocupacion = "Comentario_Ocupacion";

	/** Set Notes of occupancy	  */
	public void setComentario_Ocupacion (String Comentario_Ocupacion);

	/** Get Notes of occupancy	  */
	public String getComentario_Ocupacion();

    /** Column name Comentario_Procedencia */
    public static final String COLUMNNAME_Comentario_Procedencia = "Comentario_Procedencia";

	/** Set Notes of provenance	  */
	public void setComentario_Procedencia (String Comentario_Procedencia);

	/** Get Notes of provenance	  */
	public String getComentario_Procedencia();

    /** Column name Comentario_SaludFam */
    public static final String COLUMNNAME_Comentario_SaludFam = "Comentario_SaludFam";

	/** Set Notes of family healt	  */
	public void setComentario_SaludFam (String Comentario_SaludFam);

	/** Get Notes of family healt	  */
	public String getComentario_SaludFam();

    /** Column name Comentario_ServIntra */
    public static final String COLUMNNAME_Comentario_ServIntra = "Comentario_ServIntra";

	/** Set Notes of intrahousehold services	  */
	public void setComentario_ServIntra (String Comentario_ServIntra);

	/** Get Notes of intrahousehold services	  */
	public String getComentario_ServIntra();

    /** Column name Comentario_Tenencia */
    public static final String COLUMNNAME_Comentario_Tenencia = "Comentario_Tenencia";

	/** Set Notes of tenancy	  */
	public void setComentario_Tenencia (String Comentario_Tenencia);

	/** Get Notes of tenancy	  */
	public String getComentario_Tenencia();

    /** Column name Comentario_Vivienda */
    public static final String COLUMNNAME_Comentario_Vivienda = "Comentario_Vivienda";

	/** Set Notes of housing	  */
	public void setComentario_Vivienda (String Comentario_Vivienda);

	/** Get Notes of housing	  */
	public String getComentario_Vivienda();

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

    /** Column name EgrAgua */
    public static final String COLUMNNAME_EgrAgua = "EgrAgua";

	/** Set Water Expense	  */
	public void setEgrAgua (BigDecimal EgrAgua);

	/** Get Water Expense	  */
	public BigDecimal getEgrAgua();

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

    /** Column name EgrLuzCombustible */
    public static final String COLUMNNAME_EgrLuzCombustible = "EgrLuzCombustible";

	/** Set Light and Fuel Expenses	  */
	public void setEgrLuzCombustible (BigDecimal EgrLuzCombustible);

	/** Get Light and Fuel Expenses	  */
	public BigDecimal getEgrLuzCombustible();

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

    /** Column name EgrTransporte */
    public static final String COLUMNNAME_EgrTransporte = "EgrTransporte";

	/** Set Transpotation.
	  * Transpotation
	  */
	public void setEgrTransporte (BigDecimal EgrTransporte);

	/** Get Transpotation.
	  * Transpotation
	  */
	public BigDecimal getEgrTransporte();

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

    /** Column name EsDeConvenio */
    public static final String COLUMNNAME_EsDeConvenio = "EsDeConvenio";

	/** Set Agreement	  */
	public void setEsDeConvenio (boolean EsDeConvenio);

	/** Get Agreement	  */
	public boolean isEsDeConvenio();

    /** Column name EspacioBlanco */
    public static final String COLUMNNAME_EspacioBlanco = "EspacioBlanco";

	/** Set White Space	  */
	public void setEspacioBlanco (String EspacioBlanco);

	/** Get White Space	  */
	public String getEspacioBlanco();

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

    /** Column name EXME_Clasificacion_ID */
    public static final String COLUMNNAME_EXME_Clasificacion_ID = "EXME_Clasificacion_ID";

	/** Set Classification.
	  * Classification
	  */
	public void setEXME_Clasificacion_ID (int EXME_Clasificacion_ID);

	/** Get Classification.
	  * Classification
	  */
	public int getEXME_Clasificacion_ID();

    /** Column name EXME_Estatus_Clas_ID */
    public static final String COLUMNNAME_EXME_Estatus_Clas_ID = "EXME_Estatus_Clas_ID";

	/** Set Status.
	  * Status
	  */
	public void setEXME_Estatus_Clas_ID (int EXME_Estatus_Clas_ID);

	/** Get Status.
	  * Status
	  */
	public int getEXME_Estatus_Clas_ID();

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

    /** Column name EXME_Salarios_Min_Mes_ID */
    public static final String COLUMNNAME_EXME_Salarios_Min_Mes_ID = "EXME_Salarios_Min_Mes_ID";

	/** Set Minimum Monthly Salaries.
	  * Minimum Monthly Salaries
	  */
	public void setEXME_Salarios_Min_Mes_ID (int EXME_Salarios_Min_Mes_ID);

	/** Get Minimum Monthly Salaries.
	  * Minimum Monthly Salaries
	  */
	public int getEXME_Salarios_Min_Mes_ID();

    /** Column name EXME_Servicio_Medico_ID */
    public static final String COLUMNNAME_EXME_Servicio_Medico_ID = "EXME_Servicio_Medico_ID";

	/** Set Medical Service.
	  * Medical Service
	  */
	public void setEXME_Servicio_Medico_ID (int EXME_Servicio_Medico_ID);

	/** Get Medical Service.
	  * Medical Service
	  */
	public int getEXME_Servicio_Medico_ID();

	public I_EXME_Servicio_Medico getEXME_Servicio_Medico() throws RuntimeException;

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

    /** Column name EXME_Vigencia_ID */
    public static final String COLUMNNAME_EXME_Vigencia_ID = "EXME_Vigencia_ID";

	/** Set Validity.
	  * Duration in Years for the Study of a Patient
	  */
	public void setEXME_Vigencia_ID (int EXME_Vigencia_ID);

	/** Get Validity.
	  * Duration in Years for the Study of a Patient
	  */
	public int getEXME_Vigencia_ID();

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

    /** Column name Imprime_Clas_Abierta */
    public static final String COLUMNNAME_Imprime_Clas_Abierta = "Imprime_Clas_Abierta";

	/** Set Imprime_Clas_Abierta	  */
	public void setImprime_Clas_Abierta (String Imprime_Clas_Abierta);

	/** Get Imprime_Clas_Abierta	  */
	public String getImprime_Clas_Abierta();

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
	public void setIsLocked (String IsLocked);

	/** Get Locked.
	  * Locked
	  */
	public String getIsLocked();

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
	public void setNumMiembros (int NumMiembros);

	/** Get Number of Members.
	  * Number of Members
	  */
	public int getNumMiembros();

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

    /** Column name Tipo_Empleo */
    public static final String COLUMNNAME_Tipo_Empleo = "Tipo_Empleo";

	/** Set Job Type.
	  * Job Type
	  */
	public void setTipo_Empleo (String Tipo_Empleo);

	/** Get Job Type.
	  * Job Type
	  */
	public String getTipo_Empleo();

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
