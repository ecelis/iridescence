/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_PInstance_Para
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_PInstance_Para extends PO implements I_AD_PInstance_Para, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_PInstance_Para (Properties ctx, int AD_PInstance_Para_ID, String trxName)
    {
      super (ctx, AD_PInstance_Para_ID, trxName);
      /** if (AD_PInstance_Para_ID == 0)
        {
			setAD_PInstance_ID (0);
			setSeqNo (0);
        } */
    }

    /** Load Constructor */
    public X_AD_PInstance_Para (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_PInstance_Para[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_PInstance getAD_PInstance() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PInstance.Table_Name);
        I_AD_PInstance result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PInstance)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PInstance_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1)
			 throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
	}

	/** Get Process Instance.
		@return Instance of the process
	  */
	public int getAD_PInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Info.
		@param Info 
		Information
	  */
	public void setInfo (String Info)
	{
		set_Value (COLUMNNAME_Info, Info);
	}

	/** Get Info.
		@return Information
	  */
	public String getInfo () 
	{
		return (String)get_Value(COLUMNNAME_Info);
	}

	/** Set Info To.
		@param Info_To Info To	  */
	public void setInfo_To (String Info_To)
	{
		set_Value (COLUMNNAME_Info_To, Info_To);
	}

	/** Get Info To.
		@return Info To	  */
	public String getInfo_To () 
	{
		return (String)get_Value(COLUMNNAME_Info_To);
	}

	/** Set Parameter Name.
		@param ParameterName Parameter Name	  */
	public void setParameterName (String ParameterName)
	{
		set_Value (COLUMNNAME_ParameterName, ParameterName);
	}

	/** Get Parameter Name.
		@return Parameter Name	  */
	public String getParameterName () 
	{
		return (String)get_Value(COLUMNNAME_ParameterName);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getParameterName());
    }

	/** Set Process Date.
		@param P_Date 
		Process Parameter
	  */
	public void setP_Date (Timestamp P_Date)
	{
		set_Value (COLUMNNAME_P_Date, P_Date);
	}

	/** Get Process Date.
		@return Process Parameter
	  */
	public Timestamp getP_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_P_Date);
	}

	/** Set Process Date To.
		@param P_Date_To 
		Process Parameter
	  */
	public void setP_Date_To (Timestamp P_Date_To)
	{
		set_Value (COLUMNNAME_P_Date_To, P_Date_To);
	}

	/** Get Process Date To.
		@return Process Parameter
	  */
	public Timestamp getP_Date_To () 
	{
		return (Timestamp)get_Value(COLUMNNAME_P_Date_To);
	}

	/** Set Process Number.
		@param P_Number 
		Process Parameter
	  */
	public void setP_Number (BigDecimal P_Number)
	{
		set_Value (COLUMNNAME_P_Number, P_Number);
	}

	/** Get Process Number.
		@return Process Parameter
	  */
	public BigDecimal getP_Number () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_P_Number);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Process Number To.
		@param P_Number_To 
		Process Parameter
	  */
	public void setP_Number_To (BigDecimal P_Number_To)
	{
		set_Value (COLUMNNAME_P_Number_To, P_Number_To);
	}

	/** Get Process Number To.
		@return Process Parameter
	  */
	public BigDecimal getP_Number_To () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_P_Number_To);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Process String.
		@param P_String 
		Process Parameter
	  */
	public void setP_String (String P_String)
	{
		set_Value (COLUMNNAME_P_String, P_String);
	}

	/** Get Process String.
		@return Process Parameter
	  */
	public String getP_String () 
	{
		return (String)get_Value(COLUMNNAME_P_String);
	}

	/** Set Process String To.
		@param P_String_To 
		Process Parameter
	  */
	public void setP_String_To (String P_String_To)
	{
		set_Value (COLUMNNAME_P_String_To, P_String_To);
	}

	/** Get Process String To.
		@return Process Parameter
	  */
	public String getP_String_To () 
	{
		return (String)get_Value(COLUMNNAME_P_String_To);
	}

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_ValueNoCheck (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
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
}