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

/** Generated Model for R_Status
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_R_Status extends PO implements I_R_Status, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_R_Status (Properties ctx, int R_Status_ID, String trxName)
    {
      super (ctx, R_Status_ID, trxName);
      /** if (R_Status_ID == 0)
        {
			setIsClosed (false);
// N
			setIsDefault (false);
			setIsFinalClose (false);
// N
			setIsOpen (false);
			setIsWebCanUpdate (false);
			setName (null);
			setR_StatusCategory_ID (0);
			setR_Status_ID (0);
			setSeqNo (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_R_Status (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_R_Status[")
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Closed Status.
		@param IsClosed 
		The status is closed
	  */
	public void setIsClosed (boolean IsClosed)
	{
		set_Value (COLUMNNAME_IsClosed, Boolean.valueOf(IsClosed));
	}

	/** Get Closed Status.
		@return The status is closed
	  */
	public boolean isClosed () 
	{
		Object oo = get_Value(COLUMNNAME_IsClosed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Final Close.
		@param IsFinalClose 
		Entries with Final Close cannot be re-opened
	  */
	public void setIsFinalClose (boolean IsFinalClose)
	{
		set_Value (COLUMNNAME_IsFinalClose, Boolean.valueOf(IsFinalClose));
	}

	/** Get Final Close.
		@return Entries with Final Close cannot be re-opened
	  */
	public boolean isFinalClose () 
	{
		Object oo = get_Value(COLUMNNAME_IsFinalClose);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open Status.
		@param IsOpen 
		The status is closed
	  */
	public void setIsOpen (boolean IsOpen)
	{
		set_Value (COLUMNNAME_IsOpen, Boolean.valueOf(IsOpen));
	}

	/** Get Open Status.
		@return The status is closed
	  */
	public boolean isOpen () 
	{
		Object oo = get_Value(COLUMNNAME_IsOpen);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Web Can Update.
		@param IsWebCanUpdate 
		Entry can be updated from the Web
	  */
	public void setIsWebCanUpdate (boolean IsWebCanUpdate)
	{
		set_Value (COLUMNNAME_IsWebCanUpdate, Boolean.valueOf(IsWebCanUpdate));
	}

	/** Get Web Can Update.
		@return Entry can be updated from the Web
	  */
	public boolean isWebCanUpdate () 
	{
		Object oo = get_Value(COLUMNNAME_IsWebCanUpdate);
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

	/** Set Next Status.
		@param Next_Status_ID 
		Move to next status automatically after timeout
	  */
	public void setNext_Status_ID (int Next_Status_ID)
	{
		if (Next_Status_ID < 1) 
			set_Value (COLUMNNAME_Next_Status_ID, null);
		else 
			set_Value (COLUMNNAME_Next_Status_ID, Integer.valueOf(Next_Status_ID));
	}

	/** Get Next Status.
		@return Move to next status automatically after timeout
	  */
	public int getNext_Status_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Next_Status_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_StatusCategory getR_StatusCategory() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_R_StatusCategory.Table_Name);
        I_R_StatusCategory result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_StatusCategory)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_StatusCategory_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Status Category.
		@param R_StatusCategory_ID 
		Request Status Category
	  */
	public void setR_StatusCategory_ID (int R_StatusCategory_ID)
	{
		if (R_StatusCategory_ID < 1)
			 throw new IllegalArgumentException ("R_StatusCategory_ID is mandatory.");
		set_Value (COLUMNNAME_R_StatusCategory_ID, Integer.valueOf(R_StatusCategory_ID));
	}

	/** Get Status Category.
		@return Request Status Category
	  */
	public int getR_StatusCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_StatusCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Status.
		@param R_Status_ID 
		Request Status
	  */
	public void setR_Status_ID (int R_Status_ID)
	{
		if (R_Status_ID < 1)
			 throw new IllegalArgumentException ("R_Status_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_Status_ID, Integer.valueOf(R_Status_ID));
	}

	/** Get Status.
		@return Request Status
	  */
	public int getR_Status_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Status_ID);
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

	/** Set Timeout in Days.
		@param TimeoutDays 
		Timeout in Days to change Status automatically
	  */
	public void setTimeoutDays (int TimeoutDays)
	{
		set_Value (COLUMNNAME_TimeoutDays, Integer.valueOf(TimeoutDays));
	}

	/** Get Timeout in Days.
		@return Timeout in Days to change Status automatically
	  */
	public int getTimeoutDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TimeoutDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Update Status.
		@param Update_Status_ID 
		Automatically change the status after entry from web
	  */
	public void setUpdate_Status_ID (int Update_Status_ID)
	{
		if (Update_Status_ID < 1) 
			set_Value (COLUMNNAME_Update_Status_ID, null);
		else 
			set_Value (COLUMNNAME_Update_Status_ID, Integer.valueOf(Update_Status_ID));
	}

	/** Get Update Status.
		@return Automatically change the status after entry from web
	  */
	public int getUpdate_Status_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Update_Status_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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