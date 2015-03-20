/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Intervencion
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Intervencion 
{

    /** TableName=EXME_Intervencion */
    public static final String Table_Name = "EXME_Intervencion";

    /** AD_Table_ID=1000026 */
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

    /** Column name CodGrd */
    public static final String COLUMNNAME_CodGrd = "CodGrd";

	/** Set Code GRD.
	  * Code GRD
	  */
	public void setCodGrd (String CodGrd);

	/** Get Code GRD.
	  * Code GRD
	  */
	public String getCodGrd();

    /** Column name CodInegi */
    public static final String COLUMNNAME_CodInegi = "CodInegi";

	/** Set Code INEGI.
	  * Code INEGI
	  */
	public void setCodInegi (String CodInegi);

	/** Get Code INEGI.
	  * Code INEGI
	  */
	public String getCodInegi();

    /** Column name CodOms */
    public static final String COLUMNNAME_CodOms = "CodOms";

	/** Set World Organization Health Code.
	  * World Organization Health Code
	  */
	public void setCodOms (String CodOms);

	/** Get World Organization Health Code.
	  * World Organization Health Code
	  */
	public String getCodOms();

    /** Column name CodSnomed */
    public static final String COLUMNNAME_CodSnomed = "CodSnomed";

	/** Set Snomed Code.
	  * Snomed Code
	  */
	public void setCodSnomed (String CodSnomed);

	/** Get Snomed Code.
	  * Snomed Code
	  */
	public String getCodSnomed();

    /** Column name CostoLimpStd */
    public static final String COLUMNNAME_CostoLimpStd = "CostoLimpStd";

	/** Set Cleaning.
	  * Cleaning Standard Cost
	  */
	public void setCostoLimpStd (BigDecimal CostoLimpStd);

	/** Get Cleaning.
	  * Cleaning Standard Cost
	  */
	public BigDecimal getCostoLimpStd();

    /** Column name CostoPrepStd */
    public static final String COLUMNNAME_CostoPrepStd = "CostoPrepStd";

	/** Set Preparation.
	  * Preparation Standard Cost
	  */
	public void setCostoPrepStd (BigDecimal CostoPrepStd);

	/** Get Preparation.
	  * Preparation Standard Cost
	  */
	public BigDecimal getCostoPrepStd();

    /** Column name CostoUsoStd */
    public static final String COLUMNNAME_CostoUsoStd = "CostoUsoStd";

	/** Set Use.
	  * Use Standard Cost
	  */
	public void setCostoUsoStd (BigDecimal CostoUsoStd);

	/** Get Use.
	  * Use Standard Cost
	  */
	public BigDecimal getCostoUsoStd();

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

    /** Column name EXME_IntervencionHdr_ID */
    public static final String COLUMNNAME_EXME_IntervencionHdr_ID = "EXME_IntervencionHdr_ID";

	/** Set ICD 9 Vol III	  */
	public void setEXME_IntervencionHdr_ID (int EXME_IntervencionHdr_ID);

	/** Get ICD 9 Vol III	  */
	public int getEXME_IntervencionHdr_ID();

	public I_EXME_IntervencionHdr getEXME_IntervencionHdr() throws RuntimeException;

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set CPT Code.
	  * Current Procedural Terminology (CPT) 
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get CPT Code.
	  * Current Procedural Terminology (CPT) 
	  */
	public int getEXME_Intervencion_ID();

    /** Column name EXME_RevenueCode_ID */
    public static final String COLUMNNAME_EXME_RevenueCode_ID = "EXME_RevenueCode_ID";

	/** Set Revenue Code	  */
	public void setEXME_RevenueCode_ID (int EXME_RevenueCode_ID);

	/** Get Revenue Code	  */
	public int getEXME_RevenueCode_ID();

	public I_EXME_RevenueCode getEXME_RevenueCode() throws RuntimeException;

    /** Column name HCPCS_Level */
    public static final String COLUMNNAME_HCPCS_Level = "HCPCS_Level";

	/** Set HCPCS Level.
	  * HCPCS Level
	  */
	public void setHCPCS_Level (String HCPCS_Level);

	/** Get HCPCS Level.
	  * HCPCS Level
	  */
	public String getHCPCS_Level();

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

    /** Column name ProcedureGroup */
    public static final String COLUMNNAME_ProcedureGroup = "ProcedureGroup";

	/** Set Procedure Group	  */
	public void setProcedureGroup (String ProcedureGroup);

	/** Get Procedure Group	  */
	public String getProcedureGroup();

    /** Column name TiempoLimpStd */
    public static final String COLUMNNAME_TiempoLimpStd = "TiempoLimpStd";

	/** Set Cleaning.
	  * cleaning standard time
	  */
	public void setTiempoLimpStd (BigDecimal TiempoLimpStd);

	/** Get Cleaning.
	  * cleaning standard time
	  */
	public BigDecimal getTiempoLimpStd();

    /** Column name TiempoPrepStd */
    public static final String COLUMNNAME_TiempoPrepStd = "TiempoPrepStd";

	/** Set Preparation.
	  * preparation standard time
	  */
	public void setTiempoPrepStd (BigDecimal TiempoPrepStd);

	/** Get Preparation.
	  * preparation standard time
	  */
	public BigDecimal getTiempoPrepStd();

    /** Column name TiempoUsoStd */
    public static final String COLUMNNAME_TiempoUsoStd = "TiempoUsoStd";

	/** Set Use.
	  * use standard time
	  */
	public void setTiempoUsoStd (BigDecimal TiempoUsoStd);

	/** Get Use.
	  * use standard time
	  */
	public BigDecimal getTiempoUsoStd();

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
