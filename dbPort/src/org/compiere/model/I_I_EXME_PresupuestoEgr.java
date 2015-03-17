/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_PresupuestoEgr
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_I_EXME_PresupuestoEgr 
{

    /** TableName=I_EXME_PresupuestoEgr */
    public static final String Table_Name = "I_EXME_PresupuestoEgr";

    /** AD_Table_ID=1201353 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name ActInsName */
    public static final String COLUMNNAME_ActInsName = "ActInsName";

	/** Set Name "Institutional Activity"	  */
	public void setActInsName (String ActInsName);

	/** Get Name "Institutional Activity"	  */
	public String getActInsName();

    /** Column name ActInsValue */
    public static final String COLUMNNAME_ActInsValue = "ActInsValue";

	/** Set Value  "Institutional Activity"	  */
	public void setActInsValue (String ActInsValue);

	/** Get Value  "Institutional Activity"	  */
	public String getActInsValue();

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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name AmountAbr */
    public static final String COLUMNNAME_AmountAbr = "AmountAbr";

	/** Set Amount April.
	  * Amount April
	  */
	public void setAmountAbr (BigDecimal AmountAbr);

	/** Get Amount April.
	  * Amount April
	  */
	public BigDecimal getAmountAbr();

    /** Column name AmountAgo */
    public static final String COLUMNNAME_AmountAgo = "AmountAgo";

	/** Set Amount August.
	  * Amount August
	  */
	public void setAmountAgo (BigDecimal AmountAgo);

	/** Get Amount August.
	  * Amount August
	  */
	public BigDecimal getAmountAgo();

    /** Column name AmountDic */
    public static final String COLUMNNAME_AmountDic = "AmountDic";

	/** Set Amount December.
	  * Amount December
	  */
	public void setAmountDic (BigDecimal AmountDic);

	/** Get Amount December.
	  * Amount December
	  */
	public BigDecimal getAmountDic();

    /** Column name AmountEne */
    public static final String COLUMNNAME_AmountEne = "AmountEne";

	/** Set Amount January.
	  * Amount January
	  */
	public void setAmountEne (BigDecimal AmountEne);

	/** Get Amount January.
	  * Amount January
	  */
	public BigDecimal getAmountEne();

    /** Column name AmountFeb */
    public static final String COLUMNNAME_AmountFeb = "AmountFeb";

	/** Set Amount February.
	  * Amount February
	  */
	public void setAmountFeb (BigDecimal AmountFeb);

	/** Get Amount February.
	  * Amount February
	  */
	public BigDecimal getAmountFeb();

    /** Column name AmountJul */
    public static final String COLUMNNAME_AmountJul = "AmountJul";

	/** Set Amount July.
	  * Amount July
	  */
	public void setAmountJul (BigDecimal AmountJul);

	/** Get Amount July.
	  * Amount July
	  */
	public BigDecimal getAmountJul();

    /** Column name AmountJun */
    public static final String COLUMNNAME_AmountJun = "AmountJun";

	/** Set Amount June.
	  * Amount June
	  */
	public void setAmountJun (BigDecimal AmountJun);

	/** Get Amount June.
	  * Amount June
	  */
	public BigDecimal getAmountJun();

    /** Column name AmountMar */
    public static final String COLUMNNAME_AmountMar = "AmountMar";

	/** Set Amount March.
	  * Amount March
	  */
	public void setAmountMar (BigDecimal AmountMar);

	/** Get Amount March.
	  * Amount March
	  */
	public BigDecimal getAmountMar();

    /** Column name AmountMay */
    public static final String COLUMNNAME_AmountMay = "AmountMay";

	/** Set Amount May.
	  * Amount May
	  */
	public void setAmountMay (BigDecimal AmountMay);

	/** Get Amount May.
	  * Amount May
	  */
	public BigDecimal getAmountMay();

    /** Column name AmountNov */
    public static final String COLUMNNAME_AmountNov = "AmountNov";

	/** Set Amount November.
	  * Amount November
	  */
	public void setAmountNov (BigDecimal AmountNov);

	/** Get Amount November.
	  * Amount November
	  */
	public BigDecimal getAmountNov();

    /** Column name AmountOct */
    public static final String COLUMNNAME_AmountOct = "AmountOct";

	/** Set Amount October.
	  * Amount October
	  */
	public void setAmountOct (BigDecimal AmountOct);

	/** Get Amount October.
	  * Amount October
	  */
	public BigDecimal getAmountOct();

    /** Column name AmountSep */
    public static final String COLUMNNAME_AmountSep = "AmountSep";

	/** Set Amount September.
	  * Amount September
	  */
	public void setAmountSep (BigDecimal AmountSep);

	/** Get Amount September.
	  * Amount September
	  */
	public BigDecimal getAmountSep();

    /** Column name Codigo */
    public static final String COLUMNNAME_Codigo = "Codigo";

	/** Set Code	  */
	public void setCodigo (String Codigo);

	/** Get Code	  */
	public String getCodigo();

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_Region_ID */
    public static final String COLUMNNAME_C_Region_ID = "C_Region_ID";

	/** Set Region.
	  * Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID);

	/** Get Region.
	  * Identifies a geographical Region
	  */
	public int getC_Region_ID();

	public I_C_Region getC_Region() throws RuntimeException;

    /** Column name C_Year_ID */
    public static final String COLUMNNAME_C_Year_ID = "C_Year_ID";

	/** Set Year.
	  * Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID);

	/** Get Year.
	  * Calendar Year
	  */
	public int getC_Year_ID();

	public I_C_Year getC_Year() throws RuntimeException;

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

    /** Column name Entidad */
    public static final String COLUMNNAME_Entidad = "Entidad";

	/** Set State.
	  * State
	  */
	public void setEntidad (String Entidad);

	/** Get State.
	  * State
	  */
	public String getEntidad();

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

    /** Column name EXME_ActInstitucional_ID */
    public static final String COLUMNNAME_EXME_ActInstitucional_ID = "EXME_ActInstitucional_ID";

	/** Set Institutional Activity	  */
	public void setEXME_ActInstitucional_ID (int EXME_ActInstitucional_ID);

	/** Get Institutional Activity	  */
	public int getEXME_ActInstitucional_ID();

	public I_EXME_ActInstitucional getEXME_ActInstitucional() throws RuntimeException;

    /** Column name EXME_ClasFuncional_Fun_ID */
    public static final String COLUMNNAME_EXME_ClasFuncional_Fun_ID = "EXME_ClasFuncional_Fun_ID";

	/** Set Function	  */
	public void setEXME_ClasFuncional_Fun_ID (int EXME_ClasFuncional_Fun_ID);

	/** Get Function	  */
	public int getEXME_ClasFuncional_Fun_ID();

    /** Column name EXME_ClasFuncional_ID */
    public static final String COLUMNNAME_EXME_ClasFuncional_ID = "EXME_ClasFuncional_ID";

	/** Set Functional classification	  */
	public void setEXME_ClasFuncional_ID (int EXME_ClasFuncional_ID);

	/** Get Functional classification	  */
	public int getEXME_ClasFuncional_ID();

	public I_EXME_ClasFuncional getEXME_ClasFuncional() throws RuntimeException;

    /** Column name EXME_ClasFuncional_Sfu_ID */
    public static final String COLUMNNAME_EXME_ClasFuncional_Sfu_ID = "EXME_ClasFuncional_Sfu_ID";

	/** Set Subfunction	  */
	public void setEXME_ClasFuncional_Sfu_ID (int EXME_ClasFuncional_Sfu_ID);

	/** Get Subfunction	  */
	public int getEXME_ClasFuncional_Sfu_ID();

    /** Column name EXME_FteFinanciamiento_ID */
    public static final String COLUMNNAME_EXME_FteFinanciamiento_ID = "EXME_FteFinanciamiento_ID";

	/** Set Source of Funding.
	  * Source of Funding
	  */
	public void setEXME_FteFinanciamiento_ID (int EXME_FteFinanciamiento_ID);

	/** Get Source of Funding.
	  * Source of Funding
	  */
	public int getEXME_FteFinanciamiento_ID();

	public I_EXME_FteFinanciamiento getEXME_FteFinanciamiento() throws RuntimeException;

    /** Column name EXME_PartidaPres_ID */
    public static final String COLUMNNAME_EXME_PartidaPres_ID = "EXME_PartidaPres_ID";

	/** Set Budget Item.
	  * Budget Item
	  */
	public void setEXME_PartidaPres_ID (int EXME_PartidaPres_ID);

	/** Get Budget Item.
	  * Budget Item
	  */
	public int getEXME_PartidaPres_ID();

	public I_EXME_PartidaPres getEXME_PartidaPres() throws RuntimeException;

    /** Column name EXME_PresupuestoEgr_ID */
    public static final String COLUMNNAME_EXME_PresupuestoEgr_ID = "EXME_PresupuestoEgr_ID";

	/** Set Egress budget	  */
	public void setEXME_PresupuestoEgr_ID (int EXME_PresupuestoEgr_ID);

	/** Get Egress budget	  */
	public int getEXME_PresupuestoEgr_ID();

	public I_EXME_PresupuestoEgr getEXME_PresupuestoEgr() throws RuntimeException;

    /** Column name EXME_PresupuestoModif_ID */
    public static final String COLUMNNAME_EXME_PresupuestoModif_ID = "EXME_PresupuestoModif_ID";

	/** Set Reallocation	  */
	public void setEXME_PresupuestoModif_ID (int EXME_PresupuestoModif_ID);

	/** Get Reallocation	  */
	public int getEXME_PresupuestoModif_ID();

	public I_EXME_PresupuestoModif getEXME_PresupuestoModif() throws RuntimeException;

    /** Column name EXME_ProgInstitucional_ID */
    public static final String COLUMNNAME_EXME_ProgInstitucional_ID = "EXME_ProgInstitucional_ID";

	/** Set Institutional program	  */
	public void setEXME_ProgInstitucional_ID (int EXME_ProgInstitucional_ID);

	/** Get Institutional program	  */
	public int getEXME_ProgInstitucional_ID();

	public I_EXME_ProgInstitucional getEXME_ProgInstitucional() throws RuntimeException;

    /** Column name EXME_ProgPresupuestal_ID */
    public static final String COLUMNNAME_EXME_ProgPresupuestal_ID = "EXME_ProgPresupuestal_ID";

	/** Set Budget programmes.
	  * Budget programmes
	  */
	public void setEXME_ProgPresupuestal_ID (int EXME_ProgPresupuestal_ID);

	/** Get Budget programmes.
	  * Budget programmes
	  */
	public int getEXME_ProgPresupuestal_ID();

	public I_EXME_ProgPresupuestal getEXME_ProgPresupuestal() throws RuntimeException;

    /** Column name EXME_Reasignacion_ID */
    public static final String COLUMNNAME_EXME_Reasignacion_ID = "EXME_Reasignacion_ID";

	/** Set Reallocation.
	  * Reallocation
	  */
	public void setEXME_Reasignacion_ID (int EXME_Reasignacion_ID);

	/** Get Reallocation.
	  * Reallocation
	  */
	public int getEXME_Reasignacion_ID();

	public I_EXME_Reasignacion getEXME_Reasignacion() throws RuntimeException;

    /** Column name EXME_TipoGasto_ID */
    public static final String COLUMNNAME_EXME_TipoGasto_ID = "EXME_TipoGasto_ID";

	/** Set Type of expenditure.
	  * Type of expenditure
	  */
	public void setEXME_TipoGasto_ID (int EXME_TipoGasto_ID);

	/** Get Type of expenditure.
	  * Type of expenditure
	  */
	public int getEXME_TipoGasto_ID();

	public I_EXME_TipoGasto getEXME_TipoGasto() throws RuntimeException;

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

    /** Column name Finalidad */
    public static final String COLUMNNAME_Finalidad = "Finalidad";

	/** Set Finality	  */
	public void setFinalidad (String Finalidad);

	/** Get Finality	  */
	public String getFinalidad();

    /** Column name FinalidadClv */
    public static final String COLUMNNAME_FinalidadClv = "FinalidadClv";

	/** Set Key finality.
	  * Key finality
	  */
	public void setFinalidadClv (String FinalidadClv);

	/** Get Key finality.
	  * Key finality
	  */
	public String getFinalidadClv();

    /** Column name FteFinValue */
    public static final String COLUMNNAME_FteFinValue = "FteFinValue";

	/** Set Value "Source of Funding"	  */
	public void setFteFinValue (String FteFinValue);

	/** Get Value "Source of Funding"	  */
	public String getFteFinValue();

    /** Column name Funcion */
    public static final String COLUMNNAME_Funcion = "Funcion";

	/** Set Function	  */
	public void setFuncion (String Funcion);

	/** Get Function	  */
	public String getFuncion();

    /** Column name FuncionClv */
    public static final String COLUMNNAME_FuncionClv = "FuncionClv";

	/** Set Key function	  */
	public void setFuncionClv (String FuncionClv);

	/** Get Key function	  */
	public String getFuncionClv();

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

    /** Column name I_EXME_PresupuestoEgr_ID */
    public static final String COLUMNNAME_I_EXME_PresupuestoEgr_ID = "I_EXME_PresupuestoEgr_ID";

	/** Set Egress budget (Import)	  */
	public void setI_EXME_PresupuestoEgr_ID (int I_EXME_PresupuestoEgr_ID);

	/** Get Egress budget (Import)	  */
	public int getI_EXME_PresupuestoEgr_ID();

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

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

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

    /** Column name OrgValue */
    public static final String COLUMNNAME_OrgValue = "OrgValue";

	/** Set Organization Key.
	  * Key of the Organization
	  */
	public void setOrgValue (String OrgValue);

	/** Get Organization Key.
	  * Key of the Organization
	  */
	public String getOrgValue();

    /** Column name PartName */
    public static final String COLUMNNAME_PartName = "PartName";

	/** Set Name "Budget Item"	  */
	public void setPartName (String PartName);

	/** Get Name "Budget Item"	  */
	public String getPartName();

    /** Column name PartValue */
    public static final String COLUMNNAME_PartValue = "PartValue";

	/** Set Value "Budget Item"	  */
	public void setPartValue (String PartValue);

	/** Get Value "Budget Item"	  */
	public String getPartValue();

    /** Column name PresupuestoValue */
    public static final String COLUMNNAME_PresupuestoValue = "PresupuestoValue";

	/** Set Value "Egress budget".
	  * Value "Egress budget"
	  */
	public void setPresupuestoValue (String PresupuestoValue);

	/** Get Value "Egress budget".
	  * Value "Egress budget"
	  */
	public String getPresupuestoValue();

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

    /** Column name ProgInsName */
    public static final String COLUMNNAME_ProgInsName = "ProgInsName";

	/** Set Name "Institutional program"	  */
	public void setProgInsName (String ProgInsName);

	/** Get Name "Institutional program"	  */
	public String getProgInsName();

    /** Column name ProgInsValue */
    public static final String COLUMNNAME_ProgInsValue = "ProgInsValue";

	/** Set Value "Institutional program"	  */
	public void setProgInsValue (String ProgInsValue);

	/** Get Value "Institutional program"	  */
	public String getProgInsValue();

    /** Column name ProgPresName */
    public static final String COLUMNNAME_ProgPresName = "ProgPresName";

	/** Set Name "Budget programmes"	  */
	public void setProgPresName (String ProgPresName);

	/** Get Name "Budget programmes"	  */
	public String getProgPresName();

    /** Column name ProgPresValue */
    public static final String COLUMNNAME_ProgPresValue = "ProgPresValue";

	/** Set Value "Budget programmes"	  */
	public void setProgPresValue (String ProgPresValue);

	/** Get Value "Budget programmes"	  */
	public String getProgPresValue();

    /** Column name ProjectValue */
    public static final String COLUMNNAME_ProjectValue = "ProjectValue";

	/** Set Project Key.
	  * Key of the Project
	  */
	public void setProjectValue (String ProjectValue);

	/** Get Project Key.
	  * Key of the Project
	  */
	public String getProjectValue();

    /** Column name ReasignacionValue */
    public static final String COLUMNNAME_ReasignacionValue = "ReasignacionValue";

	/** Set Key Reallocation	  */
	public void setReasignacionValue (String ReasignacionValue);

	/** Get Key Reallocation	  */
	public String getReasignacionValue();

    /** Column name Revision */
    public static final String COLUMNNAME_Revision = "Revision";

	/** Set Revision	  */
	public void setRevision (BigDecimal Revision);

	/** Get Revision	  */
	public BigDecimal getRevision();

    /** Column name Subfuncion */
    public static final String COLUMNNAME_Subfuncion = "Subfuncion";

	/** Set Subfunction.
	  * Subfunction
	  */
	public void setSubfuncion (String Subfuncion);

	/** Get Subfunction.
	  * Subfunction
	  */
	public String getSubfuncion();

    /** Column name SubfuncionClv */
    public static final String COLUMNNAME_SubfuncionClv = "SubfuncionClv";

	/** Set Subfunction key.
	  * Subfunction key
	  */
	public void setSubfuncionClv (String SubfuncionClv);

	/** Get Subfunction key.
	  * Subfunction key
	  */
	public String getSubfuncionClv();

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

    /** Column name TipoGastoValue */
    public static final String COLUMNNAME_TipoGastoValue = "TipoGastoValue";

	/** Set Value "Type of expenditure"	  */
	public void setTipoGastoValue (String TipoGastoValue);

	/** Get Value "Type of expenditure"	  */
	public String getTipoGastoValue();

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

    /** Column name Version */
    public static final String COLUMNNAME_Version = "Version";

	/** Set Version.
	  * Version of the table definition
	  */
	public void setVersion (String Version);

	/** Get Version.
	  * Version of the table definition
	  */
	public String getVersion();

    /** Column name Year */
    public static final String COLUMNNAME_Year = "Year";

	/** Set Year.
	  * Calendar Year
	  */
	public void setYear (String Year);

	/** Get Year.
	  * Calendar Year
	  */
	public String getYear();
}
