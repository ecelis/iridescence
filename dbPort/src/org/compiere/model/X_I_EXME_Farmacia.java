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

/** Generated Model for I_EXME_Farmacia
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha) - $Id$ */
public class X_I_EXME_Farmacia extends PO implements I_I_EXME_Farmacia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Farmacia (Properties ctx, int I_EXME_Farmacia_ID, String trxName)
    {
      super (ctx, I_EXME_Farmacia_ID, trxName);
      /** if (I_EXME_Farmacia_ID == 0)
        {
			setI_EXME_Farmacia_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Farmacia (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Farmacia[")
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
		set_Value (COLUMNNAME_Description, Description);
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
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Farmacia.Table_Name);
        I_EXME_Farmacia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Farmacia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Farmacia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pharmacy.
		@param EXME_Farmacia_ID Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID)
	{
		if (EXME_Farmacia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, Integer.valueOf(EXME_Farmacia_ID));
	}

	/** Get Pharmacy.
		@return Pharmacy	  */
	public int getEXME_Farmacia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Farmacia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fax Number.
		@param FaxNumber 
		Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public void setFaxNumber (String FaxNumber)
	{
		set_Value (COLUMNNAME_FaxNumber, FaxNumber);
	}

	/** Get Fax Number.
		@return Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public String getFaxNumber () 
	{
		return (String)get_Value(COLUMNNAME_FaxNumber);
	}

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Initial Date.
		@param FechaIni 
		Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni)
	{
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
	}

	/** Set Import Pharmacy.
		@param I_EXME_Farmacia_ID Import Pharmacy	  */
	public void setI_EXME_Farmacia_ID (int I_EXME_Farmacia_ID)
	{
		if (I_EXME_Farmacia_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Farmacia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Farmacia_ID, Integer.valueOf(I_EXME_Farmacia_ID));
	}

	/** Get Import Pharmacy.
		@return Import Pharmacy	  */
	public int getI_EXME_Farmacia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Farmacia_ID);
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

	/** Set NPI.
		@param NPI NPI	  */
	public void setNPI (int NPI)
	{
		set_Value (COLUMNNAME_NPI, Integer.valueOf(NPI));
	}

	/** Get NPI.
		@return NPI	  */
	public int getNPI () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NPI);
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

	/** Set Old Service Level.
		@param OldServiceLevel Old Service Level	  */
	public void setOldServiceLevel (String OldServiceLevel)
	{
		set_Value (COLUMNNAME_OldServiceLevel, OldServiceLevel);
	}

	/** Get Old Service Level.
		@return Old Service Level	  */
	public String getOldServiceLevel () 
	{
		return (String)get_Value(COLUMNNAME_OldServiceLevel);
	}

	/** Set Partner Account.
		@param PartnerAccount Partner Account	  */
	public void setPartnerAccount (String PartnerAccount)
	{
		set_Value (COLUMNNAME_PartnerAccount, PartnerAccount);
	}

	/** Get Partner Account.
		@return Partner Account	  */
	public String getPartnerAccount () 
	{
		return (String)get_Value(COLUMNNAME_PartnerAccount);
	}

	/** Set Main Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Main Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
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

	/** Set Service Level.
		@param ServiceLevel Service Level	  */
	public void setServiceLevel (String ServiceLevel)
	{
		set_Value (COLUMNNAME_ServiceLevel, ServiceLevel);
	}

	/** Get Service Level.
		@return Service Level	  */
	public String getServiceLevel () 
	{
		return (String)get_Value(COLUMNNAME_ServiceLevel);
	}

	/** Set State.
		@param State State	  */
	public void setState (String State)
	{
		set_Value (COLUMNNAME_State, State);
	}

	/** Get State.
		@return State	  */
	public String getState () 
	{
		return (String)get_Value(COLUMNNAME_State);
	}

	/** Set Text Service Level.
		@param TextServiceLevel Text Service Level	  */
	public void setTextServiceLevel (String TextServiceLevel)
	{
		set_Value (COLUMNNAME_TextServiceLevel, TextServiceLevel);
	}

	/** Get Text Service Level.
		@return Text Service Level	  */
	public String getTextServiceLevel () 
	{
		return (String)get_Value(COLUMNNAME_TextServiceLevel);
	}

	/** Set Twenty Four Hour Flag.
		@param TwentyFourHourFlag Twenty Four Hour Flag	  */
	public void setTwentyFourHourFlag (String TwentyFourHourFlag)
	{
		set_Value (COLUMNNAME_TwentyFourHourFlag, TwentyFourHourFlag);
	}

	/** Get Twenty Four Hour Flag.
		@return Twenty Four Hour Flag	  */
	public String getTwentyFourHourFlag () 
	{
		return (String)get_Value(COLUMNNAME_TwentyFourHourFlag);
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