/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EsqInsulinaLine
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_EsqInsulinaLine 
{

    /** TableName=EXME_EsqInsulinaLine */
    public static final String Table_Name = "EXME_EsqInsulinaLine";

    /** AD_Table_ID=1201319 */
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

    /** Column name EXME_EsqInsulinaLine_ID */
    public static final String COLUMNNAME_EXME_EsqInsulinaLine_ID = "EXME_EsqInsulinaLine_ID";

	/** Set Sliding Scale detail	  */
	public void setEXME_EsqInsulinaLine_ID (int EXME_EsqInsulinaLine_ID);

	/** Get Sliding Scale detail	  */
	public int getEXME_EsqInsulinaLine_ID();

    /** Column name EXME_EsqInsulina_ID */
    public static final String COLUMNNAME_EXME_EsqInsulina_ID = "EXME_EsqInsulina_ID";

	/** Set Insulin Scheme	  */
	public void setEXME_EsqInsulina_ID (int EXME_EsqInsulina_ID);

	/** Get Insulin Scheme	  */
	public int getEXME_EsqInsulina_ID();

	public I_EXME_EsqInsulina getEXME_EsqInsulina() throws RuntimeException;

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException;

    /** Column name Instructions */
    public static final String COLUMNNAME_Instructions = "Instructions";

	/** Set Instructions	  */
	public void setInstructions (String Instructions);

	/** Get Instructions	  */
	public String getInstructions();

    /** Column name Lim_Inferior */
    public static final String COLUMNNAME_Lim_Inferior = "Lim_Inferior";

	/** Set Minimum Level	  */
	public void setLim_Inferior (int Lim_Inferior);

	/** Get Minimum Level	  */
	public int getLim_Inferior();

    /** Column name Lim_Superior */
    public static final String COLUMNNAME_Lim_Superior = "Lim_Superior";

	/** Set Maximum Level	  */
	public void setLim_Superior (int Lim_Superior);

	/** Get Maximum Level	  */
	public int getLim_Superior();

    /** Column name Unidad */
    public static final String COLUMNNAME_Unidad = "Unidad";

	/** Set Unity	  */
	public void setUnidad (int Unidad);

	/** Get Unity	  */
	public int getUnidad();
}
