/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_Colonia
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Colonia extends PO implements I_I_EXME_Colonia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Colonia (Properties ctx, int I_EXME_Colonia_ID, String trxName)
    {
      super (ctx, I_EXME_Colonia_ID, trxName);
      /** if (I_EXME_Colonia_ID == 0)
        {
			setCodigo_Postal (null);
			setI_EXME_Colonia_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Colonia (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Colonia[")
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
			set_Value (COLUMNNAME_C_Country_ID, null);
		else 
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
		set_Value (COLUMNNAME_Colonia, Colonia);
	}

	/** Get Suburb / District.
		@return Suburb / District
	  */
	public String getColonia () 
	{
		return (String)get_Value(COLUMNNAME_Colonia);
	}

	/** Set Country_Value.
		@param Country_Value Country_Value	  */
	public void setCountry_Value (String Country_Value)
	{
		set_Value (COLUMNNAME_Country_Value, Country_Value);
	}

	/** Get Country_Value.
		@return Country_Value	  */
	public String getCountry_Value () 
	{
		return (String)get_Value(COLUMNNAME_Country_Value);
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
			set_Value (COLUMNNAME_C_Region_ID, null);
		else 
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

	public I_EXME_Colonia getEXME_Colonia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Colonia.Table_Name);
        I_EXME_Colonia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Colonia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Colonia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Suburb / District.
		@param EXME_Colonia_ID 
		Suburb / District
	  */
	public void setEXME_Colonia_ID (int EXME_Colonia_ID)
	{
		if (EXME_Colonia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Colonia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Colonia_ID, Integer.valueOf(EXME_Colonia_ID));
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
			set_Value (COLUMNNAME_EXME_TownCouncil_ID, null);
		else 
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

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set I_EXME_Colonia_ID.
		@param I_EXME_Colonia_ID I_EXME_Colonia_ID	  */
	public void setI_EXME_Colonia_ID (int I_EXME_Colonia_ID)
	{
		if (I_EXME_Colonia_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Colonia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Colonia_ID, Integer.valueOf(I_EXME_Colonia_ID));
	}

	/** Get I_EXME_Colonia_ID.
		@return I_EXME_Colonia_ID	  */
	public int getI_EXME_Colonia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Colonia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Region.
		@param RegionName 
		Name of the Region
	  */
	public void setRegionName (String RegionName)
	{
		set_Value (COLUMNNAME_RegionName, RegionName);
	}

	/** Get Region.
		@return Name of the Region
	  */
	public String getRegionName () 
	{
		return (String)get_Value(COLUMNNAME_RegionName);
	}

	/** Set Town Council Name.
		@param TownCouncilName Town Council Name	  */
	public void setTownCouncilName (String TownCouncilName)
	{
		set_Value (COLUMNNAME_TownCouncilName, TownCouncilName);
	}

	/** Get Town Council Name.
		@return Town Council Name	  */
	public String getTownCouncilName () 
	{
		return (String)get_Value(COLUMNNAME_TownCouncilName);
	}
}