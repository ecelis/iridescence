/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for R_RequestProcessor_Route
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_R_RequestProcessor_Route extends PO implements I_R_RequestProcessor_Route, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_R_RequestProcessor_Route (Properties ctx, int R_RequestProcessor_Route_ID, String trxName)
    {
      super (ctx, R_RequestProcessor_Route_ID, trxName);
      /** if (R_RequestProcessor_Route_ID == 0)
        {
			setAD_User_ID (0);
			setR_RequestProcessor_ID (0);
			setR_RequestProcessor_Route_ID (0);
			setSeqNo (0);
        } */
    }

    /** Load Constructor */
    public X_R_RequestProcessor_Route (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_R_RequestProcessor_Route[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Keyword.
		@param Keyword 
		Case insensitive keyword
	  */
	public void setKeyword (String Keyword)
	{
		set_Value (COLUMNNAME_Keyword, Keyword);
	}

	/** Get Keyword.
		@return Case insensitive keyword
	  */
	public String getKeyword () 
	{
		return (String)get_Value(COLUMNNAME_Keyword);
	}

	public I_R_RequestProcessor getR_RequestProcessor() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_R_RequestProcessor.Table_Name);
        I_R_RequestProcessor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_RequestProcessor)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_RequestProcessor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Request Processor.
		@param R_RequestProcessor_ID 
		Processor for Requests
	  */
	public void setR_RequestProcessor_ID (int R_RequestProcessor_ID)
	{
		if (R_RequestProcessor_ID < 1)
			 throw new IllegalArgumentException ("R_RequestProcessor_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_RequestProcessor_ID, Integer.valueOf(R_RequestProcessor_ID));
	}

	/** Get Request Processor.
		@return Processor for Requests
	  */
	public int getR_RequestProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestProcessor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Request Routing.
		@param R_RequestProcessor_Route_ID 
		Automatic routing of requests
	  */
	public void setR_RequestProcessor_Route_ID (int R_RequestProcessor_Route_ID)
	{
		if (R_RequestProcessor_Route_ID < 1)
			 throw new IllegalArgumentException ("R_RequestProcessor_Route_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_RequestProcessor_Route_ID, Integer.valueOf(R_RequestProcessor_Route_ID));
	}

	/** Get Request Routing.
		@return Automatic routing of requests
	  */
	public int getR_RequestProcessor_Route_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestProcessor_Route_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_RequestType getR_RequestType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_R_RequestType.Table_Name);
        I_R_RequestType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_RequestType)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_RequestType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Request Type.
		@param R_RequestType_ID 
		Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public void setR_RequestType_ID (int R_RequestType_ID)
	{
		if (R_RequestType_ID < 1) 
			set_Value (COLUMNNAME_R_RequestType_ID, null);
		else 
			set_Value (COLUMNNAME_R_RequestType_ID, Integer.valueOf(R_RequestType_ID));
	}

	/** Get Request Type.
		@return Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public int getR_RequestType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence Number.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
    }
}