/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_InterfazProcessorLog
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_InterfazProcessorLog extends PO implements I_EXME_InterfazProcessorLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_InterfazProcessorLog (Properties ctx, int EXME_InterfazProcessorLog_ID, String trxName)
    {
      super (ctx, EXME_InterfazProcessorLog_ID, trxName);
      /** if (EXME_InterfazProcessorLog_ID == 0)
        {
			setEXME_InterfazProcessor_ID (0);
			setEXME_InterfazProcessorLog_ID (0);
			setIsError (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_InterfazProcessorLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_InterfazProcessorLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BinaryData.
		@param BinaryData 
		Binary Data
	  */
	public void setBinaryData (byte[] BinaryData)
	{
		set_Value (COLUMNNAME_BinaryData, BinaryData);
	}

	/** Get BinaryData.
		@return Binary Data
	  */
	public byte[] getBinaryData () 
	{
		return (byte[])get_Value(COLUMNNAME_BinaryData);
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

	public I_EXME_InterfazProcessor getEXME_InterfazProcessor() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_InterfazProcessor.Table_Name);
        I_EXME_InterfazProcessor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_InterfazProcessor)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_InterfazProcessor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Processor Interface.
		@param EXME_InterfazProcessor_ID Processor Interface	  */
	public void setEXME_InterfazProcessor_ID (int EXME_InterfazProcessor_ID)
	{
		if (EXME_InterfazProcessor_ID < 1)
			 throw new IllegalArgumentException ("EXME_InterfazProcessor_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_InterfazProcessor_ID, Integer.valueOf(EXME_InterfazProcessor_ID));
	}

	/** Get Processor Interface.
		@return Processor Interface	  */
	public int getEXME_InterfazProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InterfazProcessor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processor Interface Log.
		@param EXME_InterfazProcessorLog_ID 
		Processor Interface Log
	  */
	public void setEXME_InterfazProcessorLog_ID (int EXME_InterfazProcessorLog_ID)
	{
		if (EXME_InterfazProcessorLog_ID < 1)
			 throw new IllegalArgumentException ("EXME_InterfazProcessorLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_InterfazProcessorLog_ID, Integer.valueOf(EXME_InterfazProcessorLog_ID));
	}

	/** Get Processor Interface Log.
		@return Processor Interface Log
	  */
	public int getEXME_InterfazProcessorLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InterfazProcessorLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Error.
		@param IsError 
		An Error occured in the execution
	  */
	public void setIsError (boolean IsError)
	{
		set_Value (COLUMNNAME_IsError, Boolean.valueOf(IsError));
	}

	/** Get Error.
		@return An Error occured in the execution
	  */
	public boolean isError () 
	{
		Object oo = get_Value(COLUMNNAME_IsError);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Reference.
		@param Reference 
		Reference for this record
	  */
	public void setReference (String Reference)
	{
		set_Value (COLUMNNAME_Reference, Reference);
	}

	/** Get Reference.
		@return Reference for this record
	  */
	public String getReference () 
	{
		return (String)get_Value(COLUMNNAME_Reference);
	}

	/** Set Summary.
		@param Summary 
		Textual summary of this request
	  */
	public void setSummary (String Summary)
	{
		set_Value (COLUMNNAME_Summary, Summary);
	}

	/** Get Summary.
		@return Textual summary of this request
	  */
	public String getSummary () 
	{
		return (String)get_Value(COLUMNNAME_Summary);
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
	}
}