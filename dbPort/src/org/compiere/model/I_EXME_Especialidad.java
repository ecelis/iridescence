/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Especialidad
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_EXME_Especialidad 
{

    /** TableName=EXME_Especialidad */
    public static final String Table_Name = "EXME_Especialidad";

    /** AD_Table_ID=1000015 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Color_ID */
    public static final String COLUMNNAME_AD_Color_ID = "AD_Color_ID";

	/** Set System Color.
	  * Color for backgrounds or indicators
	  */
	public void setAD_Color_ID (int AD_Color_ID);

	/** Get System Color.
	  * Color for backgrounds or indicators
	  */
	public int getAD_Color_ID();

	public I_AD_Color getAD_Color() throws RuntimeException;

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

    /** Column name IntervaloConsulta */
    public static final String COLUMNNAME_IntervaloConsulta = "IntervaloConsulta";

	/** Set Consult Interval	  */
	public void setIntervaloConsulta (BigDecimal IntervaloConsulta);

	/** Get Consult Interval	  */
	public BigDecimal getIntervaloConsulta();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsEnf */
    public static final String COLUMNNAME_IsEnf = "IsEnf";

	/** Set Is Nurse	  */
	public void setIsEnf (boolean IsEnf);

	/** Get Is Nurse	  */
	public boolean isEnf();

    /** Column name IsMed */
    public static final String COLUMNNAME_IsMed = "IsMed";

	/** Set Is Medical Specialty	  */
	public void setIsMed (boolean IsMed);

	/** Get Is Medical Specialty	  */
	public boolean isMed();

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

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

    /** Column name Ref_EXME_Especialidad_ID */
    public static final String COLUMNNAME_Ref_EXME_Especialidad_ID = "Ref_EXME_Especialidad_ID";

	/** Set Specialty Reference	  */
	public void setRef_EXME_Especialidad_ID (int Ref_EXME_Especialidad_ID);

	/** Get Specialty Reference	  */
	public int getRef_EXME_Especialidad_ID();

    /** Column name SpecType */
    public static final String COLUMNNAME_SpecType = "SpecType";

	/** Set Specialty Type.
	  * The Specialty Type
	  */
	public void setSpecType (String SpecType);

	/** Get Specialty Type.
	  * The Specialty Type
	  */
	public String getSpecType();

    /** Column name TiempoEspera */
    public static final String COLUMNNAME_TiempoEspera = "TiempoEspera";

	/** Set Waiting Time.
	  * Waiting Time (in months)
	  */
	public void setTiempoEspera (BigDecimal TiempoEspera);

	/** Get Waiting Time.
	  * Waiting Time (in months)
	  */
	public BigDecimal getTiempoEspera();

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
