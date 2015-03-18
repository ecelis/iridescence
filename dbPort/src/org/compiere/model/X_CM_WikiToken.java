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

/** Generated Model for CM_WikiToken
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_CM_WikiToken extends PO implements I_CM_WikiToken, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_WikiToken (Properties ctx, int CM_WikiToken_ID, String trxName)
    {
      super (ctx, CM_WikiToken_ID, trxName);
      /** if (CM_WikiToken_ID == 0)
        {
			setCM_WikiToken_ID (0);
			setName (null);
			setTokenType (null);
// I
        } */
    }

    /** Load Constructor */
    public X_CM_WikiToken (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_CM_WikiToken[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Table_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Wiki Token.
		@param CM_WikiToken_ID 
		Wiki Token
	  */
	public void setCM_WikiToken_ID (int CM_WikiToken_ID)
	{
		if (CM_WikiToken_ID < 1)
			 throw new IllegalArgumentException ("CM_WikiToken_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_WikiToken_ID, Integer.valueOf(CM_WikiToken_ID));
	}

	/** Get Wiki Token.
		@return Wiki Token
	  */
	public int getCM_WikiToken_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_WikiToken_ID);
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

	/** Set Macro.
		@param Macro 
		Macro
	  */
	public void setMacro (String Macro)
	{
		set_Value (COLUMNNAME_Macro, Macro);
	}

	/** Get Macro.
		@return Macro
	  */
	public String getMacro () 
	{
		return (String)get_Value(COLUMNNAME_Macro);
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

	/** Set Sql SELECT.
		@param SelectClause 
		SQL SELECT clause
	  */
	public void setSelectClause (String SelectClause)
	{
		set_Value (COLUMNNAME_SelectClause, SelectClause);
	}

	/** Get Sql SELECT.
		@return SQL SELECT clause
	  */
	public String getSelectClause () 
	{
		return (String)get_Value(COLUMNNAME_SelectClause);
	}

	/** TokenType AD_Reference_ID=1200267 */
	public static final int TOKENTYPE_AD_Reference_ID=1200267;
	/** SQL Command = Q */
	public static final String TOKENTYPE_SQLCommand = "Q";
	/** Internal Link = I */
	public static final String TOKENTYPE_InternalLink = "I";
	/** External Link = E */
	public static final String TOKENTYPE_ExternalLink = "E";
	/** Style = S */
	public static final String TOKENTYPE_Style = "S";
	/** Set TokenType.
		@param TokenType 
		Wiki Token Type
	  */
	public void setTokenType (String TokenType)
	{
		if (TokenType == null) throw new IllegalArgumentException ("TokenType is mandatory");
		if (TokenType.equals("Q") || TokenType.equals("I") || TokenType.equals("E") || TokenType.equals("S")); else throw new IllegalArgumentException ("TokenType Invalid value - " + TokenType + " - Reference_ID=1200267 - Q - I - E - S");		set_Value (COLUMNNAME_TokenType, TokenType);
	}

	/** Get TokenType.
		@return Wiki Token Type
	  */
	public String getTokenType () 
	{
		return (String)get_Value(COLUMNNAME_TokenType);
	}

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		set_Value (COLUMNNAME_WhereClause, WhereClause);
	}

	/** Get Sql WHERE.
		@return Fully qualified SQL WHERE clause
	  */
	public String getWhereClause () 
	{
		return (String)get_Value(COLUMNNAME_WhereClause);
	}
}