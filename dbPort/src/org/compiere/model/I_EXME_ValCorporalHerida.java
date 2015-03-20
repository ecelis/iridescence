/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ValCorporalHerida
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ValCorporalHerida 
{

    /** TableName=EXME_ValCorporalHerida */
    public static final String Table_Name = "EXME_ValCorporalHerida";

    /** AD_Table_ID=1200518 */
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

    /** Column name EXME_HeridaQCA_ID */
    public static final String COLUMNNAME_EXME_HeridaQCA_ID = "EXME_HeridaQCA_ID";

	/** Set Surgical wound	  */
	public void setEXME_HeridaQCA_ID (int EXME_HeridaQCA_ID);

	/** Get Surgical wound	  */
	public int getEXME_HeridaQCA_ID();

	public I_EXME_HeridaQCA getEXME_HeridaQCA() throws RuntimeException;

    /** Column name EXME_ParteCorporal_ID */
    public static final String COLUMNNAME_EXME_ParteCorporal_ID = "EXME_ParteCorporal_ID";

	/** Set Body part	  */
	public void setEXME_ParteCorporal_ID (int EXME_ParteCorporal_ID);

	/** Get Body part	  */
	public int getEXME_ParteCorporal_ID();

	public I_EXME_ParteCorporal getEXME_ParteCorporal() throws RuntimeException;

    /** Column name EXME_ValCorporalHerida_ID */
    public static final String COLUMNNAME_EXME_ValCorporalHerida_ID = "EXME_ValCorporalHerida_ID";

	/** Set Injury Body Valuation	  */
	public void setEXME_ValCorporalHerida_ID (int EXME_ValCorporalHerida_ID);

	/** Get Injury Body Valuation	  */
	public int getEXME_ValCorporalHerida_ID();

    /** Column name EXME_ValCorporal_ID */
    public static final String COLUMNNAME_EXME_ValCorporal_ID = "EXME_ValCorporal_ID";

	/** Set Body Valuation	  */
	public void setEXME_ValCorporal_ID (int EXME_ValCorporal_ID);

	/** Get Body Valuation	  */
	public int getEXME_ValCorporal_ID();

	public I_EXME_ValCorporal getEXME_ValCorporal() throws RuntimeException;

    /** Column name Herida */
    public static final String COLUMNNAME_Herida = "Herida";

	/** Set Wound	  */
	public void setHerida (String Herida);

	/** Get Wound	  */
	public String getHerida();
}
