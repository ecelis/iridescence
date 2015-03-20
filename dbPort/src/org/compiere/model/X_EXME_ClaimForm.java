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

/** Generated Model for EXME_ClaimForm
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ClaimForm extends PO implements I_EXME_ClaimForm, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClaimForm (Properties ctx, int EXME_ClaimForm_ID, String trxName)
    {
      super (ctx, EXME_ClaimForm_ID, trxName);
      /** if (EXME_ClaimForm_ID == 0)
        {
			setAD_Column_ID (0);
			setAD_Table_ID (0);
			setEXME_ClaimForm_ID (0);
			setEXME_ClaimMessage_ID (0);
			setLine (Env.ZERO);
			setPosition (Env.ZERO);
			setRecord_ID (0);
			setSql (null);
			setSubPosition (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ClaimForm (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ClaimForm[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Column getAD_Column() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Column.Table_Name);
        I_AD_Column result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Column)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Column_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1)
			 throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
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

	/** Set EXME_ClaimForm_ID.
		@param EXME_ClaimForm_ID EXME_ClaimForm_ID	  */
	public void setEXME_ClaimForm_ID (int EXME_ClaimForm_ID)
	{
		if (EXME_ClaimForm_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClaimForm_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ClaimForm_ID, Integer.valueOf(EXME_ClaimForm_ID));
	}

	/** Get EXME_ClaimForm_ID.
		@return EXME_ClaimForm_ID	  */
	public int getEXME_ClaimForm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimForm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ClaimMessage getEXME_ClaimMessage() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClaimMessage.Table_Name);
        I_EXME_ClaimMessage result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClaimMessage)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClaimMessage_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_ClaimMessage_ID.
		@param EXME_ClaimMessage_ID EXME_ClaimMessage_ID	  */
	public void setEXME_ClaimMessage_ID (int EXME_ClaimMessage_ID)
	{
		if (EXME_ClaimMessage_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClaimMessage_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ClaimMessage_ID, Integer.valueOf(EXME_ClaimMessage_ID));
	}

	/** Get EXME_ClaimMessage_ID.
		@return EXME_ClaimMessage_ID	  */
	public int getEXME_ClaimMessage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimMessage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (BigDecimal Line)
	{
		if (Line == null)
			throw new IllegalArgumentException ("Line is mandatory.");
		set_Value (COLUMNNAME_Line, Line);
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public BigDecimal getLine () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Line);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Position.
		@param Position Position	  */
	public void setPosition (BigDecimal Position)
	{
		if (Position == null)
			throw new IllegalArgumentException ("Position is mandatory.");
		set_Value (COLUMNNAME_Position, Position);
	}

	/** Get Position.
		@return Position	  */
	public BigDecimal getPosition () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Position);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0)
			 throw new IllegalArgumentException ("Record_ID is mandatory.");
		set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sql.
		@param Sql Sql	  */
	public void setSql (String Sql)
	{
		if (Sql == null)
			throw new IllegalArgumentException ("Sql is mandatory.");
		set_Value (COLUMNNAME_Sql, Sql);
	}

	/** Get Sql.
		@return Sql	  */
	public String getSql () 
	{
		return (String)get_Value(COLUMNNAME_Sql);
	}

	/** Set SubPosition.
		@param SubPosition SubPosition	  */
	public void setSubPosition (String SubPosition)
	{
		if (SubPosition == null)
			throw new IllegalArgumentException ("SubPosition is mandatory.");
		set_Value (COLUMNNAME_SubPosition, SubPosition);
	}

	/** Get SubPosition.
		@return SubPosition	  */
	public String getSubPosition () 
	{
		return (String)get_Value(COLUMNNAME_SubPosition);
	}
}