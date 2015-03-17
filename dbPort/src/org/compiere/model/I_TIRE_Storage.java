/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TIRE_Storage
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_TIRE_Storage 
{

    /** TableName=TIRE_Storage */
    public static final String Table_Name = "TIRE_Storage";

    /** AD_Table_ID=384 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name DateReceived */
    public static final String COLUMNNAME_DateReceived = "DateReceived";

	/** Set Date received.
	  * Date a product was received
	  */
	public void setDateReceived (Timestamp DateReceived);

	/** Get Date received.
	  * Date a product was received
	  */
	public Timestamp getDateReceived();

    /** Column name DateReturned */
    public static final String COLUMNNAME_DateReturned = "DateReturned";

	/** Set Date returned.
	  * Date a product was returned
	  */
	public void setDateReturned (Timestamp DateReturned);

	/** Get Date returned.
	  * Date a product was returned
	  */
	public Timestamp getDateReturned();

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

    /** Column name IsReturned */
    public static final String COLUMNNAME_IsReturned = "IsReturned";

	/** Set Returned	  */
	public void setIsReturned (boolean IsReturned);

	/** Get Returned	  */
	public boolean isReturned();

    /** Column name IsStored */
    public static final String COLUMNNAME_IsStored = "IsStored";

	/** Set Moved to storage	  */
	public void setIsStored (boolean IsStored);

	/** Get Moved to storage	  */
	public boolean isStored();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

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

    /** Column name Registration */
    public static final String COLUMNNAME_Registration = "Registration";

	/** Set Registration.
	  * Vehicle registration
	  */
	public void setRegistration (String Registration);

	/** Get Registration.
	  * Vehicle registration
	  */
	public String getRegistration();

    /** Column name Remark */
    public static final String COLUMNNAME_Remark = "Remark";

	/** Set Remark	  */
	public void setRemark (String Remark);

	/** Get Remark	  */
	public String getRemark();

    /** Column name Rim */
    public static final String COLUMNNAME_Rim = "Rim";

	/** Set Rim.
	  * Stored rim
	  */
	public void setRim (String Rim);

	/** Get Rim.
	  * Stored rim
	  */
	public String getRim();

    /** Column name Rim_B */
    public static final String COLUMNNAME_Rim_B = "Rim_B";

	/** Set Rim Back	  */
	public void setRim_B (String Rim_B);

	/** Get Rim Back	  */
	public String getRim_B();

    /** Column name TireQuality */
    public static final String COLUMNNAME_TireQuality = "TireQuality";

	/** Set Tire Quality	  */
	public void setTireQuality (String TireQuality);

	/** Get Tire Quality	  */
	public String getTireQuality();

    /** Column name TireQuality_B */
    public static final String COLUMNNAME_TireQuality_B = "TireQuality_B";

	/** Set Tire Quality Back	  */
	public void setTireQuality_B (String TireQuality_B);

	/** Get Tire Quality Back	  */
	public String getTireQuality_B();

    /** Column name TireSize */
    public static final String COLUMNNAME_TireSize = "TireSize";

	/** Set Tire size (L/R)	  */
	public void setTireSize (String TireSize);

	/** Get Tire size (L/R)	  */
	public String getTireSize();

    /** Column name TireSize_B */
    public static final String COLUMNNAME_TireSize_B = "TireSize_B";

	/** Set Tire size Back	  */
	public void setTireSize_B (String TireSize_B);

	/** Get Tire size Back	  */
	public String getTireSize_B();

    /** Column name Tire_Storage_ID */
    public static final String COLUMNNAME_Tire_Storage_ID = "Tire_Storage_ID";

	/** Set Tire Storage	  */
	public void setTire_Storage_ID (int Tire_Storage_ID);

	/** Get Tire Storage	  */
	public int getTire_Storage_ID();

    /** Column name TireType */
    public static final String COLUMNNAME_TireType = "TireType";

	/** Set Tire type	  */
	public void setTireType (String TireType);

	/** Get Tire type	  */
	public String getTireType();

    /** Column name TireType_B */
    public static final String COLUMNNAME_TireType_B = "TireType_B";

	/** Set Tire type Back	  */
	public void setTireType_B (String TireType_B);

	/** Get Tire type Back	  */
	public String getTireType_B();

    /** Column name Vehicle */
    public static final String COLUMNNAME_Vehicle = "Vehicle";

	/** Set Vehicle	  */
	public void setVehicle (String Vehicle);

	/** Get Vehicle	  */
	public String getVehicle();
}
