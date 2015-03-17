/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for TIRE_Storage
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_TIRE_Storage extends PO implements I_TIRE_Storage, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_TIRE_Storage (Properties ctx, int TIRE_Storage_ID, String trxName)
    {
      super (ctx, TIRE_Storage_ID, trxName);
      /** if (TIRE_Storage_ID == 0)
        {
			setDateReceived (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setIsReturned (false);
			setIsStored (false);
			setName (null);
			setTire_Storage_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TIRE_Storage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TIRE_Storage[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date received.
		@param DateReceived 
		Date a product was received
	  */
	public void setDateReceived (Timestamp DateReceived)
	{
		if (DateReceived == null)
			throw new IllegalArgumentException ("DateReceived is mandatory.");
		set_Value (COLUMNNAME_DateReceived, DateReceived);
	}

	/** Get Date received.
		@return Date a product was received
	  */
	public Timestamp getDateReceived () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReceived);
	}

	/** Set Date returned.
		@param DateReturned 
		Date a product was returned
	  */
	public void setDateReturned (Timestamp DateReturned)
	{
		set_Value (COLUMNNAME_DateReturned, DateReturned);
	}

	/** Get Date returned.
		@return Date a product was returned
	  */
	public Timestamp getDateReturned () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReturned);
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

	/** Set Returned.
		@param IsReturned Returned	  */
	public void setIsReturned (boolean IsReturned)
	{
		set_Value (COLUMNNAME_IsReturned, Boolean.valueOf(IsReturned));
	}

	/** Get Returned.
		@return Returned	  */
	public boolean isReturned () 
	{
		Object oo = get_Value(COLUMNNAME_IsReturned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Moved to storage.
		@param IsStored Moved to storage	  */
	public void setIsStored (boolean IsStored)
	{
		set_Value (COLUMNNAME_IsStored, Boolean.valueOf(IsStored));
	}

	/** Get Moved to storage.
		@return Moved to storage	  */
	public boolean isStored () 
	{
		Object oo = get_Value(COLUMNNAME_IsStored);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
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

	/** Set Registration.
		@param Registration 
		Vehicle registration
	  */
	public void setRegistration (String Registration)
	{
		set_Value (COLUMNNAME_Registration, Registration);
	}

	/** Get Registration.
		@return Vehicle registration
	  */
	public String getRegistration () 
	{
		return (String)get_Value(COLUMNNAME_Registration);
	}

	/** Set Remark.
		@param Remark Remark	  */
	public void setRemark (String Remark)
	{
		set_Value (COLUMNNAME_Remark, Remark);
	}

	/** Get Remark.
		@return Remark	  */
	public String getRemark () 
	{
		return (String)get_Value(COLUMNNAME_Remark);
	}

	/** Set Rim.
		@param Rim 
		Stored rim
	  */
	public void setRim (String Rim)
	{
		set_Value (COLUMNNAME_Rim, Rim);
	}

	/** Get Rim.
		@return Stored rim
	  */
	public String getRim () 
	{
		return (String)get_Value(COLUMNNAME_Rim);
	}

	/** Set Rim Back.
		@param Rim_B Rim Back	  */
	public void setRim_B (String Rim_B)
	{
		set_Value (COLUMNNAME_Rim_B, Rim_B);
	}

	/** Get Rim Back.
		@return Rim Back	  */
	public String getRim_B () 
	{
		return (String)get_Value(COLUMNNAME_Rim_B);
	}

	/** Set Tire Quality.
		@param TireQuality Tire Quality	  */
	public void setTireQuality (String TireQuality)
	{
		set_Value (COLUMNNAME_TireQuality, TireQuality);
	}

	/** Get Tire Quality.
		@return Tire Quality	  */
	public String getTireQuality () 
	{
		return (String)get_Value(COLUMNNAME_TireQuality);
	}

	/** Set Tire Quality Back.
		@param TireQuality_B Tire Quality Back	  */
	public void setTireQuality_B (String TireQuality_B)
	{
		set_Value (COLUMNNAME_TireQuality_B, TireQuality_B);
	}

	/** Get Tire Quality Back.
		@return Tire Quality Back	  */
	public String getTireQuality_B () 
	{
		return (String)get_Value(COLUMNNAME_TireQuality_B);
	}

	/** Set Tire size (L/R).
		@param TireSize Tire size (L/R)	  */
	public void setTireSize (String TireSize)
	{
		set_Value (COLUMNNAME_TireSize, TireSize);
	}

	/** Get Tire size (L/R).
		@return Tire size (L/R)	  */
	public String getTireSize () 
	{
		return (String)get_Value(COLUMNNAME_TireSize);
	}

	/** Set Tire size Back.
		@param TireSize_B Tire size Back	  */
	public void setTireSize_B (String TireSize_B)
	{
		set_Value (COLUMNNAME_TireSize_B, TireSize_B);
	}

	/** Get Tire size Back.
		@return Tire size Back	  */
	public String getTireSize_B () 
	{
		return (String)get_Value(COLUMNNAME_TireSize_B);
	}

	/** Set Tire Storage.
		@param Tire_Storage_ID Tire Storage	  */
	public void setTire_Storage_ID (int Tire_Storage_ID)
	{
		if (Tire_Storage_ID < 1)
			 throw new IllegalArgumentException ("Tire_Storage_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Tire_Storage_ID, Integer.valueOf(Tire_Storage_ID));
	}

	/** Get Tire Storage.
		@return Tire Storage	  */
	public int getTire_Storage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Tire_Storage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tire type.
		@param TireType Tire type	  */
	public void setTireType (String TireType)
	{
		set_Value (COLUMNNAME_TireType, TireType);
	}

	/** Get Tire type.
		@return Tire type	  */
	public String getTireType () 
	{
		return (String)get_Value(COLUMNNAME_TireType);
	}

	/** Set Tire type Back.
		@param TireType_B Tire type Back	  */
	public void setTireType_B (String TireType_B)
	{
		set_Value (COLUMNNAME_TireType_B, TireType_B);
	}

	/** Get Tire type Back.
		@return Tire type Back	  */
	public String getTireType_B () 
	{
		return (String)get_Value(COLUMNNAME_TireType_B);
	}

	/** Set Vehicle.
		@param Vehicle Vehicle	  */
	public void setVehicle (String Vehicle)
	{
		set_Value (COLUMNNAME_Vehicle, Vehicle);
	}

	/** Get Vehicle.
		@return Vehicle	  */
	public String getVehicle () 
	{
		return (String)get_Value(COLUMNNAME_Vehicle);
	}
}