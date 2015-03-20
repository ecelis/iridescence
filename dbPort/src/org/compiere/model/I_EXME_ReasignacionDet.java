/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ReasignacionDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ReasignacionDet 
{

    /** TableName=EXME_ReasignacionDet */
    public static final String Table_Name = "EXME_ReasignacionDet";

    /** AD_Table_ID=1201365 */
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

	public I_EXME_ClasFuncional_Fun getEXME_ClasFuncional_Fun() throws RuntimeException;

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

	public I_EXME_ClasFuncional_Sfu getEXME_ClasFuncional_Sfu() throws RuntimeException;

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

    /** Column name EXME_ReasignacionDet_ID */
    public static final String COLUMNNAME_EXME_ReasignacionDet_ID = "EXME_ReasignacionDet_ID";

	/** Set Reallocation	  */
	public void setEXME_ReasignacionDet_ID (int EXME_ReasignacionDet_ID);

	/** Get Reallocation	  */
	public int getEXME_ReasignacionDet_ID();

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

    /** Column name Tipo */
    public static final String COLUMNNAME_Tipo = "Tipo";

	/** Set Type	  */
	public void setTipo (String Tipo);

	/** Get Type	  */
	public String getTipo();

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
