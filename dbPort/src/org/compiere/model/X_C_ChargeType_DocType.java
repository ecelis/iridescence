/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for C_ChargeType_DocType
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_ChargeType_DocType extends PO implements I_C_ChargeType_DocType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_ChargeType_DocType (Properties ctx, int C_ChargeType_DocType_ID, String trxName)
    {
      super (ctx, C_ChargeType_DocType_ID, trxName);
      /** if (C_ChargeType_DocType_ID == 0)
        {
			setC_ChargeType_ID (0);
			setC_DocType_ID (0);
			setIsAllowNegative (true);
// Y
			setIsAllowPositive (true);
// Y
        } */
    }

    /** Load Constructor */
    public X_C_ChargeType_DocType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_ChargeType_DocType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_ChargeType getC_ChargeType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_ChargeType.Table_Name);
        I_C_ChargeType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_ChargeType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_ChargeType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Charge Type.
		@param C_ChargeType_ID Charge Type	  */
	public void setC_ChargeType_ID (int C_ChargeType_ID)
	{
		if (C_ChargeType_ID < 1)
			 throw new IllegalArgumentException ("C_ChargeType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_ChargeType_ID, Integer.valueOf(C_ChargeType_ID));
	}

	/** Get Charge Type.
		@return Charge Type	  */
	public int getC_ChargeType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ChargeType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_DocType getC_DocType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			 throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Allow Negative.
		@param IsAllowNegative Allow Negative	  */
	public void setIsAllowNegative (boolean IsAllowNegative)
	{
		set_Value (COLUMNNAME_IsAllowNegative, Boolean.valueOf(IsAllowNegative));
	}

	/** Get Allow Negative.
		@return Allow Negative	  */
	public boolean isAllowNegative () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllowNegative);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Positive.
		@param IsAllowPositive Allow Positive	  */
	public void setIsAllowPositive (boolean IsAllowPositive)
	{
		set_Value (COLUMNNAME_IsAllowPositive, Boolean.valueOf(IsAllowPositive));
	}

	/** Get Allow Positive.
		@return Allow Positive	  */
	public boolean isAllowPositive () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllowPositive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}