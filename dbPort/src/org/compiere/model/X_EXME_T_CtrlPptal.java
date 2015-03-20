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

/** Generated Model for EXME_T_CtrlPptal
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_T_CtrlPptal extends PO implements I_EXME_T_CtrlPptal, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_T_CtrlPptal (Properties ctx, int EXME_T_CtrlPptal_ID, String trxName)
    {
      super (ctx, EXME_T_CtrlPptal_ID, trxName);
      /** if (EXME_T_CtrlPptal_ID == 0)
        {
			setC_ElementValue_ID (0);
			setComp_Acum (Env.ZERO);
			setComp_Per (Env.ZERO);
			setEjer_Acum (Env.ZERO);
			setEjer_Per (Env.ZERO);
			setEXME_T_CtrlPptal_ID (0);
			setPpto_Acum (Env.ZERO);
			setPpto_Per (Env.ZERO);
			setXComp_Acum (Env.ZERO);
			setXComp_Per (Env.ZERO);
			setXEjer_Acum (Env.ZERO);
			setXEjer_Per (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_T_CtrlPptal (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_T_CtrlPptal[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
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

	/** Set Commitment (Accrued).
		@param Comp_Acum 
		Commitment (Accrued)
	  */
	public void setComp_Acum (BigDecimal Comp_Acum)
	{
		if (Comp_Acum == null)
			throw new IllegalArgumentException ("Comp_Acum is mandatory.");
		set_Value (COLUMNNAME_Comp_Acum, Comp_Acum);
	}

	/** Get Commitment (Accrued).
		@return Commitment (Accrued)
	  */
	public BigDecimal getComp_Acum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Comp_Acum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Commitment (Period).
		@param Comp_Per 
		Commitment (Period)
	  */
	public void setComp_Per (BigDecimal Comp_Per)
	{
		if (Comp_Per == null)
			throw new IllegalArgumentException ("Comp_Per is mandatory.");
		set_Value (COLUMNNAME_Comp_Per, Comp_Per);
	}

	/** Get Commitment (Period).
		@return Commitment (Period)
	  */
	public BigDecimal getComp_Per () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Comp_Per);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Done (Accumulated).
		@param Ejer_Acum 
		Done (Accumulated)
	  */
	public void setEjer_Acum (BigDecimal Ejer_Acum)
	{
		if (Ejer_Acum == null)
			throw new IllegalArgumentException ("Ejer_Acum is mandatory.");
		set_Value (COLUMNNAME_Ejer_Acum, Ejer_Acum);
	}

	/** Get Done (Accumulated).
		@return Done (Accumulated)
	  */
	public BigDecimal getEjer_Acum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ejer_Acum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Done (Period).
		@param Ejer_Per 
		Done (Period)
	  */
	public void setEjer_Per (BigDecimal Ejer_Per)
	{
		if (Ejer_Per == null)
			throw new IllegalArgumentException ("Ejer_Per is mandatory.");
		set_Value (COLUMNNAME_Ejer_Per, Ejer_Per);
	}

	/** Get Done (Period).
		@return Done (Period)
	  */
	public BigDecimal getEjer_Per () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ejer_Per);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Report of Budgetary Control.
		@param EXME_T_CtrlPptal_ID 
		Report of Budgetary Control
	  */
	public void setEXME_T_CtrlPptal_ID (int EXME_T_CtrlPptal_ID)
	{
		if (EXME_T_CtrlPptal_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_CtrlPptal_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_T_CtrlPptal_ID, Integer.valueOf(EXME_T_CtrlPptal_ID));
	}

	/** Get Report of Budgetary Control.
		@return Report of Budgetary Control
	  */
	public int getEXME_T_CtrlPptal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_CtrlPptal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Budget (Accumulated).
		@param Ppto_Acum 
		Budget (Accumulated)
	  */
	public void setPpto_Acum (BigDecimal Ppto_Acum)
	{
		if (Ppto_Acum == null)
			throw new IllegalArgumentException ("Ppto_Acum is mandatory.");
		set_Value (COLUMNNAME_Ppto_Acum, Ppto_Acum);
	}

	/** Get Budget (Accumulated).
		@return Budget (Accumulated)
	  */
	public BigDecimal getPpto_Acum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ppto_Acum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Budget (Period).
		@param Ppto_Per 
		Budget (Period)
	  */
	public void setPpto_Per (BigDecimal Ppto_Per)
	{
		if (Ppto_Per == null)
			throw new IllegalArgumentException ("Ppto_Per is mandatory.");
		set_Value (COLUMNNAME_Ppto_Per, Ppto_Per);
	}

	/** Get Budget (Period).
		@return Budget (Period)
	  */
	public BigDecimal getPpto_Per () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ppto_Per);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Session.
		@param Sesion 
		Session
	  */
	public void setSesion (String Sesion)
	{
		set_Value (COLUMNNAME_Sesion, Sesion);
	}

	/** Get Session.
		@return Session
	  */
	public String getSesion () 
	{
		return (String)get_Value(COLUMNNAME_Sesion);
	}

	/** Set To Compromise (Accumulated).
		@param XComp_Acum 
		To Compromise (Accumulated)
	  */
	public void setXComp_Acum (BigDecimal XComp_Acum)
	{
		if (XComp_Acum == null)
			throw new IllegalArgumentException ("XComp_Acum is mandatory.");
		set_Value (COLUMNNAME_XComp_Acum, XComp_Acum);
	}

	/** Get To Compromise (Accumulated).
		@return To Compromise (Accumulated)
	  */
	public BigDecimal getXComp_Acum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_XComp_Acum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set To Compromise (Period).
		@param XComp_Per 
		To Compromise (Period)
	  */
	public void setXComp_Per (BigDecimal XComp_Per)
	{
		if (XComp_Per == null)
			throw new IllegalArgumentException ("XComp_Per is mandatory.");
		set_Value (COLUMNNAME_XComp_Per, XComp_Per);
	}

	/** Get To Compromise (Period).
		@return To Compromise (Period)
	  */
	public BigDecimal getXComp_Per () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_XComp_Per);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set To Be Spend (Accumulated).
		@param XEjer_Acum 
		To Be Spend (Accumulated)
	  */
	public void setXEjer_Acum (BigDecimal XEjer_Acum)
	{
		if (XEjer_Acum == null)
			throw new IllegalArgumentException ("XEjer_Acum is mandatory.");
		set_Value (COLUMNNAME_XEjer_Acum, XEjer_Acum);
	}

	/** Get To Be Spend (Accumulated).
		@return To Be Spend (Accumulated)
	  */
	public BigDecimal getXEjer_Acum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_XEjer_Acum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set To Spend (Period).
		@param XEjer_Per 
		To Spend (Period)
	  */
	public void setXEjer_Per (BigDecimal XEjer_Per)
	{
		if (XEjer_Per == null)
			throw new IllegalArgumentException ("XEjer_Per is mandatory.");
		set_Value (COLUMNNAME_XEjer_Per, XEjer_Per);
	}

	/** Get To Spend (Period).
		@return To Spend (Period)
	  */
	public BigDecimal getXEjer_Per () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_XEjer_Per);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}