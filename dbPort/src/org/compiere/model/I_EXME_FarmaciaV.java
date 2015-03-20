/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FarmaciaV
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_FarmaciaV 
{

    /** TableName=EXME_FarmaciaV */
    public static final String Table_Name = "EXME_FarmaciaV";

    /** AD_Table_ID=1201163 */
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

    /** Column name Address3 */
    public static final String COLUMNNAME_Address3 = "Address3";

	/** Set Address 3.
	  * Address Line 3 for the location
	  */
	public void setAddress3 (String Address3);

	/** Get Address 3.
	  * Address Line 3 for the location
	  */
	public String getAddress3();

    /** Column name Address4 */
    public static final String COLUMNNAME_Address4 = "Address4";

	/** Set Address 4.
	  * Address Line 4 for the location
	  */
	public void setAddress4 (String Address4);

	/** Get Address 4.
	  * Address Line 4 for the location
	  */
	public String getAddress4();

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

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public I_C_Country getC_Country() throws RuntimeException;

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

    /** Column name C_Region_ID */
    public static final String COLUMNNAME_C_Region_ID = "C_Region_ID";

	/** Set Region.
	  * Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID);

	/** Get Region.
	  * Identifies a geographical Region
	  */
	public int getC_Region_ID();

	public I_C_Region getC_Region() throws RuntimeException;

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

    /** Column name EXME_FarmaciaV_ID */
    public static final String COLUMNNAME_EXME_FarmaciaV_ID = "EXME_FarmaciaV_ID";

	/** Set Pharmacy View	  */
	public void setEXME_FarmaciaV_ID (int EXME_FarmaciaV_ID);

	/** Get Pharmacy View	  */
	public int getEXME_FarmaciaV_ID();

    /** Column name IsFax */
    public static final String COLUMNNAME_IsFax = "IsFax";

	/** Set IsFax	  */
	public void setIsFax (boolean IsFax);

	/** Get IsFax	  */
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

	/** Set Is24Hour	  */
	public void setIs24Hour (boolean Is24Hour);

	/** Get Is24Hour	  */
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

    /** Column name NumExt */
    public static final String COLUMNNAME_NumExt = "NumExt";

	/** Set External Num.
	  * External Number
	  */
	public void setNumExt (String NumExt);

	/** Get External Num.
	  * External Number
	  */
	public String getNumExt();

    /** Column name NumIn */
    public static final String COLUMNNAME_NumIn = "NumIn";

	/** Set Internal Num.
	  * Internal Number
	  */
	public void setNumIn (String NumIn);

	/** Get Internal Num.
	  * Internal Number
	  */
	public String getNumIn();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Main Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (BigDecimal Phone);

	/** Get Main Phone.
	  * Identifies a telephone number
	  */
	public BigDecimal getPhone();

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
