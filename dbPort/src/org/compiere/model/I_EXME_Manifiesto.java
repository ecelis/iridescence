/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Manifiesto
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Manifiesto 
{

    /** TableName=EXME_Manifiesto */
    public static final String Table_Name = "EXME_Manifiesto";

    /** AD_Table_ID=1200826 */
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

    /** Column name EXME_Manifiesto_ID */
    public static final String COLUMNNAME_EXME_Manifiesto_ID = "EXME_Manifiesto_ID";

	/** Set Manifest.
	  * It contains the vouchers issued by the garbage collector
	  */
	public void setEXME_Manifiesto_ID (int EXME_Manifiesto_ID);

	/** Get Manifest.
	  * It contains the vouchers issued by the garbage collector
	  */
	public int getEXME_Manifiesto_ID();

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

    /** Column name NoManifiesto */
    public static final String COLUMNNAME_NoManifiesto = "NoManifiesto";

	/** Set Manifesto Number.
	  * Specifies the folio number of the voucher issued by the garbage collector on the part of the service provider
	  */
	public void setNoManifiesto (String NoManifiesto);

	/** Get Manifesto Number.
	  * Specifies the folio number of the voucher issued by the garbage collector on the part of the service provider
	  */
	public String getNoManifiesto();
}
