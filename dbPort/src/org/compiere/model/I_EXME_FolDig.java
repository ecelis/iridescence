/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FolDig
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_FolDig 
{

    /** TableName=EXME_FolDig */
    public static final String Table_Name = "EXME_FolDig";

    /** AD_Table_ID=1201359 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AssignClient_ID */
    public static final String COLUMNNAME_AssignClient_ID = "AssignClient_ID";

	/** Set Assign Client_ID.
	  * AssignClientID
	  */
	public void setAssignClient_ID (int AssignClient_ID);

	/** Get Assign Client_ID.
	  * AssignClientID
	  */
	public int getAssignClient_ID();

    /** Column name Assigned */
    public static final String COLUMNNAME_Assigned = "Assigned";

	/** Set Assigned	  */
	public void setAssigned (int Assigned);

	/** Get Assigned	  */
	public int getAssigned();

    /** Column name Available */
    public static final String COLUMNNAME_Available = "Available";

	/** Set Available.
	  * Available
	  */
	public void setAvailable (int Available);

	/** Get Available.
	  * Available
	  */
	public int getAvailable();

    /** Column name DateAssign */
    public static final String COLUMNNAME_DateAssign = "DateAssign";

	/** Set Date Assign.
	  * DateAssign
	  */
	public void setDateAssign (Timestamp DateAssign);

	/** Get Date Assign.
	  * DateAssign
	  */
	public Timestamp getDateAssign();

    /** Column name DateSale */
    public static final String COLUMNNAME_DateSale = "DateSale";

	/** Set Date Sale.
	  * Date Sale
	  */
	public void setDateSale (Timestamp DateSale);

	/** Get Date Sale.
	  * Date Sale
	  */
	public Timestamp getDateSale();

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

    /** Column name EXME_FolDig_ID */
    public static final String COLUMNNAME_EXME_FolDig_ID = "EXME_FolDig_ID";

	/** Set Folios Assigned ID.
	  * Folios Assigned ID
	  */
	public void setEXME_FolDig_ID (int EXME_FolDig_ID);

	/** Get Folios Assigned ID.
	  * Folios Assigned ID
	  */
	public int getEXME_FolDig_ID();

    /** Column name isConsumo */
    public static final String COLUMNNAME_isConsumo = "isConsumo";

	/** Set Consumption	  */
	public void setisConsumo (boolean isConsumo);

	/** Get Consumption	  */
	public boolean isConsumo();

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
