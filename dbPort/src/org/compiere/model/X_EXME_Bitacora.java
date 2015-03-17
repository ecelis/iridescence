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

/** Generated Model for EXME_Bitacora
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Bitacora extends PO implements I_EXME_Bitacora, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Bitacora (Properties ctx, int EXME_Bitacora_ID, String trxName)
    {
      super (ctx, EXME_Bitacora_ID, trxName);
      /** if (EXME_Bitacora_ID == 0)
        {
			setAutorizada (false);
			setBloqueada (false);
			setEXME_Bitacora_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Bitacora (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Bitacora[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Authorized.
		@param Autorizada 
		Authorized
	  */
	public void setAutorizada (boolean Autorizada)
	{
		set_Value (COLUMNNAME_Autorizada, Boolean.valueOf(Autorizada));
	}

	/** Get Authorized.
		@return Authorized
	  */
	public boolean isAutorizada () 
	{
		Object oo = get_Value(COLUMNNAME_Autorizada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Blocked.
		@param Bloqueada 
		Blocked
	  */
	public void setBloqueada (boolean Bloqueada)
	{
		set_Value (COLUMNNAME_Bloqueada, Boolean.valueOf(Bloqueada));
	}

	/** Get Blocked.
		@return Blocked
	  */
	public boolean isBloqueada () 
	{
		Object oo = get_Value(COLUMNNAME_Bloqueada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Log .
		@param EXME_Bitacora_ID 
		Log 
	  */
	public void setEXME_Bitacora_ID (int EXME_Bitacora_ID)
	{
		if (EXME_Bitacora_ID < 1)
			 throw new IllegalArgumentException ("EXME_Bitacora_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Bitacora_ID, Integer.valueOf(EXME_Bitacora_ID));
	}

	/** Get Log .
		@return Log 
	  */
	public int getEXME_Bitacora_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Bitacora_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPacExt.Table_Name);
        I_EXME_CtaPacExt result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPacExt)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPacExt_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account Extension.
		@param EXME_CtaPacExt_ID 
		Patient Account Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPacExt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Patient Account Extension.
		@return Patient Account Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Grand Total.
		@param GrandTotal 
		Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal)
	{
		set_Value (COLUMNNAME_GrandTotal, GrandTotal);
	}

	/** Get Grand Total.
		@return Total amount of document
	  */
	public BigDecimal getGrandTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GrandTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Lines.
		@param TotalLines 
		Total of all document lines
	  */
	public void setTotalLines (BigDecimal TotalLines)
	{
		set_Value (COLUMNNAME_TotalLines, TotalLines);
	}

	/** Get Total Lines.
		@return Total of all document lines
	  */
	public BigDecimal getTotalLines () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalLines);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}