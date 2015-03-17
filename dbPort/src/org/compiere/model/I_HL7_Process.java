/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_Process
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_Process 
{

    /** TableName=HL7_Process */
    public static final String Table_Name = "HL7_Process";

    /** AD_Table_ID=1200600 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Menu_ID */
    public static final String COLUMNNAME_AD_Menu_ID = "AD_Menu_ID";

	/** Set Menu.
	  * Identifies a Menu
	  */
	public void setAD_Menu_ID (int AD_Menu_ID);

	/** Get Menu.
	  * Identifies a Menu
	  */
	public int getAD_Menu_ID();

	public I_AD_Menu getAD_Menu() throws RuntimeException;

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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name HL7_Process_ID */
    public static final String COLUMNNAME_HL7_Process_ID = "HL7_Process_ID";

	/** Set HL7 Process.
	  * Process that generate HL7 Messages
	  */
	public void setHL7_Process_ID (int HL7_Process_ID);

	/** Get HL7 Process.
	  * Process that generate HL7 Messages
	  */
	public int getHL7_Process_ID();

    /** Column name Ruta */
    public static final String COLUMNNAME_Ruta = "Ruta";

	/** Set Route.
	  * Route of screen or process
	  */
	public void setRuta (String Ruta);

	/** Get Route.
	  * Route of screen or process
	  */
	public String getRuta();
}
