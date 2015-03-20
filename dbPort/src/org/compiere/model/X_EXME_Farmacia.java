/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Farmacia
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Farmacia extends PO implements I_EXME_Farmacia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Farmacia (Properties ctx, int EXME_Farmacia_ID, String trxName)
    {
      super (ctx, EXME_Farmacia_ID, trxName);
      /** if (EXME_Farmacia_ID == 0)
        {
			setEPrescribing (false);
// 'N'
			setEXME_Farmacia_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Farmacia (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Farmacia[")
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

	/** Set Address .
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

	/** Get Address .
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set E-Prescribing.
		@param EPrescribing E-Prescribing	  */
	public void setEPrescribing (boolean EPrescribing)
	{
		set_Value (COLUMNNAME_EPrescribing, Boolean.valueOf(EPrescribing));
	}

	/** Get E-Prescribing.
		@return E-Prescribing	  */
	public boolean isEPrescribing () 
	{
		Object oo = get_Value(COLUMNNAME_EPrescribing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pharmacy.
		@param EXME_Farmacia_ID Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID)
	{
		if (EXME_Farmacia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Farmacia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Farmacia_ID, Integer.valueOf(EXME_Farmacia_ID));
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

	/** Set Fax.
		@param IsFax Fax	  */
	public void setIsFax (boolean IsFax)
	{
		set_Value (COLUMNNAME_IsFax, Boolean.valueOf(IsFax));
	}

	/** Get Fax.
		@return Fax	  */
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

	/** Set 24 Hour.
		@param Is24Hour 24 Hour	  */
	public void setIs24Hour (boolean Is24Hour)
	{
		set_Value (COLUMNNAME_Is24Hour, Boolean.valueOf(Is24Hour));
	}

	/** Get 24 Hour.
		@return 24 Hour	  */
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

	/** Set NPI.
		@param NPI NPI	  */
	public void setNPI (String NPI)
	{
		set_Value (COLUMNNAME_NPI, NPI);
	}

	/** Get NPI.
		@return NPI	  */
	public String getNPI () 
	{
		return (String)get_Value(COLUMNNAME_NPI);
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

	/** Set Telephone extension.
		@param PhoneExt 
		Telephone extension
	  */
	public void setPhoneExt (String PhoneExt)
	{
		set_Value (COLUMNNAME_PhoneExt, PhoneExt);
	}

	/** Get Telephone extension.
		@return Telephone extension
	  */
	public String getPhoneExt () 
	{
		return (String)get_Value(COLUMNNAME_PhoneExt);
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

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
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

	/** Set Store Number.
		@param StoreNumber 
		Store Number
	  */
	public void setStoreNumber (String StoreNumber)
	{
		set_Value (COLUMNNAME_StoreNumber, StoreNumber);
	}

	/** Get Store Number.
		@return Store Number
	  */
	public String getStoreNumber () 
	{
		return (String)get_Value(COLUMNNAME_StoreNumber);
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

	/** Tipo AD_Reference_ID=1200602 */
	public static final int TIPO_AD_Reference_ID=1200602;
	/** MailOrder = M */
	public static final String TIPO_MailOrder = "M";
	/** Retail = R */
	public static final String TIPO_Retail = "R";
	/** Set Type.
		@param Tipo Type	  */
	public void setTipo (String Tipo)
	{

		if (Tipo == null || Tipo.equals("M") || Tipo.equals("R")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200602 - M - R");		set_Value (COLUMNNAME_Tipo, Tipo);
	}

	/** Get Type.
		@return Type	  */
	public String getTipo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo);
	}

	/** Set TWENTYFOURHRFLAG.
		@param TWENTYFOURHRFLAG TWENTYFOURHRFLAG	  */
	public void setTWENTYFOURHRFLAG (String TWENTYFOURHRFLAG)
	{
		set_Value (COLUMNNAME_TWENTYFOURHRFLAG, TWENTYFOURHRFLAG);
	}

	/** Get TWENTYFOURHRFLAG.
		@return TWENTYFOURHRFLAG	  */
	public String getTWENTYFOURHRFLAG () 
	{
		return (String)get_Value(COLUMNNAME_TWENTYFOURHRFLAG);
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