/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_FolDigDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_FolDigDet extends PO implements I_EXME_FolDigDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FolDigDet (Properties ctx, int EXME_FolDigDet_ID, String trxName)
    {
      super (ctx, EXME_FolDigDet_ID, trxName);
      /** if (EXME_FolDigDet_ID == 0)
        {
			setEXME_FolDigDet_ID (0);
			setEXME_FolDig_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FolDigDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FolDigDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
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

	/** Set Detail of Digital Folio ID.
		@param EXME_FolDigDet_ID Detail of Digital Folio ID	  */
	public void setEXME_FolDigDet_ID (int EXME_FolDigDet_ID)
	{
		if (EXME_FolDigDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_FolDigDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FolDigDet_ID, Integer.valueOf(EXME_FolDigDet_ID));
	}

	/** Get Detail of Digital Folio ID.
		@return Detail of Digital Folio ID	  */
	public int getEXME_FolDigDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FolDigDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Folios Assigned ID.
		@param EXME_FolDig_ID 
		Folios Assigned ID
	  */
	public void setEXME_FolDig_ID (int EXME_FolDig_ID)
	{
		if (EXME_FolDig_ID < 1)
			 throw new IllegalArgumentException ("EXME_FolDig_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_FolDig_ID, Integer.valueOf(EXME_FolDig_ID));
	}

	/** Get Folios Assigned ID.
		@return Folios Assigned ID
	  */
	public int getEXME_FolDig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FolDig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cash Balance Date.
		@param FechaCorte 
		Cash Balance Date
	  */
	public void setFechaCorte (Timestamp FechaCorte)
	{
		set_Value (COLUMNNAME_FechaCorte, FechaCorte);
	}

	/** Get Cash Balance Date.
		@return Cash Balance Date
	  */
	public Timestamp getFechaCorte () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCorte);
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

	/** Set Paid.
		@param Pagado Paid	  */
	public void setPagado (boolean Pagado)
	{
		set_Value (COLUMNNAME_Pagado, Boolean.valueOf(Pagado));
	}

	/** Get Paid.
		@return Paid	  */
	public boolean isPagado () 
	{
		Object oo = get_Value(COLUMNNAME_Pagado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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