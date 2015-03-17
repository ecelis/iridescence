/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Colonia
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Colonia extends PO implements I_EXME_Colonia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Colonia (Properties ctx, int EXME_Colonia_ID, String trxName)
    {
      super (ctx, EXME_Colonia_ID, trxName);
      /** if (EXME_Colonia_ID == 0)
        {
			setC_Country_ID (0);
			setCodigo_Postal (null);
			setColonia (null);
			setC_Region_ID (0);
			setEXME_Colonia_ID (0);
			setEXME_TownCouncil_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Colonia (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Colonia[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Country getC_Country() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Country.Table_Name);
        I_C_Country result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Country)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Country_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Country.
		@param C_Country_ID 
		Country 
	  */
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1)
			 throw new IllegalArgumentException ("C_Country_ID is mandatory.");
		set_Value (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set City.
		@param Ciudad 
		description of a city
	  */
	public void setCiudad (String Ciudad)
	{
		set_Value (COLUMNNAME_Ciudad, Ciudad);
	}

	/** Get City.
		@return description of a city
	  */
	public String getCiudad () 
	{
		return (String)get_Value(COLUMNNAME_Ciudad);
	}

	/** Set Postal Code.
		@param Codigo_Postal 
		Postal Code as a key
	  */
	public void setCodigo_Postal (String Codigo_Postal)
	{
		if (Codigo_Postal == null)
			throw new IllegalArgumentException ("Codigo_Postal is mandatory.");
		set_Value (COLUMNNAME_Codigo_Postal, Codigo_Postal);
	}

	/** Get Postal Code.
		@return Postal Code as a key
	  */
	public String getCodigo_Postal () 
	{
		return (String)get_Value(COLUMNNAME_Codigo_Postal);
	}

	/** Set Suburb / District.
		@param Colonia 
		Suburb / District
	  */
	public void setColonia (String Colonia)
	{
		if (Colonia == null)
			throw new IllegalArgumentException ("Colonia is mandatory.");
		set_Value (COLUMNNAME_Colonia, Colonia);
	}

	/** Get Suburb / District.
		@return Suburb / District
	  */
	public String getColonia () 
	{
		return (String)get_Value(COLUMNNAME_Colonia);
	}

	public I_C_Region getC_Region() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Region.Table_Name);
        I_C_Region result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Region)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Region_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Region.
		@param C_Region_ID 
		Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID)
	{
		if (C_Region_ID < 1)
			 throw new IllegalArgumentException ("C_Region_ID is mandatory.");
		set_Value (COLUMNNAME_C_Region_ID, Integer.valueOf(C_Region_ID));
	}

	/** Get Region.
		@return Identifies a geographical Region
	  */
	public int getC_Region_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Suburb / District.
		@param EXME_Colonia_ID 
		Suburb / District
	  */
	public void setEXME_Colonia_ID (int EXME_Colonia_ID)
	{
		if (EXME_Colonia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Colonia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Colonia_ID, Integer.valueOf(EXME_Colonia_ID));
	}

	/** Get Suburb / District.
		@return Suburb / District
	  */
	public int getEXME_Colonia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Colonia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TownCouncil getEXME_TownCouncil() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TownCouncil.Table_Name);
        I_EXME_TownCouncil result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TownCouncil)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TownCouncil_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set County.
		@param EXME_TownCouncil_ID 
		County
	  */
	public void setEXME_TownCouncil_ID (int EXME_TownCouncil_ID)
	{
		if (EXME_TownCouncil_ID < 1)
			 throw new IllegalArgumentException ("EXME_TownCouncil_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TownCouncil_ID, Integer.valueOf(EXME_TownCouncil_ID));
	}

	/** Get County.
		@return County
	  */
	public int getEXME_TownCouncil_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TownCouncil_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}