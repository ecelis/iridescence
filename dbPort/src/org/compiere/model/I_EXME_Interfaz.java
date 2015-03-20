/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Interfaz
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Interfaz 
{

    /** TableName=EXME_Interfaz */
    public static final String Table_Name = "EXME_Interfaz";

    /** AD_Table_ID=1000185 */
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

    /** Column name AD_Process_ID */
    public static final String COLUMNNAME_AD_Process_ID = "AD_Process_ID";

	/** Set Process.
	  * Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID);

	/** Get Process.
	  * Process or Report
	  */
	public int getAD_Process_ID();

	public I_AD_Process getAD_Process() throws RuntimeException;

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

    /** Column name EXME_Interfaz_ID */
    public static final String COLUMNNAME_EXME_Interfaz_ID = "EXME_Interfaz_ID";

	/** Set Interface	  */
	public void setEXME_Interfaz_ID (int EXME_Interfaz_ID);

	/** Get Interface	  */
	public int getEXME_Interfaz_ID();

    /** Column name EXME_InterfazProcessor_ID */
    public static final String COLUMNNAME_EXME_InterfazProcessor_ID = "EXME_InterfazProcessor_ID";

	/** Set Processor Interface	  */
	public void setEXME_InterfazProcessor_ID (int EXME_InterfazProcessor_ID);

	/** Get Processor Interface	  */
	public int getEXME_InterfazProcessor_ID();

	public I_EXME_InterfazProcessor getEXME_InterfazProcessor() throws RuntimeException;

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

    /** Column name TableName */
    public static final String COLUMNNAME_TableName = "TableName";

	/** Set DB Table Name.
	  * Name of the table in the database
	  */
	public void setTableName (String TableName);

	/** Get DB Table Name.
	  * Name of the table in the database
	  */
	public String getTableName();
}
