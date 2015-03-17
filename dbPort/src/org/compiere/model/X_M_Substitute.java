/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_Substitute
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_M_Substitute extends PO implements I_M_Substitute, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Substitute (Properties ctx, int M_Substitute_ID, String trxName)
    {
      super (ctx, M_Substitute_ID, trxName);
      /** if (M_Substitute_ID == 0)
        {
			setC_UOM_ID (0);
			setM_Product_ID (0);
			setM_Substitute_ID (0);
			setName (null);
			setQtyOrigin (Env.ZERO);
			setQtyTarget (Env.ZERO);
			setSubstitute_ID (0);
			setUOM_Substitute_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Substitute (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Substitute[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
		set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
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

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Substitute ID.
		@param M_Substitute_ID Substitute ID	  */
	public void setM_Substitute_ID (int M_Substitute_ID)
	{
		if (M_Substitute_ID < 1)
			 throw new IllegalArgumentException ("M_Substitute_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Substitute_ID, Integer.valueOf(M_Substitute_ID));
	}

	/** Get Substitute ID.
		@return Substitute ID	  */
	public int getM_Substitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Substitute_ID);
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

	/** Set Quantity Origin.
		@param QtyOrigin Quantity Origin	  */
	public void setQtyOrigin (BigDecimal QtyOrigin)
	{
		if (QtyOrigin == null)
			throw new IllegalArgumentException ("QtyOrigin is mandatory.");
		set_Value (COLUMNNAME_QtyOrigin, QtyOrigin);
	}

	/** Get Quantity Origin.
		@return Quantity Origin	  */
	public BigDecimal getQtyOrigin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrigin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Target.
		@param QtyTarget Quantity Target	  */
	public void setQtyTarget (BigDecimal QtyTarget)
	{
		if (QtyTarget == null)
			throw new IllegalArgumentException ("QtyTarget is mandatory.");
		set_Value (COLUMNNAME_QtyTarget, QtyTarget);
	}

	/** Get Quantity Target.
		@return Quantity Target	  */
	public BigDecimal getQtyTarget () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyTarget);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Substitute.
		@param Substitute_ID 
		Entity which can be used in place of this entity
	  */
	public void setSubstitute_ID (int Substitute_ID)
	{
		if (Substitute_ID < 1)
			 throw new IllegalArgumentException ("Substitute_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Substitute_ID, Integer.valueOf(Substitute_ID));
	}

	/** Get Substitute.
		@return Entity which can be used in place of this entity
	  */
	public int getSubstitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Substitute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM Substitute.
		@param UOM_Substitute_ID UOM Substitute	  */
	public void setUOM_Substitute_ID (int UOM_Substitute_ID)
	{
		if (UOM_Substitute_ID < 1)
			 throw new IllegalArgumentException ("UOM_Substitute_ID is mandatory.");
		set_Value (COLUMNNAME_UOM_Substitute_ID, Integer.valueOf(UOM_Substitute_ID));
	}

	/** Get UOM Substitute.
		@return UOM Substitute	  */
	public int getUOM_Substitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UOM_Substitute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}