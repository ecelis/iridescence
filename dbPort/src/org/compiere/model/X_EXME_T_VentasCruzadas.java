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

/** Generated Model for EXME_T_VentasCruzadas
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_T_VentasCruzadas extends PO implements I_EXME_T_VentasCruzadas, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_T_VentasCruzadas (Properties ctx, int EXME_T_VentasCruzadas_ID, String trxName)
    {
      super (ctx, EXME_T_VentasCruzadas_ID, trxName);
      /** if (EXME_T_VentasCruzadas_ID == 0)
        {
			setEXME_T_VentasCruzadas_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_T_VentasCruzadas (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_T_VentasCruzadas[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BP_Group getC_BP_Group() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BP_Group.Table_Name);
        I_C_BP_Group result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BP_Group)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BP_Group_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner Group.
		@param C_BP_Group_ID 
		Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Business Partner Group.
		@return Business Partner Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
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

	/** Set Profit Center Amount 1.
		@param CentroCosto1Amt 
		Profit Center Amount 1
	  */
	public void setCentroCosto1Amt (BigDecimal CentroCosto1Amt)
	{
		set_Value (COLUMNNAME_CentroCosto1Amt, CentroCosto1Amt);
	}

	/** Get Profit Center Amount 1.
		@return Profit Center Amount 1
	  */
	public BigDecimal getCentroCosto1Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CentroCosto1Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Profit Center Amount 2.
		@param CentroCosto2Amt 
		Profit Center Amount 2
	  */
	public void setCentroCosto2Amt (BigDecimal CentroCosto2Amt)
	{
		set_Value (COLUMNNAME_CentroCosto2Amt, CentroCosto2Amt);
	}

	/** Get Profit Center Amount 2.
		@return Profit Center Amount 2
	  */
	public BigDecimal getCentroCosto2Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CentroCosto2Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Profit Center Amount 3.
		@param CentroCosto3Amt Profit Center Amount 3	  */
	public void setCentroCosto3Amt (BigDecimal CentroCosto3Amt)
	{
		set_Value (COLUMNNAME_CentroCosto3Amt, CentroCosto3Amt);
	}

	/** Get Profit Center Amount 3.
		@return Profit Center Amount 3	  */
	public BigDecimal getCentroCosto3Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CentroCosto3Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Profit Center Amount 4.
		@param CentroCosto4Amt Profit Center Amount 4	  */
	public void setCentroCosto4Amt (BigDecimal CentroCosto4Amt)
	{
		set_Value (COLUMNNAME_CentroCosto4Amt, CentroCosto4Amt);
	}

	/** Get Profit Center Amount 4.
		@return Profit Center Amount 4	  */
	public BigDecimal getCentroCosto4Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CentroCosto4Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Profit Center Amount 5.
		@param CentroCosto5Amt Profit Center Amount 5	  */
	public void setCentroCosto5Amt (BigDecimal CentroCosto5Amt)
	{
		set_Value (COLUMNNAME_CentroCosto5Amt, CentroCosto5Amt);
	}

	/** Get Profit Center Amount 5.
		@return Profit Center Amount 5	  */
	public BigDecimal getCentroCosto5Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CentroCosto5Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Profit Center Amount 6.
		@param CentroCosto6Amt Profit Center Amount 6	  */
	public void setCentroCosto6Amt (BigDecimal CentroCosto6Amt)
	{
		set_Value (COLUMNNAME_CentroCosto6Amt, CentroCosto6Amt);
	}

	/** Get Profit Center Amount 6.
		@return Profit Center Amount 6	  */
	public BigDecimal getCentroCosto6Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CentroCosto6Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Profit Center Amount 7.
		@param CentroCosto7Amt Profit Center Amount 7	  */
	public void setCentroCosto7Amt (BigDecimal CentroCosto7Amt)
	{
		set_Value (COLUMNNAME_CentroCosto7Amt, CentroCosto7Amt);
	}

	/** Get Profit Center Amount 7.
		@return Profit Center Amount 7	  */
	public BigDecimal getCentroCosto7Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CentroCosto7Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Profit Center Amount 8.
		@param CentroCosto8Amt Profit Center Amount 8	  */
	public void setCentroCosto8Amt (BigDecimal CentroCosto8Amt)
	{
		set_Value (COLUMNNAME_CentroCosto8Amt, CentroCosto8Amt);
	}

	/** Get Profit Center Amount 8.
		@return Profit Center Amount 8	  */
	public BigDecimal getCentroCosto8Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CentroCosto8Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Cost Center.
		@param EXME_CentroCosto_ID Cost Center	  */
	public void setEXME_CentroCosto_ID (int EXME_CentroCosto_ID)
	{
		if (EXME_CentroCosto_ID < 1) 
			set_Value (COLUMNNAME_EXME_CentroCosto_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CentroCosto_ID, Integer.valueOf(EXME_CentroCosto_ID));
	}

	/** Get Cost Center.
		@return Cost Center	  */
	public int getEXME_CentroCosto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CentroCosto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cross-Selling.
		@param EXME_T_VentasCruzadas_ID Cross-Selling	  */
	public void setEXME_T_VentasCruzadas_ID (int EXME_T_VentasCruzadas_ID)
	{
		if (EXME_T_VentasCruzadas_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_VentasCruzadas_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_T_VentasCruzadas_ID, Integer.valueOf(EXME_T_VentasCruzadas_ID));
	}

	/** Get Cross-Selling.
		@return Cross-Selling	  */
	public int getEXME_T_VentasCruzadas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_VentasCruzadas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}