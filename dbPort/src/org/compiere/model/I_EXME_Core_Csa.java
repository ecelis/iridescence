/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Core_Csa
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Core_Csa 
{

    /** TableName=EXME_Core_Csa */
    public static final String Table_Name = "EXME_Core_Csa";

    /** AD_Table_ID=1201161 */
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

    /** Column name CSA_Code */
    public static final String COLUMNNAME_CSA_Code = "CSA_Code";

	/** Set CSA Code	  */
	public void setCSA_Code (String CSA_Code);

	/** Get CSA Code	  */
	public String getCSA_Code();

    /** Column name EXME_CORE_CSA_ID */
    public static final String COLUMNNAME_EXME_CORE_CSA_ID = "EXME_CORE_CSA_ID";

	/** Set EXME_CORE_CSA_ID	  */
	public void setEXME_CORE_CSA_ID (int EXME_CORE_CSA_ID);

	/** Get EXME_CORE_CSA_ID	  */
	public int getEXME_CORE_CSA_ID();

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException;

    /** Column name GenProduct_ID */
    public static final String COLUMNNAME_GenProduct_ID = "GenProduct_ID";

	/** Set Generic Product ID	  */
	public void setGenProduct_ID (int GenProduct_ID);

	/** Get Generic Product ID	  */
	public int getGenProduct_ID();
}
