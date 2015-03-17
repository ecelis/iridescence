/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Route
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Route 
{

    /** TableName=EXME_Route */
    public static final String Table_Name = "EXME_Route";

    /** AD_Table_ID=1201060 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name Abrev */
    public static final String COLUMNNAME_Abrev = "Abrev";

	/** Set Abbreviation.
	  * Abbreviation
	  */
	public void setAbrev (String Abrev);

	/** Get Abbreviation.
	  * Abbreviation
	  */
	public String getAbrev();

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

    /** Column name Description1 */
    public static final String COLUMNNAME_Description1 = "Description1";

	/** Set Description 1.
	  * Description 1
	  */
	public void setDescription1 (String Description1);

	/** Get Description 1.
	  * Description 1
	  */
	public String getDescription1();

    /** Column name Description2 */
    public static final String COLUMNNAME_Description2 = "Description2";

	/** Set Description 2	  */
	public void setDescription2 (String Description2);

	/** Get Description 2	  */
	public String getDescription2();

    /** Column name Description3 */
    public static final String COLUMNNAME_Description3 = "Description3";

	/** Set Description 3.
	  * Description 3
	  */
	public void setDescription3 (String Description3);

	/** Get Description 3.
	  * Description 3
	  */
	public String getDescription3();

    /** Column name Description4 */
    public static final String COLUMNNAME_Description4 = "Description4";

	/** Set Description 4.
	  * Description 4
	  */
	public void setDescription4 (String Description4);

	/** Get Description 4.
	  * Description 4
	  */
	public String getDescription4();

    /** Column name Description5 */
    public static final String COLUMNNAME_Description5 = "Description5";

	/** Set Description 5.
	  * Description 5
	  */
	public void setDescription5 (String Description5);

	/** Get Description 5.
	  * Description 5
	  */
	public String getDescription5();

    /** Column name Description6 */
    public static final String COLUMNNAME_Description6 = "Description6";

	/** Set Description 6.
	  * Description 6
	  */
	public void setDescription6 (String Description6);

	/** Get Description 6.
	  * Description 6
	  */
	public String getDescription6();

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

    /** Column name RtID */
    public static final String COLUMNNAME_RtID = "RtID";

	/** Set RtID	  */
	public void setRtID (int RtID);

	/** Get RtID	  */
	public int getRtID();
}
