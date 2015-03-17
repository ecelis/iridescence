/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Impresora
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Impresora extends PO implements I_EXME_Impresora, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Impresora (Properties ctx, int EXME_Impresora_ID, String trxName)
    {
      super (ctx, EXME_Impresora_ID, trxName);
      /** if (EXME_Impresora_ID == 0)
        {
			setEXME_Impresora_ID (0);
			setIP (null);
// 0.0.0.0
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Impresora (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_EXME_Impresora[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** BraceletType AD_Reference_ID=1200635 */
	public static final int BRACELETTYPE_AD_Reference_ID=1200635;
	/** Adult = A */
	public static final String BRACELETTYPE_Adult = "A";
	/** Pediatric = P */
	public static final String BRACELETTYPE_Pediatric = "P";
	/** Infant = I */
	public static final String BRACELETTYPE_Infant = "I";
	/** Set Bracelet Type.
		@param BraceletType 
		Bracelet Type
	  */
	public void setBraceletType (String BraceletType)
	{

		if (BraceletType == null || BraceletType.equals("A") || BraceletType.equals("P") || BraceletType.equals("I")); else throw new IllegalArgumentException ("BraceletType Invalid value - " + BraceletType + " - Reference_ID=1200635 - A - P - I");		set_Value (COLUMNNAME_BraceletType, BraceletType);
	}

	/** Get Bracelet Type.
		@return Bracelet Type
	  */
	public String getBraceletType () 
	{
		return (String)get_Value(COLUMNNAME_BraceletType);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set printer.
		@param EXME_Impresora_ID 
		printer
	  */
	public void setEXME_Impresora_ID (int EXME_Impresora_ID)
	{
		if (EXME_Impresora_ID < 1)
			 throw new IllegalArgumentException ("EXME_Impresora_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Impresora_ID, Integer.valueOf(EXME_Impresora_ID));
	}

	/** Get printer.
		@return printer
	  */
	public int getEXME_Impresora_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Impresora_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IP Address.
		@param IP IP Address	  */
	public void setIP (String IP)
	{
		if (IP == null)
			throw new IllegalArgumentException ("IP is mandatory.");
		set_Value (COLUMNNAME_IP, IP);
	}

	/** Get IP Address.
		@return IP Address	  */
	public String getIP () 
	{
		return (String)get_Value(COLUMNNAME_IP);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** PrinterModel AD_Reference_ID=1200634 */
	public static final int PRINTERMODEL_AD_Reference_ID=1200634;
	/** HC100 = HC */
	public static final String PRINTERMODEL_HC100 = "HC";
	/** ZM400 = ZM */
	public static final String PRINTERMODEL_ZM400 = "ZM";
	/** Set Printer Model.
		@param PrinterModel 
		Printer Model
	  */
	public void setPrinterModel (String PrinterModel)
	{

		if (PrinterModel == null || PrinterModel.equals("HC") || PrinterModel.equals("ZM")); else throw new IllegalArgumentException ("PrinterModel Invalid value - " + PrinterModel + " - Reference_ID=1200634 - HC - ZM");		set_Value (COLUMNNAME_PrinterModel, PrinterModel);
	}

	/** Get Printer Model.
		@return Printer Model
	  */
	public String getPrinterModel () 
	{
		return (String)get_Value(COLUMNNAME_PrinterModel);
	}

	/** PrinterType AD_Reference_ID=1200636 */
	public static final int PRINTERTYPE_AD_Reference_ID=1200636;
	/** Normal = NO */
	public static final String PRINTERTYPE_Normal = "NO";
	/** Tickets = TI */
	public static final String PRINTERTYPE_Tickets = "TI";
	/** BarCodes = BC */
	public static final String PRINTERTYPE_BarCodes = "BC";
	/** Bracelet = BR */
	public static final String PRINTERTYPE_Bracelet = "BR";
	/** ElectronicInvoice = EI */
	public static final String PRINTERTYPE_ElectronicInvoice = "EI";
	/** Set Printer Type.
		@param PrinterType 
		Printer Type
	  */
	public void setPrinterType (String PrinterType)
	{

		if (PrinterType == null || PrinterType.equals("NO") || PrinterType.equals("TI") || PrinterType.equals("BC") || PrinterType.equals("BR") || PrinterType.equals("EI")); else throw new IllegalArgumentException ("PrinterType Invalid value - " + PrinterType + " - Reference_ID=1200636 - NO - TI - BC - BR - EI");		set_Value (COLUMNNAME_PrinterType, PrinterType);
	}

	/** Get Printer Type.
		@return Printer Type
	  */
	public String getPrinterType () 
	{
		return (String)get_Value(COLUMNNAME_PrinterType);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}