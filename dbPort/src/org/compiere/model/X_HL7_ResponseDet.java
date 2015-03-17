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

/** Generated Model for HL7_ResponseDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_ResponseDet extends PO implements I_HL7_ResponseDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_ResponseDet (Properties ctx, int HL7_ResponseDet_ID, String trxName)
    {
      super (ctx, HL7_ResponseDet_ID, trxName);
      /** if (HL7_ResponseDet_ID == 0)
        {
			setHL7_MessageConf_ID (0);
			setHL7_ResponseDet_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_ResponseDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_ResponseDet[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_HL7_MessageConf getHL7_MessageConf() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_MessageConf.Table_Name);
        I_HL7_MessageConf result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_MessageConf)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_MessageConf_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Message Configuration.
		@param HL7_MessageConf_ID 
		Configuration of message and the process that generate them
	  */
	public void setHL7_MessageConf_ID (int HL7_MessageConf_ID)
	{
		if (HL7_MessageConf_ID < 1)
			 throw new IllegalArgumentException ("HL7_MessageConf_ID is mandatory.");
		set_Value (COLUMNNAME_HL7_MessageConf_ID, Integer.valueOf(HL7_MessageConf_ID));
	}

	/** Get HL7 Message Configuration.
		@return Configuration of message and the process that generate them
	  */
	public int getHL7_MessageConf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_MessageConf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Response Details.
		@param HL7_ResponseDet_ID Response Details	  */
	public void setHL7_ResponseDet_ID (int HL7_ResponseDet_ID)
	{
		if (HL7_ResponseDet_ID < 1)
			 throw new IllegalArgumentException ("HL7_ResponseDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_ResponseDet_ID, Integer.valueOf(HL7_ResponseDet_ID));
	}

	/** Get Response Details.
		@return Response Details	  */
	public int getHL7_ResponseDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_ResponseDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Parent Segment.
		@param HL7_ResponseDetParent_ID Parent Segment	  */
	public void setHL7_ResponseDetParent_ID (int HL7_ResponseDetParent_ID)
	{
		if (HL7_ResponseDetParent_ID < 1) 
			set_Value (COLUMNNAME_HL7_ResponseDetParent_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_ResponseDetParent_ID, Integer.valueOf(HL7_ResponseDetParent_ID));
	}

	/** Get Parent Segment.
		@return Parent Segment	  */
	public int getHL7_ResponseDetParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_ResponseDetParent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Group.
		@param IsGroup 
		Marks this segment as a group
	  */
	public void setIsGroup (boolean IsGroup)
	{
		set_Value (COLUMNNAME_IsGroup, Boolean.valueOf(IsGroup));
	}

	/** Get Is Group.
		@return Marks this segment as a group
	  */
	public boolean isGroup () 
	{
		Object oo = get_Value(COLUMNNAME_IsGroup);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Script.
		@param Script 
		Dynamic Java Language Script to calculate result
	  */
	public void setScript (String Script)
	{
		set_Value (COLUMNNAME_Script, Script);
	}

	/** Get Script.
		@return Dynamic Java Language Script to calculate result
	  */
	public String getScript () 
	{
		return (String)get_Value(COLUMNNAME_Script);
	}
}