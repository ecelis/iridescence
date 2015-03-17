/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProductFDB
 *  @author Generated Class 
 *  @version Release 6.0
 */
public interface I_EXME_ProductFDB 
{

    /** TableName=EXME_ProductFDB */
    public static final String Table_Name = "EXME_ProductFDB";

    /** AD_Table_ID=1201064 */
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

    /** Column name EXME_DoseForm_ID */
    public static final String COLUMNNAME_EXME_DoseForm_ID = "EXME_DoseForm_ID";

	/** Set DoseForm.
	  * DoseForm
	  */
	public void setEXME_DoseForm_ID (int EXME_DoseForm_ID);

	/** Get DoseForm.
	  * DoseForm
	  */
	public int getEXME_DoseForm_ID();

    /** Column name EXME_Labeler_ID */
    public static final String COLUMNNAME_EXME_Labeler_ID = "EXME_Labeler_ID";

	/** Set Labeler.
	  * Labeler
	  */
	public void setEXME_Labeler_ID (int EXME_Labeler_ID);

	/** Get Labeler.
	  * Labeler
	  */
	public int getEXME_Labeler_ID();

    /** Column name EXME_Productfdb_ID */
    public static final String COLUMNNAME_EXME_Productfdb_ID = "EXME_Productfdb_ID";

	/** Set EXME_Productfdb_ID	  */
	public void setEXME_Productfdb_ID (int EXME_Productfdb_ID);

	/** Get EXME_Productfdb_ID	  */
	public int getEXME_Productfdb_ID();

    /** Column name EXME_Route_ID */
    public static final String COLUMNNAME_EXME_Route_ID = "EXME_Route_ID";

	/** Set Route.
	  * Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID);

	/** Get Route.
	  * Route
	  */
	public int getEXME_Route_ID();

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

    /** Column name Strength */
    public static final String COLUMNNAME_Strength = "Strength";

	/** Set Strength.
	  * Strength
	  */
	public void setStrength (int Strength);

	/** Get Strength.
	  * Strength
	  */
	public int getStrength();

    /** Column name Strengthunits */
    public static final String COLUMNNAME_Strengthunits = "Strengthunits";

	/** Set Strengthunits.
	  * Strengthunits 
	  */
	public void setStrengthunits (int Strengthunits);

	/** Get Strengthunits.
	  * Strengthunits 
	  */
	public int getStrengthunits();
}
