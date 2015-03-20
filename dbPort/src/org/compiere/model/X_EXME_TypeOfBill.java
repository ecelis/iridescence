/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_TypeOfBill
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_TypeOfBill extends PO implements I_EXME_TypeOfBill, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TypeOfBill (Properties ctx, int EXME_TypeOfBill_ID, String trxName)
    {
      super (ctx, EXME_TypeOfBill_ID, trxName);
      /** if (EXME_TypeOfBill_ID == 0)
        {
			setBillType (null);
			setCode (0);
			setEXME_TypeOfBill_ID (0);
			setName (null);
			setSequence (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TypeOfBill (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TypeOfBill[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** BillType AD_Reference_ID=1200500 */
	public static final int BILLTYPE_AD_Reference_ID=1200500;
	/** All = A */
	public static final String BILLTYPE_All = "A";
	/** Hospitals Only = H */
	public static final String BILLTYPE_HospitalsOnly = "H";
	/** Clinics Only = C */
	public static final String BILLTYPE_ClinicsOnly = "C";
	/** Special Facility Only = SF */
	public static final String BILLTYPE_SpecialFacilityOnly = "SF";
	/** Frequency = F */
	public static final String BILLTYPE_Frequency = "F";
	/** Set Type of Bill.
		@param BillType 
		Type of Bill
	  */
	public void setBillType (String BillType)
	{
		if (BillType == null) throw new IllegalArgumentException ("BillType is mandatory");
		if (BillType.equals("A") || BillType.equals("H") || BillType.equals("C") || BillType.equals("SF") || BillType.equals("F")); else throw new IllegalArgumentException ("BillType Invalid value - " + BillType + " - Reference_ID=1200500 - A - H - C - SF - F");		set_Value (COLUMNNAME_BillType, BillType);
	}

	/** Get Type of Bill.
		@return Type of Bill
	  */
	public String getBillType () 
	{
		return (String)get_Value(COLUMNNAME_BillType);
	}

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (int Code)
	{
		set_Value (COLUMNNAME_Code, Integer.valueOf(Code));
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public int getCode () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Code);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Type Of Bill.
		@param EXME_TypeOfBill_ID 
		Type Of Bill
	  */
	public void setEXME_TypeOfBill_ID (int EXME_TypeOfBill_ID)
	{
		if (EXME_TypeOfBill_ID < 1)
			 throw new IllegalArgumentException ("EXME_TypeOfBill_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TypeOfBill_ID, Integer.valueOf(EXME_TypeOfBill_ID));
	}

	/** Get Type Of Bill.
		@return Type Of Bill
	  */
	public int getEXME_TypeOfBill_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TypeOfBill_ID);
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

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (int Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Integer.valueOf(Sequence));
	}

	/** Get Sequence.
		@return Sequence	  */
	public int getSequence () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sequence);
		if (ii == null)
			 return 0;
		return ii.intValue();
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