/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ProductClassFact
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProductClassFact extends PO implements I_EXME_ProductClassFact, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductClassFact (Properties ctx, int EXME_ProductClassFact_ID, String trxName)
    {
      super (ctx, EXME_ProductClassFact_ID, trxName);
      /** if (EXME_ProductClassFact_ID == 0)
        {
			setAD_Ref_List_ID (0);
			setEXME_ProductClassFact_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductClassFact (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductClassFact[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Reference List.
		@param AD_Ref_List_ID 
		Reference List based on Table
	  */
	public void setAD_Ref_List_ID (int AD_Ref_List_ID)
	{
		if (AD_Ref_List_ID < 1)
			 throw new IllegalArgumentException ("AD_Ref_List_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Ref_List_ID, Integer.valueOf(AD_Ref_List_ID));
	}

	/** Get Reference List.
		@return Reference List based on Table
	  */
	public int getAD_Ref_List_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Ref_List_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product class.
		@param EXME_ProductClassFact_ID 
		Product class
	  */
	public void setEXME_ProductClassFact_ID (int EXME_ProductClassFact_ID)
	{
		if (EXME_ProductClassFact_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductClassFact_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProductClassFact_ID, Integer.valueOf(EXME_ProductClassFact_ID));
	}

	/** Get Product class.
		@return Product class
	  */
	public int getEXME_ProductClassFact_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductClassFact_ID);
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

	/** Value AD_Reference_ID=1200509 */
	public static final int VALUE_AD_Reference_ID=1200509;
	/** Laboratory = LA */
	public static final String VALUE_Laboratory = "LA";
	/** X Ray = XR */
	public static final String VALUE_XRay = "XR";
	/** Drug = DG */
	public static final String VALUE_Drug = "DG";
	/** Procedures = PR */
	public static final String VALUE_Procedures = "PR";
	/** Immunization = IM */
	public static final String VALUE_Immunization = "IM";
	/** Cultures = CL */
	public static final String VALUE_Cultures = "CL";
	/** Blood = BL */
	public static final String VALUE_Blood = "BL";
	/** Surgeries = SG */
	public static final String VALUE_Surgeries = "SG";
	/** Anesthesic = AN */
	public static final String VALUE_Anesthesic = "AN";
	/** Medical Supplies = MS */
	public static final String VALUE_MedicalSupplies = "MS";
	/** Treatment = TR */
	public static final String VALUE_Treatment = "TR";
	/** Office Visit = OV */
	public static final String VALUE_OfficeVisit = "OV";
	/** Other Service = OS */
	public static final String VALUE_OtherService = "OS";
	/** Ambulance = AB */
	public static final String VALUE_Ambulance = "AB";
	/** Home Healt = HH */
	public static final String VALUE_HomeHealt = "HH";
	/** Hospice = HP */
	public static final String VALUE_Hospice = "HP";
	/** FQHC/RHC = FR */
	public static final String VALUE_FQHCRHC = "FR";
	/** Physician Services = PS */
	public static final String VALUE_PhysicianServices = "PS";
	/** Rooms = RM */
	public static final String VALUE_Rooms = "RM";
	/** Others = TH */
	public static final String VALUE_Others = "TH";
	/** MR = MR */
	public static final String VALUE_MR = "MR";
	/** CT = CT */
	public static final String VALUE_CT = "CT";
	/** NM = NM */
	public static final String VALUE_NM = "NM";
	/** PT = PT */
	public static final String VALUE_PT = "PT";
	/** INSTRUMENTAL = IN */
	public static final String VALUE_INSTRUMENTAL = "IN";
	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null) throw new IllegalArgumentException ("Value is mandatory");
		if (Value.equals("LA") || Value.equals("XR") || Value.equals("DG") || Value.equals("PR") || Value.equals("IM") || Value.equals("CL") || Value.equals("BL") || Value.equals("SG") || Value.equals("AN") || Value.equals("MS") || Value.equals("TR") || Value.equals("OV") || Value.equals("OS") || Value.equals("AB") || Value.equals("HH") || Value.equals("HP") || Value.equals("FR") || Value.equals("PS") || Value.equals("RM") || Value.equals("TH") || Value.equals("MR") || Value.equals("CT") || Value.equals("NM") || Value.equals("PT") || Value.equals("IN")); else throw new IllegalArgumentException ("Value Invalid value - " + Value + " - Reference_ID=1200509 - LA - XR - DG - PR - IM - CL - BL - SG - AN - MS - TR - OV - OS - AB - HH - HP - FR - PS - RM - TH - MR - CT - NM - PT - IN");		set_ValueNoCheck (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}