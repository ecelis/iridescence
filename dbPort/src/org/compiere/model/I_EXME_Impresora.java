/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Impresora
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Impresora 
{

    /** TableName=EXME_Impresora */
    public static final String Table_Name = "EXME_Impresora";

    /** AD_Table_ID=1200040 */
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

    /** Column name BraceletType */
    public static final String COLUMNNAME_BraceletType = "BraceletType";

	/** Set Bracelet Type.
	  * Bracelet Type
	  */
	public void setBraceletType (String BraceletType);

	/** Get Bracelet Type.
	  * Bracelet Type
	  */
	public String getBraceletType();

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

    /** Column name EXME_Impresora_ID */
    public static final String COLUMNNAME_EXME_Impresora_ID = "EXME_Impresora_ID";

	/** Set printer.
	  * printer
	  */
	public void setEXME_Impresora_ID (int EXME_Impresora_ID);

	/** Get printer.
	  * printer
	  */
	public int getEXME_Impresora_ID();

    /** Column name IP */
    public static final String COLUMNNAME_IP = "IP";

	/** Set IP Address	  */
	public void setIP (String IP);

	/** Get IP Address	  */
	public String getIP();

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

    /** Column name PrinterModel */
    public static final String COLUMNNAME_PrinterModel = "PrinterModel";

	/** Set Printer Model.
	  * Printer Model
	  */
	public void setPrinterModel (String PrinterModel);

	/** Get Printer Model.
	  * Printer Model
	  */
	public String getPrinterModel();

    /** Column name PrinterType */
    public static final String COLUMNNAME_PrinterType = "PrinterType";

	/** Set Printer Type.
	  * Printer Type
	  */
	public void setPrinterType (String PrinterType);

	/** Get Printer Type.
	  * Printer Type
	  */
	public String getPrinterType();

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
