/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Farmacia
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Farmacia 
{

    /** TableName=EXME_Farmacia */
    public static final String Table_Name = "EXME_Farmacia";

    /** AD_Table_ID=1200853 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

    /** Load Meta Data */

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

    /** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";

	/** Set Address 2.
	  * Address line 2 for this location
	  */
	public void setAddress2 (String Address2);

	/** Get Address 2.
	  * Address line 2 for this location
	  */
	public String getAddress2();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name CanRx */
    public static final String COLUMNNAME_CanRx = "CanRx";

	/** Set Cancel Rx capable.
	  * Cancel Rx capable
	  */
	public void setCanRx (boolean CanRx);

	/** Get Cancel Rx capable.
	  * Cancel Rx capable
	  */
	public boolean isCanRx();

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address .
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address .
	  * Location or Address
	  */
	public int getC_Location_ID();

    /** Column name CrossStreet */
    public static final String COLUMNNAME_CrossStreet = "CrossStreet";

	/** Set Cross Street	  */
	public void setCrossStreet (String CrossStreet);

	/** Get Cross Street	  */
	public String getCrossStreet();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name EPrescribing */
    public static final String COLUMNNAME_EPrescribing = "EPrescribing";

	/** Set E-Prescribing	  */
	public void setEPrescribing (boolean EPrescribing);

	/** Get E-Prescribing	  */
	public boolean isEPrescribing();

    /** Column name EXME_Farmacia_ID */
    public static final String COLUMNNAME_EXME_Farmacia_ID = "EXME_Farmacia_ID";

	/** Set Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID);

	/** Get Pharmacy	  */
	public int getEXME_Farmacia_ID();

    /** Column name FaxNumber */
    public static final String COLUMNNAME_FaxNumber = "FaxNumber";

	/** Set Fax Number.
	  * Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public void setFaxNumber (String FaxNumber);

	/** Get Fax Number.
	  * Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public String getFaxNumber();

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

    /** Column name IsFax */
    public static final String COLUMNNAME_IsFax = "IsFax";

	/** Set Fax	  */
	public void setIsFax (boolean IsFax);

	/** Get Fax	  */
	public boolean isFax();

    /** Column name isLongTerm */
    public static final String COLUMNNAME_isLongTerm = "isLongTerm";

	/** Set isLongTerm	  */
	public void setisLongTerm (boolean isLongTerm);

	/** Get isLongTerm	  */
	public boolean isLongTerm();

    /** Column name IsMailOrder */
    public static final String COLUMNNAME_IsMailOrder = "IsMailOrder";

	/** Set Is Mail Order	  */
	public void setIsMailOrder (boolean IsMailOrder);

	/** Get Is Mail Order	  */
	public boolean isMailOrder();

    /** Column name IsRetail */
    public static final String COLUMNNAME_IsRetail = "IsRetail";

	/** Set Is Retail	  */
	public void setIsRetail (boolean IsRetail);

	/** Get Is Retail	  */
	public boolean isRetail();

    /** Column name IsSpecialty */
    public static final String COLUMNNAME_IsSpecialty = "IsSpecialty";

	/** Set IsSpecialty	  */
	public void setIsSpecialty (boolean IsSpecialty);

	/** Get IsSpecialty	  */
	public boolean isSpecialty();

    /** Column name Is24Hour */
    public static final String COLUMNNAME_Is24Hour = "Is24Hour";

	/** Set 24 Hour	  */
	public void setIs24Hour (boolean Is24Hour);

	/** Get 24 Hour	  */
	public boolean is24Hour();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NewRx */
    public static final String COLUMNNAME_NewRx = "NewRx";

	/** Set NewRx Capabale.
	  * NewRx Capabale
	  */
	public void setNewRx (boolean NewRx);

	/** Get NewRx Capabale.
	  * NewRx Capabale
	  */
	public boolean isNewRx();

    /** Column name NPI */
    public static final String COLUMNNAME_NPI = "NPI";

	/** Set NPI	  */
	public void setNPI (String NPI);

	/** Get NPI	  */
	public String getNPI();

    /** Column name OldServiceLevel */
    public static final String COLUMNNAME_OldServiceLevel = "OldServiceLevel";

	/** Set Old Service Level	  */
	public void setOldServiceLevel (String OldServiceLevel);

	/** Get Old Service Level	  */
	public String getOldServiceLevel();

    /** Column name PartnerAccount */
    public static final String COLUMNNAME_PartnerAccount = "PartnerAccount";

	/** Set Partner Account	  */
	public void setPartnerAccount (String PartnerAccount);

	/** Get Partner Account	  */
	public String getPartnerAccount();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Main Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Main Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name PhoneExt */
    public static final String COLUMNNAME_PhoneExt = "PhoneExt";

	/** Set Telephone extension.
	  * Telephone extension
	  */
	public void setPhoneExt (String PhoneExt);

	/** Get Telephone extension.
	  * Telephone extension
	  */
	public String getPhoneExt();

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

    /** Column name RefReq */
    public static final String COLUMNNAME_RefReq = "RefReq";

	/** Set Refill Request capable.
	  * Refill Request capable
	  */
	public void setRefReq (boolean RefReq);

	/** Get Refill Request capable.
	  * Refill Request capable
	  */
	public boolean isRefReq();

    /** Column name RxChange */
    public static final String COLUMNNAME_RxChange = "RxChange";

	/** Set Rx Change Response capable.
	  * Rx Change Response capable
	  */
	public void setRxChange (boolean RxChange);

	/** Get Rx Change Response capable.
	  * Rx Change Response capable
	  */
	public boolean isRxChange();

    /** Column name ServiceLevel */
    public static final String COLUMNNAME_ServiceLevel = "ServiceLevel";

	/** Set Service Level	  */
	public void setServiceLevel (String ServiceLevel);

	/** Get Service Level	  */
	public String getServiceLevel();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name State */
    public static final String COLUMNNAME_State = "State";

	/** Set State	  */
	public void setState (String State);

	/** Get State	  */
	public String getState();

    /** Column name StoreNumber */
    public static final String COLUMNNAME_StoreNumber = "StoreNumber";

	/** Set Store Number.
	  * Store Number
	  */
	public void setStoreNumber (String StoreNumber);

	/** Get Store Number.
	  * Store Number
	  */
	public String getStoreNumber();

    /** Column name TextServiceLevel */
    public static final String COLUMNNAME_TextServiceLevel = "TextServiceLevel";

	/** Set Text Service Level	  */
	public void setTextServiceLevel (String TextServiceLevel);

	/** Get Text Service Level	  */
	public String getTextServiceLevel();

    /** Column name Tipo */
    public static final String COLUMNNAME_Tipo = "Tipo";

	/** Set Type	  */
	public void setTipo (String Tipo);

	/** Get Type	  */
	public String getTipo();

    /** Column name TWENTYFOURHRFLAG */
    public static final String COLUMNNAME_TWENTYFOURHRFLAG = "TWENTYFOURHRFLAG";

	/** Set TWENTYFOURHRFLAG	  */
	public void setTWENTYFOURHRFLAG (String TWENTYFOURHRFLAG);

	/** Get TWENTYFOURHRFLAG	  */
	public String getTWENTYFOURHRFLAG();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
