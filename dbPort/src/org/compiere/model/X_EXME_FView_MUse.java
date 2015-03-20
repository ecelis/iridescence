/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_FView_MUse
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_FView_MUse extends PO implements I_EXME_FView_MUse, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FView_MUse (Properties ctx, int EXME_FView_MUse_ID, String trxName)
    {
      super (ctx, EXME_FView_MUse_ID, trxName);
      /** if (EXME_FView_MUse_ID == 0)
        {
			setEXME_FView_MUse_ID (0);
			setName (null);
			setSequence (Env.ZERO);
			setSolution (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FView_MUse (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FView_MUse[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set EXME_FView_MUse_ID.
		@param EXME_FView_MUse_ID EXME_FView_MUse_ID	  */
	public void setEXME_FView_MUse_ID (int EXME_FView_MUse_ID)
	{
		if (EXME_FView_MUse_ID < 1)
			 throw new IllegalArgumentException ("EXME_FView_MUse_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FView_MUse_ID, Integer.valueOf(EXME_FView_MUse_ID));
	}

	/** Get EXME_FView_MUse_ID.
		@return EXME_FView_MUse_ID	  */
	public int getEXME_FView_MUse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FView_MUse_ID);
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

	/** Set Parent.
		@param Parent_ID 
		Parent of Entity
	  */
	public void setParent_ID (int Parent_ID)
	{
		if (Parent_ID < 1) 
			set_Value (COLUMNNAME_Parent_ID, null);
		else 
			set_Value (COLUMNNAME_Parent_ID, Integer.valueOf(Parent_ID));
	}

	/** Get Parent.
		@return Parent of Entity
	  */
	public int getParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Path.
		@param Path Path	  */
	public void setPath (String Path)
	{
		set_Value (COLUMNNAME_Path, Path);
	}

	/** Get Path.
		@return Path	  */
	public String getPath () 
	{
		return (String)get_Value(COLUMNNAME_Path);
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (BigDecimal Sequence)
	{
		if (Sequence == null)
			throw new IllegalArgumentException ("Sequence is mandatory.");
		set_Value (COLUMNNAME_Sequence, Sequence);
	}

	/** Get Sequence.
		@return Sequence	  */
	public BigDecimal getSequence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sequence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Solution.
		@param Solution Solution	  */
	public void setSolution (String Solution)
	{
		if (Solution == null)
			throw new IllegalArgumentException ("Solution is mandatory.");
		set_Value (COLUMNNAME_Solution, Solution);
	}

	/** Get Solution.
		@return Solution	  */
	public String getSolution () 
	{
		return (String)get_Value(COLUMNNAME_Solution);
	}
}