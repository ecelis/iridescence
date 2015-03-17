/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MotivoEgreso
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MotivoEgreso 
{

    /** TableName=EXME_MotivoEgreso */
    public static final String Table_Name = "EXME_MotivoEgreso";

    /** AD_Table_ID=1200004 */
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

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_MotivoEgreso_ID */
    public static final String COLUMNNAME_EXME_MotivoEgreso_ID = "EXME_MotivoEgreso_ID";

	/** Set Discharge Reason	  */
	public void setEXME_MotivoEgreso_ID (int EXME_MotivoEgreso_ID);

	/** Get Discharge Reason	  */
	public int getEXME_MotivoEgreso_ID();

    /** Column name IsDead */
    public static final String COLUMNNAME_IsDead = "IsDead";

	/** Set It is a diagnosis of death?.
	  * It is a diagnosis of death?
	  */
	public void setIsDead (boolean IsDead);

	/** Get It is a diagnosis of death?.
	  * It is a diagnosis of death?
	  */
	public boolean isDead();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
