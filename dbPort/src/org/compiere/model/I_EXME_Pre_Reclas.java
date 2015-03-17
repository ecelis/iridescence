/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Pre_Reclas
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Pre_Reclas 
{

    /** TableName=EXME_Pre_Reclas */
    public static final String Table_Name = "EXME_Pre_Reclas";

    /** AD_Table_ID=1200054 */
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

	public I_EXME_NumEnferm getEXME_NumEnferm() throws RuntimeException;

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

    /** Column name EXME_Pre_Reclas_ID */
    public static final String COLUMNNAME_EXME_Pre_Reclas_ID = "EXME_Pre_Reclas_ID";

	/** Set Pre-reclassification	  */
	public void setEXME_Pre_Reclas_ID (int EXME_Pre_Reclas_ID);

	/** Get Pre-reclassification	  */
	public int getEXME_Pre_Reclas_ID();

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
