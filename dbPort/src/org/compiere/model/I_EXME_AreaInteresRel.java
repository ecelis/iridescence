/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AreaInteresRel
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_AreaInteresRel 
{

    /** TableName=EXME_AreaInteresRel */
    public static final String Table_Name = "EXME_AreaInteresRel";

    /** AD_Table_ID=1200328 */
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

    /** Column name EXME_AreaInteres_ID */
    public static final String COLUMNNAME_EXME_AreaInteres_ID = "EXME_AreaInteres_ID";

	/** Set Area of interest	  */
	public void setEXME_AreaInteres_ID (int EXME_AreaInteres_ID);

	/** Get Area of interest	  */
	public int getEXME_AreaInteres_ID();

	public I_EXME_AreaInteres getEXME_AreaInteres() throws RuntimeException;

    /** Column name EXME_AreaInteresRel_ID */
    public static final String COLUMNNAME_EXME_AreaInteresRel_ID = "EXME_AreaInteresRel_ID";

	/** Set Interest Area Relation	  */
	public void setEXME_AreaInteresRel_ID (int EXME_AreaInteresRel_ID);

	/** Get Interest Area Relation	  */
	public int getEXME_AreaInteresRel_ID();

    /** Column name EXME_ProgramaInv_ID */
    public static final String COLUMNNAME_EXME_ProgramaInv_ID = "EXME_ProgramaInv_ID";

	/** Set Research Program	  */
	public void setEXME_ProgramaInv_ID (int EXME_ProgramaInv_ID);

	/** Get Research Program	  */
	public int getEXME_ProgramaInv_ID();

	public I_EXME_ProgramaInv getEXME_ProgramaInv() throws RuntimeException;
}
