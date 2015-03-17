/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CateterEnf
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CateterEnf 
{

    /** TableName=EXME_CateterEnf */
    public static final String Table_Name = "EXME_CateterEnf";

    /** AD_Table_ID=1200502 */
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

    /** Column name AD_User_Egr */
    public static final String COLUMNNAME_AD_User_Egr = "AD_User_Egr";

	/** Set User who removes	  */
	public void setAD_User_Egr (int AD_User_Egr);

	/** Get User who removes	  */
	public int getAD_User_Egr();

    /** Column name AD_User_Ing */
    public static final String COLUMNNAME_AD_User_Ing = "AD_User_Ing";

	/** Set User who installs	  */
	public void setAD_User_Ing (int AD_User_Ing);

	/** Get User who installs	  */
	public int getAD_User_Ing();

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

    /** Column name EXME_CateterEnf_ID */
    public static final String COLUMNNAME_EXME_CateterEnf_ID = "EXME_CateterEnf_ID";

	/** Set Nursing's Catheter	  */
	public void setEXME_CateterEnf_ID (int EXME_CateterEnf_ID);

	/** Get Nursing's Catheter	  */
	public int getEXME_CateterEnf_ID();

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

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_DiarioEnf_ID */
    public static final String COLUMNNAME_EXME_DiarioEnf_ID = "EXME_DiarioEnf_ID";

	/** Set Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID);

	/** Get Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID();

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException;

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

    /** Column name EXME_Enfermeria_Egr */
    public static final String COLUMNNAME_EXME_Enfermeria_Egr = "EXME_Enfermeria_Egr";

	/** Set Nursing Discharge	  */
	public void setEXME_Enfermeria_Egr (int EXME_Enfermeria_Egr);

	/** Get Nursing Discharge	  */
	public int getEXME_Enfermeria_Egr();

    /** Column name EXME_Enfermeria_Ing */
    public static final String COLUMNNAME_EXME_Enfermeria_Ing = "EXME_Enfermeria_Ing";

	/** Set Nursing Admission	  */
	public void setEXME_Enfermeria_Ing (int EXME_Enfermeria_Ing);

	/** Get Nursing Admission	  */
	public int getEXME_Enfermeria_Ing();

    /** Column name EXME_ParteCorporal_ID */
    public static final String COLUMNNAME_EXME_ParteCorporal_ID = "EXME_ParteCorporal_ID";

	/** Set Body part	  */
	public void setEXME_ParteCorporal_ID (int EXME_ParteCorporal_ID);

	/** Get Body part	  */
	public int getEXME_ParteCorporal_ID();

	public I_EXME_ParteCorporal getEXME_ParteCorporal() throws RuntimeException;

    /** Column name FechaEgreso */
    public static final String COLUMNNAME_FechaEgreso = "FechaEgreso";

	/** Set Discharge Date	  */
	public void setFechaEgreso (Timestamp FechaEgreso);

	/** Get Discharge Date	  */
	public Timestamp getFechaEgreso();

    /** Column name FechaIngreso */
    public static final String COLUMNNAME_FechaIngreso = "FechaIngreso";

	/** Set Admission Date.
	  * Admission Date
	  */
	public void setFechaIngreso (Timestamp FechaIngreso);

	/** Get Admission Date.
	  * Admission Date
	  */
	public Timestamp getFechaIngreso();

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
}
