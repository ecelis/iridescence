/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Farmacia
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_I_EXME_Farmacia 
{

    /** TableName=I_EXME_Farmacia */
    public static final String Table_Name = "I_EXME_Farmacia";

    /** AD_Table_ID=1201220 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

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

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

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

    /** Column name EXME_Farmacia_ID */
    public static final String COLUMNNAME_EXME_Farmacia_ID = "EXME_Farmacia_ID";

	/** Set Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID);

	/** Get Pharmacy	  */
	public int getEXME_Farmacia_ID();

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException;

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

    /** Column name I_EXME_Farmacia_ID */
    public static final String COLUMNNAME_I_EXME_Farmacia_ID = "I_EXME_Farmacia_ID";

	/** Set Import Pharmacy	  */
	public void setI_EXME_Farmacia_ID (int I_EXME_Farmacia_ID);

	/** Get Import Pharmacy	  */
	public int getI_EXME_Farmacia_ID();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name NPI */
    public static final String COLUMNNAME_NPI = "NPI";

	/** Set NPI	  */
	public void setNPI (int NPI);

	/** Get NPI	  */
	public int getNPI();

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ServiceLevel */
    public static final String COLUMNNAME_ServiceLevel = "ServiceLevel";

	/** Set Service Level	  */
	public void setServiceLevel (String ServiceLevel);

	/** Get Service Level	  */
	public String getServiceLevel();

    /** Column name State */
    public static final String COLUMNNAME_State = "State";

	/** Set State	  */
	public void setState (String State);

	/** Get State	  */
	public String getState();

    /** Column name TextServiceLevel */
    public static final String COLUMNNAME_TextServiceLevel = "TextServiceLevel";

	/** Set Text Service Level	  */
	public void setTextServiceLevel (String TextServiceLevel);

	/** Get Text Service Level	  */
	public String getTextServiceLevel();

    /** Column name TwentyFourHourFlag */
    public static final String COLUMNNAME_TwentyFourHourFlag = "TwentyFourHourFlag";

	/** Set Twenty Four Hour Flag	  */
	public void setTwentyFourHourFlag (String TwentyFourHourFlag);

	/** Get Twenty Four Hour Flag	  */
	public String getTwentyFourHourFlag();

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
