/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_RfQResponseLine
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_RfQResponseLine 
{

    /** TableName=C_RfQResponseLine */
    public static final String Table_Name = "C_RfQResponseLine";

    /** AD_Table_ID=673 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name C_RfQLine_ID */
    public static final String COLUMNNAME_C_RfQLine_ID = "C_RfQLine_ID";

	/** Set RfQ Line.
	  * Request for Quotation Line
	  */
	public void setC_RfQLine_ID (int C_RfQLine_ID);

	/** Get RfQ Line.
	  * Request for Quotation Line
	  */
	public int getC_RfQLine_ID();

	public I_C_RfQLine getC_RfQLine() throws RuntimeException;

    /** Column name C_RfQResponse_ID */
    public static final String COLUMNNAME_C_RfQResponse_ID = "C_RfQResponse_ID";

	/** Set RfQ Response.
	  * Request for Quotation Response from a potential Vendor
	  */
	public void setC_RfQResponse_ID (int C_RfQResponse_ID);

	/** Get RfQ Response.
	  * Request for Quotation Response from a potential Vendor
	  */
	public int getC_RfQResponse_ID();

	public I_C_RfQResponse getC_RfQResponse() throws RuntimeException;

    /** Column name C_RfQResponseLine_ID */
    public static final String COLUMNNAME_C_RfQResponseLine_ID = "C_RfQResponseLine_ID";

	/** Set RfQ Response Line.
	  * Request for Quotation Response Line
	  */
	public void setC_RfQResponseLine_ID (int C_RfQResponseLine_ID);

	/** Get RfQ Response Line.
	  * Request for Quotation Response Line
	  */
	public int getC_RfQResponseLine_ID();

    /** Column name DateWorkComplete */
    public static final String COLUMNNAME_DateWorkComplete = "DateWorkComplete";

	/** Set Work Complete.
	  * Date when work is (planned to be) complete
	  */
	public void setDateWorkComplete (Timestamp DateWorkComplete);

	/** Get Work Complete.
	  * Date when work is (planned to be) complete
	  */
	public Timestamp getDateWorkComplete();

    /** Column name DateWorkStart */
    public static final String COLUMNNAME_DateWorkStart = "DateWorkStart";

	/** Set Work Start.
	  * Date when work is (planned to be) started
	  */
	public void setDateWorkStart (Timestamp DateWorkStart);

	/** Get Work Start.
	  * Date when work is (planned to be) started
	  */
	public Timestamp getDateWorkStart();

    /** Column name DeliveryDays */
    public static final String COLUMNNAME_DeliveryDays = "DeliveryDays";

	/** Set Delivery Days.
	  * Number of Days (planned) until Delivery
	  */
	public void setDeliveryDays (int DeliveryDays);

	/** Get Delivery Days.
	  * Number of Days (planned) until Delivery
	  */
	public int getDeliveryDays();

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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsSelectedWinner */
    public static final String COLUMNNAME_IsSelectedWinner = "IsSelectedWinner";

	/** Set Selected Winner.
	  * The resonse is the selected winner
	  */
	public void setIsSelectedWinner (boolean IsSelectedWinner);

	/** Get Selected Winner.
	  * The resonse is the selected winner
	  */
	public boolean isSelectedWinner();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService();
}
