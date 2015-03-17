/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MedEstServ
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MedEstServ extends PO implements I_EXME_MedEstServ, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MedEstServ (Properties ctx, int EXME_MedEstServ_ID, String trxName)
    {
      super (ctx, EXME_MedEstServ_ID, trxName);
      /** if (EXME_MedEstServ_ID == 0)
        {
			setEXME_EstServ_ID (0);
			setEXME_MedEstServ_ID (0);
			setEXME_Medico_ID (0);
			setFriday (false);
			setMonday (false);
			setSaturday (false);
			setSunday (false);
			setThursday (false);
			setTuesday (false);
			setWednesday (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_MedEstServ (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_MedEstServ[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Physician Per Service Station.
		@param EXME_MedEstServ_ID Physician Per Service Station	  */
	public void setEXME_MedEstServ_ID (int EXME_MedEstServ_ID)
	{
		if (EXME_MedEstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_MedEstServ_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MedEstServ_ID, Integer.valueOf(EXME_MedEstServ_ID));
	}

	/** Get Physician Per Service Station.
		@return Physician Per Service Station	  */
	public int getEXME_MedEstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedEstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Friday.
		@param Friday Friday	  */
	public void setFriday (boolean Friday)
	{
		set_Value (COLUMNNAME_Friday, Boolean.valueOf(Friday));
	}

	/** Get Friday.
		@return Friday	  */
	public boolean isFriday () 
	{
		Object oo = get_Value(COLUMNNAME_Friday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Monday.
		@param Monday Monday	  */
	public void setMonday (boolean Monday)
	{
		set_Value (COLUMNNAME_Monday, Boolean.valueOf(Monday));
	}

	/** Get Monday.
		@return Monday	  */
	public boolean isMonday () 
	{
		Object oo = get_Value(COLUMNNAME_Monday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Saturday.
		@param Saturday Saturday	  */
	public void setSaturday (boolean Saturday)
	{
		set_Value (COLUMNNAME_Saturday, Boolean.valueOf(Saturday));
	}

	/** Get Saturday.
		@return Saturday	  */
	public boolean isSaturday () 
	{
		Object oo = get_Value(COLUMNNAME_Saturday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sunday.
		@param Sunday Sunday	  */
	public void setSunday (boolean Sunday)
	{
		set_Value (COLUMNNAME_Sunday, Boolean.valueOf(Sunday));
	}

	/** Get Sunday.
		@return Sunday	  */
	public boolean isSunday () 
	{
		Object oo = get_Value(COLUMNNAME_Sunday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Thursday.
		@param Thursday Thursday	  */
	public void setThursday (boolean Thursday)
	{
		set_Value (COLUMNNAME_Thursday, Boolean.valueOf(Thursday));
	}

	/** Get Thursday.
		@return Thursday	  */
	public boolean isThursday () 
	{
		Object oo = get_Value(COLUMNNAME_Thursday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tuesday.
		@param Tuesday Tuesday	  */
	public void setTuesday (boolean Tuesday)
	{
		set_Value (COLUMNNAME_Tuesday, Boolean.valueOf(Tuesday));
	}

	/** Get Tuesday.
		@return Tuesday	  */
	public boolean isTuesday () 
	{
		Object oo = get_Value(COLUMNNAME_Tuesday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Wednesday.
		@param Wednesday Wednesday	  */
	public void setWednesday (boolean Wednesday)
	{
		set_Value (COLUMNNAME_Wednesday, Boolean.valueOf(Wednesday));
	}

	/** Get Wednesday.
		@return Wednesday	  */
	public boolean isWednesday () 
	{
		Object oo = get_Value(COLUMNNAME_Wednesday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}