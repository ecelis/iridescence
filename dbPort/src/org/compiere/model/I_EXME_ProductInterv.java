/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProductInterv
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ProductInterv 
{

    /** TableName=EXME_ProductInterv */
    public static final String Table_Name = "EXME_ProductInterv";

    /** AD_Table_ID=1201384 */
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

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set CPT Code.
	  * Current Procedural Terminology (CPT) 
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get CPT Code.
	  * Current Procedural Terminology (CPT) 
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

    /** Column name EXME_ProductInterv_ID */
    public static final String COLUMNNAME_EXME_ProductInterv_ID = "EXME_ProductInterv_ID";

	/** Set Product - CPT	  */
	public void setEXME_ProductInterv_ID (int EXME_ProductInterv_ID);

	/** Get Product - CPT	  */
	public int getEXME_ProductInterv_ID();

    /** Column name InterValue */
    public static final String COLUMNNAME_InterValue = "InterValue";

	/** Set InterValue	  */
	public void setInterValue (String InterValue);

	/** Get InterValue	  */
	public String getInterValue();

    /** Column name Intervencion */
    public static final String COLUMNNAME_Intervencion = "Intervencion";

	/** Set CPT	  */
	public void setIntervencion (String Intervencion);

	/** Get CPT	  */
	public String getIntervencion();

    /** Column name ProductClass */
    public static final String COLUMNNAME_ProductClass = "ProductClass";

	/** Set Product Class	  */
	public void setProductClass (String ProductClass);

	/** Get Product Class	  */
	public String getProductClass();

    /** Column name ProductName */
    public static final String COLUMNNAME_ProductName = "ProductName";

	/** Set Product Name.
	  * Name of the Product
	  */
	public void setProductName (String ProductName);

	/** Get Product Name.
	  * Name of the Product
	  */
	public String getProductName();

    /** Column name ProductValue */
    public static final String COLUMNNAME_ProductValue = "ProductValue";

	/** Set Product Key.
	  * Key of the Product
	  */
	public void setProductValue (String ProductValue);

	/** Get Product Key.
	  * Key of the Product
	  */
	public String getProductValue();
}
