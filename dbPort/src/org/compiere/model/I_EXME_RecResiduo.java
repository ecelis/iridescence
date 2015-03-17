/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RecResiduo
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RecResiduo 
{

    /** TableName=EXME_RecResiduo */
    public static final String Table_Name = "EXME_RecResiduo";

    /** AD_Table_ID=1200824 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name EXME_RecResiduo_ID */
    public static final String COLUMNNAME_EXME_RecResiduo_ID = "EXME_RecResiduo_ID";

	/** Set Waste Collection.
	  * Lets keep track of collections that are made on such collection is made
	  */
	public void setEXME_RecResiduo_ID (int EXME_RecResiduo_ID);

	/** Get Waste Collection.
	  * Lets keep track of collections that are made on such collection is made
	  */
	public int getEXME_RecResiduo_ID();

    /** Column name FechaRec */
    public static final String COLUMNNAME_FechaRec = "FechaRec";

	/** Set Reception Date.
	  * Contains the date on which it carries out waste collection
	  */
	public void setFechaRec (Timestamp FechaRec);

	/** Get Reception Date.
	  * Contains the date on which it carries out waste collection
	  */
	public Timestamp getFechaRec();
}
