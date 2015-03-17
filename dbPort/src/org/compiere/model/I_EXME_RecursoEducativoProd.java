/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RecursoEducativoProd
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RecursoEducativoProd 
{

    /** TableName=EXME_RecursoEducativoProd */
    public static final String Table_Name = "EXME_RecursoEducativoProd";

    /** AD_Table_ID=1201029 */
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

    /** Column name EXME_RecursoEducativo_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativo_ID = "EXME_RecursoEducativo_ID";

	/** Set Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID);

	/** Get Education Resource	  */
	public int getEXME_RecursoEducativo_ID();

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException;

    /** Column name EXME_RecursoEducativoProd_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativoProd_ID = "EXME_RecursoEducativoProd_ID";

	/** Set Medication Education Resource	  */
	public void setEXME_RecursoEducativoProd_ID (int EXME_RecursoEducativoProd_ID);

	/** Get Medication Education Resource	  */
	public int getEXME_RecursoEducativoProd_ID();

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
