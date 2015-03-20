/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_RssPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_RssPac 
{

    /** TableName=PHR_RssPac */
    public static final String Table_Name = "PHR_RssPac";

    /** AD_Table_ID=1201020 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name PHR_RssPac_ID */
    public static final String COLUMNNAME_PHR_RssPac_ID = "PHR_RssPac_ID";

	/** Set Primary Key.
	  * Primary Key
	  */
	public void setPHR_RssPac_ID (int PHR_RssPac_ID);

	/** Get Primary Key.
	  * Primary Key
	  */
	public int getPHR_RssPac_ID();

    /** Column name Rss */
    public static final String COLUMNNAME_Rss = "Rss";

	/** Set Articles Address.
	  * Articles Address
	  */
	public void setRss (String Rss);

	/** Get Articles Address.
	  * Articles Address
	  */
	public String getRss();
}
