/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_FarmaciaV
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_FarmaciaV extends PO implements I_EXME_FarmaciaV, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FarmaciaV (Properties ctx, int EXME_FarmaciaV_ID, String trxName)
    {
      super (ctx, EXME_FarmaciaV_ID, trxName);
      /** if (EXME_FarmaciaV_ID == 0)
        {
			setC_Country_ID (0);
			setC_Region_ID (0);
			setEXME_FarmaciaV_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FarmaciaV (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FarmaciaV[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address 1.
		@param Address1 
		Address line 1 for this location
	  */
	public void setAddress1 (String Address1)
	{
		set_ValueNoCheck (COLUMNNAME_Address1, Address1);
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
		set_ValueNoCheck (COLUMNNAME_Address2, Address2);
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
		set_ValueNoCheck (COLUMNNAME_Address3, Address3);
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
		set_ValueNoCheck (COLUMNNAME_Address4, Address4);
	}

	/** Get Address 4.
		@return Address Line 4 for the location
	  */
	public String getAddress4 () 
	{
		return (String)get_Value(COLUMNNAME_Address4);
	}

	/** Set Cancel Rx capable.
		@param CanRx 
		Cancel Rx capable
	  */
	public void setCanRx (boolean CanRx)
	{
		set_Value (COLUMNNAME_CanRx, Boolean.valueOf(CanRx));
	}

	/** Get Cancel Rx capable.
		@return Cancel Rx capable
	  */
	public boolean isCanRx () 
	{
		Object oo = get_Value(COLUMNNAME_CanRx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
		set_ValueNoCheck (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
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
		set_ValueNoCheck (COLUMNNAME_City, City);
	}

	/** Get City.
		@return Identifies a City
	  */
	public String getCity () 
	{
		return (String)get_Value(COLUMNNAME_City);
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
		set_ValueNoCheck (COLUMNNAME_C_Region_ID, Integer.valueOf(C_Region_ID));
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

	/** Set Cross Street.
		@param CrossStreet Cross Street	  */
	public void setCrossStreet (String CrossStreet)
	{
		set_Value (COLUMNNAME_CrossStreet, CrossStreet);
	}

	/** Get Cross Street.
		@return Cross Street	  */
	public String getCrossStreet () 
	{
		return (String)get_Value(COLUMNNAME_CrossStreet);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_ValueNoCheck (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_ValueNoCheck (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Pharmacy View.
		@param EXME_FarmaciaV_ID Pharmacy View	  */
	public void setEXME_FarmaciaV_ID (int EXME_FarmaciaV_ID)
	{
		if (EXME_FarmaciaV_ID < 1)
			 throw new IllegalArgumentException ("EXME_FarmaciaV_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FarmaciaV_ID, Integer.valueOf(EXME_FarmaciaV_ID));
	}

	/** Get Pharmacy View.
		@return Pharmacy View	  */
	public int getEXME_FarmaciaV_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FarmaciaV_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IsFax.
		@param IsFax IsFax	  */
	public void setIsFax (boolean IsFax)
	{
		set_Value (COLUMNNAME_IsFax, Boolean.valueOf(IsFax));
	}

	/** Get IsFax.
		@return IsFax	  */
	public boolean isFax () 
	{
		Object oo = get_Value(COLUMNNAME_IsFax);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set isLongTerm.
		@param isLongTerm isLongTerm	  */
	public void setisLongTerm (boolean isLongTerm)
	{
		set_Value (COLUMNNAME_isLongTerm, Boolean.valueOf(isLongTerm));
	}

	/** Get isLongTerm.
		@return isLongTerm	  */
	public boolean isLongTerm () 
	{
		Object oo = get_Value(COLUMNNAME_isLongTerm);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Mail Order.
		@param IsMailOrder Is Mail Order	  */
	public void setIsMailOrder (boolean IsMailOrder)
	{
		set_Value (COLUMNNAME_IsMailOrder, Boolean.valueOf(IsMailOrder));
	}

	/** Get Is Mail Order.
		@return Is Mail Order	  */
	public boolean isMailOrder () 
	{
		Object oo = get_Value(COLUMNNAME_IsMailOrder);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Retail.
		@param IsRetail Is Retail	  */
	public void setIsRetail (boolean IsRetail)
	{
		set_Value (COLUMNNAME_IsRetail, Boolean.valueOf(IsRetail));
	}

	/** Get Is Retail.
		@return Is Retail	  */
	public boolean isRetail () 
	{
		Object oo = get_Value(COLUMNNAME_IsRetail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsSpecialty.
		@param IsSpecialty IsSpecialty	  */
	public void setIsSpecialty (boolean IsSpecialty)
	{
		set_Value (COLUMNNAME_IsSpecialty, Boolean.valueOf(IsSpecialty));
	}

	/** Get IsSpecialty.
		@return IsSpecialty	  */
	public boolean isSpecialty () 
	{
		Object oo = get_Value(COLUMNNAME_IsSpecialty);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is24Hour.
		@param Is24Hour Is24Hour	  */
	public void setIs24Hour (boolean Is24Hour)
	{
		set_Value (COLUMNNAME_Is24Hour, Boolean.valueOf(Is24Hour));
	}

	/** Get Is24Hour.
		@return Is24Hour	  */
	public boolean is24Hour () 
	{
		Object oo = get_Value(COLUMNNAME_Is24Hour);
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
		set_ValueNoCheck (COLUMNNAME_Name, Name);
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

	/** Set NewRx Capabale.
		@param NewRx 
		NewRx Capabale
	  */
	public void setNewRx (boolean NewRx)
	{
		set_Value (COLUMNNAME_NewRx, Boolean.valueOf(NewRx));
	}

	/** Get NewRx Capabale.
		@return NewRx Capabale
	  */
	public boolean isNewRx () 
	{
		Object oo = get_Value(COLUMNNAME_NewRx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set External Num.
		@param NumExt 
		External Number
	  */
	public void setNumExt (String NumExt)
	{
		set_ValueNoCheck (COLUMNNAME_NumExt, NumExt);
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
		set_ValueNoCheck (COLUMNNAME_NumIn, NumIn);
	}

	/** Get Internal Num.
		@return Internal Number
	  */
	public String getNumIn () 
	{
		return (String)get_Value(COLUMNNAME_NumIn);
	}

	/** Set Main Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (BigDecimal Phone)
	{
		set_ValueNoCheck (COLUMNNAME_Phone, Phone);
	}

	/** Get Main Phone.
		@return Identifies a telephone number
	  */
	public BigDecimal getPhone () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Phone);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Refill Request capable.
		@param RefReq 
		Refill Request capable
	  */
	public void setRefReq (boolean RefReq)
	{
		set_Value (COLUMNNAME_RefReq, Boolean.valueOf(RefReq));
	}

	/** Get Refill Request capable.
		@return Refill Request capable
	  */
	public boolean isRefReq () 
	{
		Object oo = get_Value(COLUMNNAME_RefReq);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rx Change Response capable.
		@param RxChange 
		Rx Change Response capable
	  */
	public void setRxChange (boolean RxChange)
	{
		set_Value (COLUMNNAME_RxChange, Boolean.valueOf(RxChange));
	}

	/** Get Rx Change Response capable.
		@return Rx Change Response capable
	  */
	public boolean isRxChange () 
	{
		Object oo = get_Value(COLUMNNAME_RxChange);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}