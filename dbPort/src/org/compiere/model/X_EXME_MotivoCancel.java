/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_MotivoCancel
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MotivoCancel extends PO implements I_EXME_MotivoCancel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MotivoCancel (Properties ctx, int EXME_MotivoCancel_ID, String trxName)
    {
      super (ctx, EXME_MotivoCancel_ID, trxName);
      /** if (EXME_MotivoCancel_ID == 0)
        {
			setEXME_MotivoCancel_ID (0);
			setModulo (null);
// OT
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MotivoCancel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MotivoCancel[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Cancel Reason.
		@param EXME_MotivoCancel_ID 
		Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID)
	{
		if (EXME_MotivoCancel_ID < 1)
			 throw new IllegalArgumentException ("EXME_MotivoCancel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MotivoCancel_ID, Integer.valueOf(EXME_MotivoCancel_ID));
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCancel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Modulo AD_Reference_ID=1200162 */
	public static final int MODULO_AD_Reference_ID=1200162;
	/** Others = OT */
	public static final String MODULO_Others = "OT";
	/** Hospitalization = HO */
	public static final String MODULO_Hospitalization = "HO";
	/** Surgery = QU */
	public static final String MODULO_Surgery = "QU";
	/** Outpatient = CE */
	public static final String MODULO_Outpatient = "CE";
	/** Receipts = IN */
	public static final String MODULO_Receipts = "IN";
	/** Billing = FA */
	public static final String MODULO_Billing = "FA";
	/** Services = SE */
	public static final String MODULO_Services = "SE";
	/** Set Module.
		@param Modulo Module	  */
	public void setModulo (String Modulo)
	{
		if (Modulo == null) throw new IllegalArgumentException ("Modulo is mandatory");
		if (Modulo.equals("OT") || Modulo.equals("HO") || Modulo.equals("QU") || Modulo.equals("CE") || Modulo.equals("IN") || Modulo.equals("FA") || Modulo.equals("SE")); else throw new IllegalArgumentException ("Modulo Invalid value - " + Modulo + " - Reference_ID=1200162 - OT - HO - QU - CE - IN - FA - SE");		set_Value (COLUMNNAME_Modulo, Modulo);
	}

	/** Get Module.
		@return Module	  */
	public String getModulo () 
	{
		return (String)get_Value(COLUMNNAME_Modulo);
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