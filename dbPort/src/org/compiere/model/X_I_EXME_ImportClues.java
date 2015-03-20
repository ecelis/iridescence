/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_ImportClues
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_ImportClues extends PO implements I_I_EXME_ImportClues, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_ImportClues (Properties ctx, int I_EXME_ImportClues_ID, String trxName)
    {
      super (ctx, I_EXME_ImportClues_ID, trxName);
      /** if (I_EXME_ImportClues_ID == 0)
        {
			setI_EXME_ImportClues_ID (0);
			setI_IsImported (false);
			setOrgName (null);
			setOrgValue (null);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_ImportClues (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_ImportClues[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address 1.
		@param Address1 
		Address line 1 for this location
	  */
	public void setAddress1 (String Address1)
	{
		set_Value (COLUMNNAME_Address1, Address1);
	}

	/** Get Address 1.
		@return Address line 1 for this location
	  */
	public String getAddress1 () 
	{
		return (String)get_Value(COLUMNNAME_Address1);
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

	public I_EXME_Localidad getEXME_Localidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Localidad.Table_Name);
        I_EXME_Localidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Localidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Localidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Locality.
		@param EXME_Localidad_ID 
		Locality
	  */
	public void setEXME_Localidad_ID (int EXME_Localidad_ID)
	{
		if (EXME_Localidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Localidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Localidad_ID, Integer.valueOf(EXME_Localidad_ID));
	}

	/** Get Locality.
		@return Locality
	  */
	public int getEXME_Localidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Localidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Locality Name.
		@param EXME_Localidad_Name 
		Locality Name
	  */
	public void setEXME_Localidad_Name (String EXME_Localidad_Name)
	{
		set_Value (COLUMNNAME_EXME_Localidad_Name, EXME_Localidad_Name);
	}

	/** Get Locality Name.
		@return Locality Name
	  */
	public String getEXME_Localidad_Name () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Localidad_Name);
	}

	/** Set Locality Value.
		@param EXME_Localidad_Value 
		Locality
	  */
	public void setEXME_Localidad_Value (String EXME_Localidad_Value)
	{
		set_Value (COLUMNNAME_EXME_Localidad_Value, EXME_Localidad_Value);
	}

	/** Get Locality Value.
		@return Locality
	  */
	public String getEXME_Localidad_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Localidad_Value);
	}

	public I_EXME_Tipologia getEXME_Tipologia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tipologia.Table_Name);
        I_EXME_Tipologia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tipologia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tipologia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Typology.
		@param EXME_Tipologia_ID Typology	  */
	public void setEXME_Tipologia_ID (int EXME_Tipologia_ID)
	{
		if (EXME_Tipologia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tipologia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tipologia_ID, Integer.valueOf(EXME_Tipologia_ID));
	}

	/** Get Typology.
		@return Typology	  */
	public int getEXME_Tipologia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tipologia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Typology's name.
		@param EXME_Tipologia_Name Typology's name	  */
	public void setEXME_Tipologia_Name (String EXME_Tipologia_Name)
	{
		set_Value (COLUMNNAME_EXME_Tipologia_Name, EXME_Tipologia_Name);
	}

	/** Get Typology's name.
		@return Typology's name	  */
	public String getEXME_Tipologia_Name () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Tipologia_Name);
	}

	/** Set Typology.
		@param EXME_Tipologia_Value Typology	  */
	public void setEXME_Tipologia_Value (String EXME_Tipologia_Value)
	{
		set_Value (COLUMNNAME_EXME_Tipologia_Value, EXME_Tipologia_Value);
	}

	/** Get Typology.
		@return Typology	  */
	public String getEXME_Tipologia_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Tipologia_Value);
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

	/** Set Town Council Name.
		@param EXME_TownCouncil_Name 
		Town Council Name
	  */
	public void setEXME_TownCouncil_Name (String EXME_TownCouncil_Name)
	{
		set_Value (COLUMNNAME_EXME_TownCouncil_Name, EXME_TownCouncil_Name);
	}

	/** Get Town Council Name.
		@return Town Council Name
	  */
	public String getEXME_TownCouncil_Name () 
	{
		return (String)get_Value(COLUMNNAME_EXME_TownCouncil_Name);
	}

	/** Set EXME_TownCouncil_Value.
		@param EXME_TownCouncil_Value EXME_TownCouncil_Value	  */
	public void setEXME_TownCouncil_Value (String EXME_TownCouncil_Value)
	{
		set_Value (COLUMNNAME_EXME_TownCouncil_Value, EXME_TownCouncil_Value);
	}

	/** Get EXME_TownCouncil_Value.
		@return EXME_TownCouncil_Value	  */
	public String getEXME_TownCouncil_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_TownCouncil_Value);
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

	/** Set Import CLUES.
		@param I_EXME_ImportClues_ID 
		Import CLUES
	  */
	public void setI_EXME_ImportClues_ID (int I_EXME_ImportClues_ID)
	{
		if (I_EXME_ImportClues_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_ImportClues_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_ImportClues_ID, Integer.valueOf(I_EXME_ImportClues_ID));
	}

	/** Get Import CLUES.
		@return Import CLUES
	  */
	public int getI_EXME_ImportClues_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_ImportClues_ID);
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

	/** Set Organization.
		@param Org_ID 
		Organizational entity within client
	  */
	public void setOrg_ID (int Org_ID)
	{
		if (Org_ID < 1) 
			set_Value (COLUMNNAME_Org_ID, null);
		else 
			set_Value (COLUMNNAME_Org_ID, Integer.valueOf(Org_ID));
	}

	/** Get Organization.
		@return Organizational entity within client
	  */
	public int getOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Organization Name.
		@param OrgName 
		Name of the Organization
	  */
	public void setOrgName (String OrgName)
	{
		if (OrgName == null)
			throw new IllegalArgumentException ("OrgName is mandatory.");
		set_Value (COLUMNNAME_OrgName, OrgName);
	}

	/** Get Organization Name.
		@return Name of the Organization
	  */
	public String getOrgName () 
	{
		return (String)get_Value(COLUMNNAME_OrgName);
	}

	/** Set Organization Key.
		@param OrgValue 
		Key of the Organization
	  */
	public void setOrgValue (String OrgValue)
	{
		if (OrgValue == null)
			throw new IllegalArgumentException ("OrgValue is mandatory.");
		set_Value (COLUMNNAME_OrgValue, OrgValue);
	}

	/** Get Organization Key.
		@return Key of the Organization
	  */
	public String getOrgValue () 
	{
		return (String)get_Value(COLUMNNAME_OrgValue);
	}

	/** Set ZIP.
		@param Postal 
		Postal code
	  */
	public void setPostal (String Postal)
	{
		set_Value (COLUMNNAME_Postal, Postal);
	}

	/** Get ZIP.
		@return Postal code
	  */
	public String getPostal () 
	{
		return (String)get_Value(COLUMNNAME_Postal);
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
		@param Region Region	  */
	public void setRegion (String Region)
	{
		set_Value (COLUMNNAME_Region, Region);
	}

	/** Get Region.
		@return Region	  */
	public String getRegion () 
	{
		return (String)get_Value(COLUMNNAME_Region);
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

	/** Set Institution Type.
		@param TipoInstitucion Institution Type	  */
	public void setTipoInstitucion (String TipoInstitucion)
	{
		set_Value (COLUMNNAME_TipoInstitucion, TipoInstitucion);
	}

	/** Get Institution Type.
		@return Institution Type	  */
	public String getTipoInstitucion () 
	{
		return (String)get_Value(COLUMNNAME_TipoInstitucion);
	}

	/** Set Unity Type.
		@param TipoUnidad Unity Type	  */
	public void setTipoUnidad (String TipoUnidad)
	{
		set_Value (COLUMNNAME_TipoUnidad, TipoUnidad);
	}

	/** Get Unity Type.
		@return Unity Type	  */
	public String getTipoUnidad () 
	{
		return (String)get_Value(COLUMNNAME_TipoUnidad);
	}
}