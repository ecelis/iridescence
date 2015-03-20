/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EncoderOrg
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_EncoderOrg 
{

    /** TableName=EXME_EncoderOrg */
    public static final String Table_Name = "EXME_EncoderOrg";

    /** AD_Table_ID=1201293 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name EXME_ConfigEncoder_ID */
    public static final String COLUMNNAME_EXME_ConfigEncoder_ID = "EXME_ConfigEncoder_ID";

	/** Set Encoder configuration.
	  * Encoder configuration
	  */
	public void setEXME_ConfigEncoder_ID (int EXME_ConfigEncoder_ID);

	/** Get Encoder configuration.
	  * Encoder configuration
	  */
	public int getEXME_ConfigEncoder_ID();

	public I_EXME_ConfigEncoder getEXME_ConfigEncoder() throws RuntimeException;

    /** Column name EXME_EncoderOrg_ID */
    public static final String COLUMNNAME_EXME_EncoderOrg_ID = "EXME_EncoderOrg_ID";

	/** Set Encoding by organization.
	  * Encoding by organization
	  */
	public void setEXME_EncoderOrg_ID (int EXME_EncoderOrg_ID);

	/** Get Encoding by organization.
	  * Encoding by organization
	  */
	public int getEXME_EncoderOrg_ID();

    /** Column name Rel_Client_ID */
    public static final String COLUMNNAME_Rel_Client_ID = "Rel_Client_ID";

	/** Set Client.
	  * Client
	  */
	public void setRel_Client_ID (int Rel_Client_ID);

	/** Get Client.
	  * Client
	  */
	public int getRel_Client_ID();

    /** Column name Rel_Org_ID */
    public static final String COLUMNNAME_Rel_Org_ID = "Rel_Org_ID";

	/** Set Organization.
	  * Organization
	  */
	public void setRel_Org_ID (int Rel_Org_ID);

	/** Get Organization.
	  * Organization
	  */
	public int getRel_Org_ID();

    /** Column name UseTrucode */
    public static final String COLUMNNAME_UseTrucode = "UseTrucode";

	/** Set Client uses the encoding.
	  * Client uses the encoding
	  */
	public void setUseTrucode (boolean UseTrucode);

	/** Get Client uses the encoding.
	  * Client uses the encoding
	  */
	public boolean isUseTrucode();
}
