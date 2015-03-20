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

/** Generated Model for C_Location
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_Location extends PO implements I_C_Location, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_Location (Properties ctx, int C_Location_ID, String trxName)
    {
      super (ctx, C_Location_ID, trxName);
      /** if (C_Location_ID == 0)
        {
			setC_Country_ID (0);
			setC_Location_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_Location (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_C_Location[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AddressType AD_Reference_ID=1200469 */
	public static final int ADDRESSTYPE_AD_Reference_ID=1200469;
	/** Firm/Business = B */
	public static final String ADDRESSTYPE_FirmBusiness = "B";
	/** Bad Address = BA */
	public static final String ADDRESSTYPE_BadAddress = "BA";
	/** Birth Delivery location  = BDL */
	public static final String ADDRESSTYPE_BirthDeliveryLocation = "BDL";
	/** Residence at Birth = BR */
	public static final String ADDRESSTYPE_ResidenceAtBirth = "BR";
	/** Current or Temporary = C */
	public static final String ADDRESSTYPE_CurrentOrTemporary = "C";
	/** Country of Origin = F */
	public static final String ADDRESSTYPE_CountryOfOrigin = "F";
	/** Home = H */
	public static final String ADDRESSTYPE_Home = "H";
	/** Legal Address = L */
	public static final String ADDRESSTYPE_LegalAddress = "L";
	/** Mailing = M */
	public static final String ADDRESSTYPE_Mailing = "M";
	/** Birth (nee) = N */
	public static final String ADDRESSTYPE_BirthNee = "N";
	/** Office = O */
	public static final String ADDRESSTYPE_Office = "O";
	/** Permanent = P */
	public static final String ADDRESSTYPE_Permanent = "P";
	/** Registry Home = RH */
	public static final String ADDRESSTYPE_RegistryHome = "RH";
	/** Set Address Type.
		@param AddressType Address Type	  */
	public void setAddressType (String AddressType)
	{

		if (AddressType == null || AddressType.equals("B") || AddressType.equals("BA") || AddressType.equals("BDL") || AddressType.equals("BR") || AddressType.equals("C") || AddressType.equals("F") || AddressType.equals("H") || AddressType.equals("L") || AddressType.equals("M") || AddressType.equals("N") || AddressType.equals("O") || AddressType.equals("P") || AddressType.equals("RH")); else throw new IllegalArgumentException ("AddressType Invalid value - " + AddressType + " - Reference_ID=1200469 - B - BA - BDL - BR - C - F - H - L - M - N - O - P - RH");		set_Value (COLUMNNAME_AddressType, AddressType);
	}

	/** Get Address Type.
		@return Address Type	  */
	public String getAddressType () 
	{
		return (String)get_Value(COLUMNNAME_AddressType);
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

	/** Set Address 2.
		@param Address2 
		Address line 2 for this location
	  */
	public void setAddress2 (String Address2)
	{
		set_Value (COLUMNNAME_Address2, Address2);
	}

	/** Get Address 2.
		@return Address line 2 for this location
	  */
	public String getAddress2 () 
	{
		return (String)get_Value(COLUMNNAME_Address2);
	}

	/** Set Address 3.
		@param Address3 
		Address Line 3 for the location
	  */
	public void setAddress3 (String Address3)
	{
		set_Value (COLUMNNAME_Address3, Address3);
	}

	/** Get Address 3.
		@return Address Line 3 for the location
	  */
	public String getAddress3 () 
	{
		return (String)get_Value(COLUMNNAME_Address3);
	}

	/** Set Address 4.
		@param Address4 
		Address Line 4 for the location
	  */
	public void setAddress4 (String Address4)
	{
		set_Value (COLUMNNAME_Address4, Address4);
	}

	/** Get Address 4.
		@return Address Line 4 for the location
	  */
	public String getAddress4 () 
	{
		return (String)get_Value(COLUMNNAME_Address4);
	}

	public I_C_City getC_City() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_City.Table_Name);
        I_C_City result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_City)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_City_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set City.
		@param C_City_ID 
		City
	  */
	public void setC_City_ID (int C_City_ID)
	{
		if (C_City_ID < 1) 
			set_Value (COLUMNNAME_C_City_ID, null);
		else 
			set_Value (COLUMNNAME_C_City_ID, Integer.valueOf(C_City_ID));
	}

	/** Get City.
		@return City
	  */
	public int getC_City_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_City_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		@param City 
		Identifies a City
	  */
	public void setCity (String City)
	{
		set_Value (COLUMNNAME_City, City);
	}

	/** Get City.
		@return Identifies a City
	  */
	public String getCity () 
	{
		return (String)get_Value(COLUMNNAME_City);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getCity());
    }

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1)
			 throw new IllegalArgumentException ("C_Location_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
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

	/** Set External Num.
		@param NumExt 
		External Number
	  */
	public void setNumExt (String NumExt)
	{
		set_Value (COLUMNNAME_NumExt, NumExt);
	}

	/** Get External Num.
		@return External Number
	  */
	public String getNumExt () 
	{
		return (String)get_Value(COLUMNNAME_NumExt);
	}

	/** Set Internal Num.
		@param NumIn 
		Internal Number
	  */
	public void setNumIn (String NumIn)
	{
		set_Value (COLUMNNAME_NumIn, NumIn);
	}

	/** Get Internal Num.
		@return Internal Number
	  */
	public String getNumIn () 
	{
		return (String)get_Value(COLUMNNAME_NumIn);
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

	/** Set -.
		@param Postal_Add 
		Additional ZIP or Postal code
	  */
	public void setPostal_Add (String Postal_Add)
	{
		set_Value (COLUMNNAME_Postal_Add, Postal_Add);
	}

	/** Get -.
		@return Additional ZIP or Postal code
	  */
	public String getPostal_Add () 
	{
		return (String)get_Value(COLUMNNAME_Postal_Add);
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
}