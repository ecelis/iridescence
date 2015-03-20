/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Config_OV
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Config_OV 
{

    /** TableName=EXME_Config_OV */
    public static final String Table_Name = "EXME_Config_OV";

    /** AD_Table_ID=1201215 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name Assessment_ID */
    public static final String COLUMNNAME_Assessment_ID = "Assessment_ID";

	/** Set Assessment.
	  * Cuestionario para la pestaña de Valoración
	  */
	public void setAssessment_ID (int Assessment_ID);

	/** Get Assessment.
	  * Cuestionario para la pestaña de Valoración
	  */
	public int getAssessment_ID();

    /** Column name BREAST_ID */
    public static final String COLUMNNAME_BREAST_ID = "BREAST_ID";

	/** Set BREAST_ID	  */
	public void setBREAST_ID (int BREAST_ID);

	/** Get BREAST_ID	  */
	public int getBREAST_ID();

    /** Column name CARDIO_ID */
    public static final String COLUMNNAME_CARDIO_ID = "CARDIO_ID";

	/** Set CARDIO_ID	  */
	public void setCARDIO_ID (int CARDIO_ID);

	/** Get CARDIO_ID	  */
	public int getCARDIO_ID();

    /** Column name ENMT_ID */
    public static final String COLUMNNAME_ENMT_ID = "ENMT_ID";

	/** Set ENMT_ID	  */
	public void setENMT_ID (int ENMT_ID);

	/** Get ENMT_ID	  */
	public int getENMT_ID();

    /** Column name EXME_Config_OV_ID */
    public static final String COLUMNNAME_EXME_Config_OV_ID = "EXME_Config_OV_ID";

	/** Set EXME_Config_OV_ID	  */
	public void setEXME_Config_OV_ID (int EXME_Config_OV_ID);

	/** Get EXME_Config_OV_ID	  */
	public int getEXME_Config_OV_ID();

    /** Column name EXME_GrupoCuestionario_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionario_ID = "EXME_GrupoCuestionario_ID";

	/** Set Form Group.
	  * Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID);

	/** Get Form Group.
	  * Form Group
	  */
	public int getEXME_GrupoCuestionario_ID();

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

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException;

    /** Column name EYES_ID */
    public static final String COLUMNNAME_EYES_ID = "EYES_ID";

	/** Set EYES_ID	  */
	public void setEYES_ID (int EYES_ID);

	/** Get EYES_ID	  */
	public int getEYES_ID();

    /** Column name GenCharges */
    public static final String COLUMNNAME_GenCharges = "GenCharges";

	/** Set Fee Charges.
	  * Fee Charges
	  */
	public void setGenCharges (boolean GenCharges);

	/** Get Fee Charges.
	  * Fee Charges
	  */
	public boolean isGenCharges();

    /** Column name GENIT_ID */
    public static final String COLUMNNAME_GENIT_ID = "GENIT_ID";

	/** Set GENIT_ID	  */
	public void setGENIT_ID (int GENIT_ID);

	/** Get GENIT_ID	  */
	public int getGENIT_ID();

    /** Column name HEMATO_ID */
    public static final String COLUMNNAME_HEMATO_ID = "HEMATO_ID";

	/** Set HEMATO_ID	  */
	public void setHEMATO_ID (int HEMATO_ID);

	/** Get HEMATO_ID	  */
	public int getHEMATO_ID();

    /** Column name HPI_ID */
    public static final String COLUMNNAME_HPI_ID = "HPI_ID";

	/** Set HPI_ID	  */
	public void setHPI_ID (int HPI_ID);

	/** Get HPI_ID	  */
	public int getHPI_ID();

    /** Column name MFSH_ID */
    public static final String COLUMNNAME_MFSH_ID = "MFSH_ID";

	/** Set MFSH_ID	  */
	public void setMFSH_ID (int MFSH_ID);

	/** Get MFSH_ID	  */
	public int getMFSH_ID();

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

    /** Column name MULTI_ID */
    public static final String COLUMNNAME_MULTI_ID = "MULTI_ID";

	/** Set MULTI_ID	  */
	public void setMULTI_ID (int MULTI_ID);

	/** Get MULTI_ID	  */
	public int getMULTI_ID();

    /** Column name MUSCUL_ID */
    public static final String COLUMNNAME_MUSCUL_ID = "MUSCUL_ID";

	/** Set MUSCUL_ID	  */
	public void setMUSCUL_ID (int MUSCUL_ID);

	/** Get MUSCUL_ID	  */
	public int getMUSCUL_ID();

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

    /** Column name NEUROL_ID */
    public static final String COLUMNNAME_NEUROL_ID = "NEUROL_ID";

	/** Set NEUROL_ID	  */
	public void setNEUROL_ID (int NEUROL_ID);

	/** Get NEUROL_ID	  */
	public int getNEUROL_ID();

    /** Column name PSYCHI_ID */
    public static final String COLUMNNAME_PSYCHI_ID = "PSYCHI_ID";

	/** Set PSYCHI_ID	  */
	public void setPSYCHI_ID (int PSYCHI_ID);

	/** Get PSYCHI_ID	  */
	public int getPSYCHI_ID();

    /** Column name RESPIR_ID */
    public static final String COLUMNNAME_RESPIR_ID = "RESPIR_ID";

	/** Set RESPIR_ID	  */
	public void setRESPIR_ID (int RESPIR_ID);

	/** Get RESPIR_ID	  */
	public int getRESPIR_ID();

    /** Column name ROS_ID */
    public static final String COLUMNNAME_ROS_ID = "ROS_ID";

	/** Set ROS Form	  */
	public void setROS_ID (int ROS_ID);

	/** Get ROS Form	  */
	public int getROS_ID();

    /** Column name SKIN_ID */
    public static final String COLUMNNAME_SKIN_ID = "SKIN_ID";

	/** Set SKIN_ID	  */
	public void setSKIN_ID (int SKIN_ID);

	/** Get SKIN_ID	  */
	public int getSKIN_ID();
}
