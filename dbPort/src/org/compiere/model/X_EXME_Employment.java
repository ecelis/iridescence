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

/** Generated Model for EXME_Employment
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Employment extends PO implements I_EXME_Employment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Employment (Properties ctx, int EXME_Employment_ID, String trxName)
    {
      super (ctx, EXME_Employment_ID, trxName);
      /** if (EXME_Employment_ID == 0)
        {
			setEXME_Employment_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Employment (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Employment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employment Information.
		@param EXME_Employment_ID 
		Employment Information
	  */
	public void setEXME_Employment_ID (int EXME_Employment_ID)
	{
		if (EXME_Employment_ID < 1)
			 throw new IllegalArgumentException ("EXME_Employment_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Employment_ID, Integer.valueOf(EXME_Employment_ID));
	}

	/** Get Employment Information.
		@return Employment Information
	  */
	public int getEXME_Employment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Employment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Puesto getEXME_Puesto() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Puesto.Table_Name);
        I_EXME_Puesto result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Puesto)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Puesto_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Job Position.
		@param EXME_Puesto_ID 
		Job Position
	  */
	public void setEXME_Puesto_ID (int EXME_Puesto_ID)
	{
		if (EXME_Puesto_ID < 1) 
			set_Value (COLUMNNAME_EXME_Puesto_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Puesto_ID, Integer.valueOf(EXME_Puesto_ID));
	}

	/** Get Job Position.
		@return Job Position
	  */
	public int getEXME_Puesto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Puesto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set 2nd Phone.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get 2nd Phone.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
	}

	/** Set 3nd Phone.
		@param Phone3 
		Identifies an alternate telephone number.
	  */
	public void setPhone3 (String Phone3)
	{
		set_Value (COLUMNNAME_Phone3, Phone3);
	}

	/** Get 3nd Phone.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone3 () 
	{
		return (String)get_Value(COLUMNNAME_Phone3);
	}

	/** Set Position.
		@param Puesto Position	  */
	public void setPuesto (String Puesto)
	{
		set_Value (COLUMNNAME_Puesto, Puesto);
	}

	/** Get Position.
		@return Position	  */
	public String getPuesto () 
	{
		return (String)get_Value(COLUMNNAME_Puesto);
	}

	/** Set Supervisor.
		@param Supervisor Supervisor	  */
	public void setSupervisor (String Supervisor)
	{
		set_Value (COLUMNNAME_Supervisor, Supervisor);
	}

	/** Get Supervisor.
		@return Supervisor	  */
	public String getSupervisor () 
	{
		return (String)get_Value(COLUMNNAME_Supervisor);
	}

	/** Set Telecommunication Code.
		@param Work1ComCode 
		Telecommunication Use Code Work Phone 1
	  */
	public void setWork1ComCode (String Work1ComCode)
	{
		set_Value (COLUMNNAME_Work1ComCode, Work1ComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Use Code Work Phone 1
	  */
	public String getWork1ComCode () 
	{
		return (String)get_Value(COLUMNNAME_Work1ComCode);
	}

	/** Set Telecommunication Code.
		@param Work2ComCode 
		Telecommunication Code Work Phone 2
	  */
	public void setWork2ComCode (String Work2ComCode)
	{
		set_Value (COLUMNNAME_Work2ComCode, Work2ComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Code Work Phone 2
	  */
	public String getWork2ComCode () 
	{
		return (String)get_Value(COLUMNNAME_Work2ComCode);
	}

	/** Set Telecommunication Code.
		@param Work3ComCode 
		Telecommunication Code Work Phone 3
	  */
	public void setWork3ComCode (String Work3ComCode)
	{
		set_Value (COLUMNNAME_Work3ComCode, Work3ComCode);
	}

	/** Get Telecommunication Code.
		@return Telecommunication Code Work Phone 3
	  */
	public String getWork3ComCode () 
	{
		return (String)get_Value(COLUMNNAME_Work3ComCode);
	}
}