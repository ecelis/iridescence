/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for GL_FundRestriction
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_GL_FundRestriction extends PO implements I_GL_FundRestriction, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_GL_FundRestriction (Properties ctx, int GL_FundRestriction_ID, String trxName)
    {
      super (ctx, GL_FundRestriction_ID, trxName);
      /** if (GL_FundRestriction_ID == 0)
        {
			setC_ElementValue_ID (0);
			setGL_Fund_ID (0);
			setGL_FundRestriction_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_GL_FundRestriction (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_GL_FundRestriction[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_ElementValue getC_ElementValue() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_ElementValue.Table_Name);
        I_C_ElementValue result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_ElementValue)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_ElementValue_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1)
			 throw new IllegalArgumentException ("C_ElementValue_ID is mandatory.");
		set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
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

	public I_GL_Fund getGL_Fund() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_GL_Fund.Table_Name);
        I_GL_Fund result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_GL_Fund)constructor.newInstance(new Object[] {getCtx(), new Integer(getGL_Fund_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set GL Fund.
		@param GL_Fund_ID 
		General Ledger Funds Control
	  */
	public void setGL_Fund_ID (int GL_Fund_ID)
	{
		if (GL_Fund_ID < 1)
			 throw new IllegalArgumentException ("GL_Fund_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GL_Fund_ID, Integer.valueOf(GL_Fund_ID));
	}

	/** Get GL Fund.
		@return General Ledger Funds Control
	  */
	public int getGL_Fund_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Fund_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fund Restriction.
		@param GL_FundRestriction_ID 
		Restriction of Funds
	  */
	public void setGL_FundRestriction_ID (int GL_FundRestriction_ID)
	{
		if (GL_FundRestriction_ID < 1)
			 throw new IllegalArgumentException ("GL_FundRestriction_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GL_FundRestriction_ID, Integer.valueOf(GL_FundRestriction_ID));
	}

	/** Get Fund Restriction.
		@return Restriction of Funds
	  */
	public int getGL_FundRestriction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_FundRestriction_ID);
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
}