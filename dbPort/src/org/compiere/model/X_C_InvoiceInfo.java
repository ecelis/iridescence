/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for C_InvoiceInfo
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_InvoiceInfo extends PO implements I_C_InvoiceInfo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_InvoiceInfo (Properties ctx, int C_InvoiceInfo_ID, String trxName)
    {
      super (ctx, C_InvoiceInfo_ID, trxName);
      /** if (C_InvoiceInfo_ID == 0)
        {
			setC_InvoiceInfo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_InvoiceInfo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_InvoiceInfo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Invoice getC_Invoice() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Invoice.Table_Name);
        I_C_Invoice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Invoice)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Invoice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Additional information bill.
		@param C_InvoiceInfo_ID Additional information bill	  */
	public void setC_InvoiceInfo_ID (int C_InvoiceInfo_ID)
	{
		if (C_InvoiceInfo_ID < 1)
			 throw new IllegalArgumentException ("C_InvoiceInfo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_InvoiceInfo_ID, Integer.valueOf(C_InvoiceInfo_ID));
	}

	/** Get Additional information bill.
		@return Additional information bill	  */
	public int getC_InvoiceInfo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceInfo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Error Msg.
		@param ErrorMsg Error Msg	  */
	public void setErrorMsg (String ErrorMsg)
	{
		set_Value (COLUMNNAME_ErrorMsg, ErrorMsg);
	}

	/** Get Error Msg.
		@return Error Msg	  */
	public String getErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_ErrorMsg);
	}

	/** Set Ref. sales receipt .
		@param Ref_Invoice_Sales_ID Ref. sales receipt 	  */
	public void setRef_Invoice_Sales_ID (int Ref_Invoice_Sales_ID)
	{
		if (Ref_Invoice_Sales_ID < 1) 
			set_Value (COLUMNNAME_Ref_Invoice_Sales_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_Invoice_Sales_ID, Integer.valueOf(Ref_Invoice_Sales_ID));
	}

	/** Get Ref. sales receipt .
		@return Ref. sales receipt 	  */
	public int getRef_Invoice_Sales_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Invoice_Sales_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Universally Unique Identifier.
		@param UUID Universally Unique Identifier	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Universally Unique Identifier.
		@return Universally Unique Identifier	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}