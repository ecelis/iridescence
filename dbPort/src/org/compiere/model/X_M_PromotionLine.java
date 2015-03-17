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

/** Generated Model for M_PromotionLine
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_M_PromotionLine extends PO implements I_M_PromotionLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_PromotionLine (Properties ctx, int M_PromotionLine_ID, String trxName)
    {
      super (ctx, M_PromotionLine_ID, trxName);
      /** if (M_PromotionLine_ID == 0)
        {
			setIsMandatoryPL (true);
// Y
			setM_Promotion_ID (0);
			setM_PromotionLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_PromotionLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_PromotionLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Mandatory Promotion Line.
		@param IsMandatoryPL 
		Order must have this promotion line
	  */
	public void setIsMandatoryPL (boolean IsMandatoryPL)
	{
		set_Value (COLUMNNAME_IsMandatoryPL, Boolean.valueOf(IsMandatoryPL));
	}

	/** Get Mandatory Promotion Line.
		@return Order must have this promotion line
	  */
	public boolean isMandatoryPL () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatoryPL);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Minimum Amt.
		@param MinimumAmt 
		Minumum Amout in Document Currency
	  */
	public void setMinimumAmt (BigDecimal MinimumAmt)
	{
		set_Value (COLUMNNAME_MinimumAmt, MinimumAmt);
	}

	/** Get Minimum Amt.
		@return Minumum Amout in Document Currency
	  */
	public BigDecimal getMinimumAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinimumAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_M_PromotionGroup getM_PromotionGroup() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_PromotionGroup.Table_Name);
        I_M_PromotionGroup result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PromotionGroup)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PromotionGroup_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Promotion Group.
		@param M_PromotionGroup_ID Promotion Group	  */
	public void setM_PromotionGroup_ID (int M_PromotionGroup_ID)
	{
		if (M_PromotionGroup_ID < 1) 
			set_Value (COLUMNNAME_M_PromotionGroup_ID, null);
		else 
			set_Value (COLUMNNAME_M_PromotionGroup_ID, Integer.valueOf(M_PromotionGroup_ID));
	}

	/** Get Promotion Group.
		@return Promotion Group	  */
	public int getM_PromotionGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PromotionGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Promotion getM_Promotion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Promotion.Table_Name);
        I_M_Promotion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Promotion)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Promotion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Promotion.
		@param M_Promotion_ID Promotion	  */
	public void setM_Promotion_ID (int M_Promotion_ID)
	{
		if (M_Promotion_ID < 1)
			 throw new IllegalArgumentException ("M_Promotion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Promotion_ID, Integer.valueOf(M_Promotion_ID));
	}

	/** Get Promotion.
		@return Promotion	  */
	public int getM_Promotion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Promotion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Promotion Line.
		@param M_PromotionLine_ID Promotion Line	  */
	public void setM_PromotionLine_ID (int M_PromotionLine_ID)
	{
		if (M_PromotionLine_ID < 1)
			 throw new IllegalArgumentException ("M_PromotionLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_PromotionLine_ID, Integer.valueOf(M_PromotionLine_ID));
	}

	/** Get Promotion Line.
		@return Promotion Line	  */
	public int getM_PromotionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PromotionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}