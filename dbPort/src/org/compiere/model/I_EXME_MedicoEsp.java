/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MedicoEsp
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_MedicoEsp 
{

    /** TableName=EXME_MedicoEsp */
    public static final String Table_Name = "EXME_MedicoEsp";

    /** AD_Table_ID=1000023 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name EXME_MedicoEsp_ID */
    public static final String COLUMNNAME_EXME_MedicoEsp_ID = "EXME_MedicoEsp_ID";

	/** Set Specialty Doctor.
	  * Specialty Doctor
	  */
	public void setEXME_MedicoEsp_ID (int EXME_MedicoEsp_ID);

	/** Get Specialty Doctor.
	  * Specialty Doctor
	  */
	public int getEXME_MedicoEsp_ID();

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

    /** Column name EXME_Medico_Sust_ID */
    public static final String COLUMNNAME_EXME_Medico_Sust_ID = "EXME_Medico_Sust_ID";

	/** Set Substitute Doctor.
	  * Substitute doctor
	  */
	public void setEXME_Medico_Sust_ID (int EXME_Medico_Sust_ID);

	/** Get Substitute Doctor.
	  * Substitute doctor
	  */
	public int getEXME_Medico_Sust_ID();

    /** Column name HaceGuardia */
    public static final String COLUMNNAME_HaceGuardia = "HaceGuardia";

	/** Set Guards	  */
	public void setHaceGuardia (boolean HaceGuardia);

	/** Get Guards	  */
	public boolean isHaceGuardia();

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
}
